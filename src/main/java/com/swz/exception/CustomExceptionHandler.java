package com.swz.exception;

import com.swz.pojo.ResJsonVO;
import com.swz.utils.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Package: com.swz.interceptor
 * @Description: 拦截器处理空白页异常
 * @author: swz
 * @date: 2019/4/1 9:48
 */
@ControllerAdvice//在异常类上添加ControllerAdvice注解
public class CustomExceptionHandler {

    /**
     * 不管是访问返回视图接口，还是返回json串接口，只要抛出的excetion异常全部由这个方法拦截，并统一返回json串
     * 统一异常拦截 rest接口
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)//Exception 这个是所有异常的父类
    //定义异常统一返回json，即使是返回视图的接口
    @ResponseBody
    String handleControllerException(HttpServletRequest request, Exception ex) {
        ResJsonVO resJsonVO = new ResJsonVO();
        HttpStatus status = getStatus(request);
        resJsonVO.setStatus(status.value());
        resJsonVO.setMsg(ex.getMessage());
        return JsonUtil.object2Json(resJsonVO);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    /**
     * 拦截自定义异常 CustomException rest接口
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    String handleControllerCustomException(HttpServletRequest request, CustomException ex) {
        ResJsonVO resJsonVO = new ResJsonVO();
        HttpStatus status = getStatus(request);
        resJsonVO.setStatus(status.value());
        resJsonVO.setMsg(ex.getErrorMsg());
        return JsonUtil.object2Json(resJsonVO);
    }

    /**
     * 拦截抛出CustomViewException 异常的方法并返回视图
     *
     * @param request
     * @param ex
     * @return
     */
    //@ExceptionHandler(CustomViewException.class)
    //ModelAndView handleControllerCustomExceptionView(HttpServletRequest request, CustomViewException ex) {
    //    ModelAndView modelAndView = new ModelAndView();
    //    modelAndView.setViewName("error");
    //    HttpStatus status = getStatus(request);
    //    Map<String, String> map = new HashMap();
    //    map.put("code", String.valueOf(status.value()));
    //    map.put("msg", "customer error msg");
    //    return modelAndView;
    //}

}
