package com.swz.log.aspect;

import com.swz.log.domain.Log;
import com.swz.log.target.ControllerLog;
import com.swz.service.log.LogService;
import com.swz.service.log.impl.LogServiceImpl;
import com.swz.utils.JsonUtil;
import com.swz.utils.UrlUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @Package: com.swz.log
 * @Description: AOP日志处理
 * @author: swz
 * @date: 2019/3/29 9:15
 */
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final ThreadLocal<LocalDateTime> beginTimeThreadLocal = new NamedThreadLocal<>("ThreadLocal beginTime");
    private static final ThreadLocal<Log> logThreadLocal = new NamedThreadLocal<>("ThreadLocal log");
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    @Autowired(required = false)
    HttpServletRequest request;

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    LogServiceImpl logService;

    /**
     * Controller层切点 注解拦截
     */
    @Pointcut("@annotation(com.swz.log.target.ControllerLog)")
    public void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作的开始时间
     *
     * @param joinPoint 切点
     * @throws InterruptedException
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws InterruptedException {
        logger.info("###########################进入日志切面前置通知###########################");
        LocalDateTime beginTime = LocalDateTime.now();
        beginTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）  
        if (logger.isDebugEnabled()) {//这里日志级别为debug
            logger.debug("######开始计时: {}, URI: {} ######", beginTime.format(format), request.getRequestURI());
        }
    }

    /**
     * 后置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @After("controllerAspect()")
    public void doAfter(JoinPoint joinPoint) {
        logger.info("###########################进入日志切面后置通知###########################");
        String title = "";
        String type = "info";
        String remoteAddr = request.getRemoteAddr();
        String requestUri = request.getRequestURI();
        String method = request.getMethod();
        Map<String, String> params = UrlUtil.getParameterStringMap(request);
        title = getControllerMethodDescription(joinPoint);
        // 打印JVM信息。
        long beginTime = beginTimeThreadLocal.get().toInstant(ZoneOffset.of("+8")).toEpochMilli();//e（开始时间）  
        long endTime = System.currentTimeMillis(); //2、结束时间  
        if (logger.isDebugEnabled()) {
            logger.debug("######计时结束：{},  URI: {},  耗时： {},   最大内存: {}m,  已分配内存: {}m,  已分配内存中的剩余空间: {}m,  最大可用内存: {}m。######", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(endTime),
                    request.getRequestURI(), endTime - beginTime,
                    Runtime.getRuntime().maxMemory() / 1024 / 1024,
                    Runtime.getRuntime().totalMemory() / 1024 / 1024,
                    Runtime.getRuntime().freeMemory() / 1024 / 1024,
                    (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
        }
        Log log = new Log();
        //log.setId(1L);
        log.setTitle(title);
        log.setType(type);
        log.setRemoteAddr(remoteAddr);
        log.setRequestUri(requestUri);
        log.setMethod(method);
        log.setParams(JsonUtil.map2Json(params));
        log.setExcep("无异常!");
        log.setUserId("7b100e8d97cf434d98c951a34f282a9c");
        LocalDateTime operateDate = beginTimeThreadLocal.get();
        log.setOperateDate(operateDate);
        log.setTimeout(endTime - beginTime);

        //再优化:通过线程池来执行日志保存
        threadPoolTaskExecutor.execute(new SaveLogThread(log, logService));
        logThreadLocal.set(log);
    }

    /**
     * 返回参数日志
     *
     * @param res
     */
    @AfterReturning(returning = "res", pointcut = "controllerAspect()")
    public void doAfterReturning(Object res) throws Throwable {
        // 处理完请求，返回内容
        logger.info("###########################返回参数日志###########################");
        logger.info("######返回接口响应参数:{} ######" + JsonUtil.object2Json(res));
        Log log = logThreadLocal.get();
        if (log != null) {
            log.setResultParams(JsonUtil.object2Json(res));
            logger.info("###########################更新日志参数###########################");
            new UpdateLogThread(log, logService).start();
        }
    }

    /**
     * 异常通知 记录操作报错日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        logger.info("###########################进入日志切面异常通知!###########################");
        logger.info("######异常信息: {} ######" + e.getMessage());
        Log log = logThreadLocal.get();
        if (log != null) {
            log.setType("templates/error");
            log.setExcep(e.toString());
            new UpdateLogThread(log, logService).start();
        }
    }

    /***
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return discription  
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ControllerLog controllerLog = method.getAnnotation(ControllerLog.class);
        String discription = controllerLog.description();
        return discription;
    }

    /**
     * 保存日志线程 
     */
    private static class SaveLogThread implements Runnable {
        private Log log;
        private LogService logService;

        public SaveLogThread(Log log, LogService logService) {
            this.log = log;
            this.logService = logService;
        }

        @Override
        public void run() {
            logService.addLog(log);
        }
    }

    /**
     * 日志更新线程
     */
    private static class UpdateLogThread extends Thread {
        private Log log;
        private LogService logService;

        public UpdateLogThread(Log log, LogService logService) {
            super(UpdateLogThread.class.getSimpleName());
            this.log = log;
            this.logService = logService;
        }

        @Override
        public void run() {
            this.logService.putLog(log);
        }
    }

}
