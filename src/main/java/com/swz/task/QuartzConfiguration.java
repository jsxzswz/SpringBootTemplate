package com.swz.task;

import com.swz.common.TaskConstants;
import com.swz.pojo.domain.TaskDO;
import com.swz.service.task.TaskService;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @Package: com.swz.task
 * @Description: quartz配置类
 * @author: swz
 * @date: 2018/10/19 15:42
 */
@Configuration
public class QuartzConfiguration {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskService taskService;

    private TaskDO config;

    /****************************************
     * jobDetail定时任务开始
     ****************************************/
    // 配置定时任务
    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean jobDetail(ScheduleTask task1) {
        config = taskService.findByJobName(TaskConstants.JOBNAME);
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setConcurrent(TaskConstants.CONCURRENT);
        jobDetail.setName(config.getJobName());
        jobDetail.setTargetObject(task1);
        jobDetail.setTargetMethod(config.getMethod());
        logger.info("定时任务jobDetail【测试】配置完成");
        return jobDetail;
    }

    // 配置触发器
    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean jobTrigger(JobDetail jobDetail) {
        config = taskService.findByJobName(TaskConstants.JOBNAME);
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetail);
        trigger.setStartDelay(TaskConstants.STARTDELAY);
        trigger.setName(config.getJobName());
        trigger.setCronExpression(config.getCron());
        logger.info("定时触发器jobTrigger【测试】配置完成");
        return trigger;
    }
    /****************************************jobDetail定时任务结束***************************************/

    /****************************************
     * jobDetail2定时任务开始
     ****************************************/
    // 配置定时任务
    @Bean(name = "jobDetail2")
    public MethodInvokingJobDetailFactoryBean jobDetail2(ScheduleTask task2) {
        config = taskService.findByJobName(TaskConstants.JOBNAME2);
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setConcurrent(TaskConstants.CONCURRENT);
        jobDetail.setName(config.getJobName());
        jobDetail.setTargetObject(task2);
        jobDetail.setTargetMethod(config.getMethod());
        logger.info("定时任务jobDetail2【测试】配置完成");
        return jobDetail;
    }

    // 配置触发器
    @Bean(name = "jobTrigger2")
    public CronTriggerFactoryBean jobTrigger2(JobDetail jobDetail2) {
        config = taskService.findByJobName(TaskConstants.JOBNAME2);
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetail2);
        trigger.setStartDelay(TaskConstants.STARTDELAY);
        trigger.setName(config.getJobName());
        trigger.setCronExpression(config.getCron());
        logger.info("定时触发器jobTrigger2【测试】配置完成");
        return trigger;
    }

    /*****************************************
     * jobDetail2定时任务结束
     ***************************************/

    // 配置Scheduler
    @Bean(name = "scheduler")
        public SchedulerFactoryBean schedulerFactory(Trigger[] triggers) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setStartupDelay(TaskConstants.STARTUPDELAY);
        bean.setTriggers(triggers);
        logger.info("调度工厂配置完成,Quartz在应用启动10秒后启动");
        return bean;
    }

}
