package com.swz.log.target;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Package: com.swz.log.target
 * @Description: 定义日志注解
 * @author: swz
 * @date: 2019/3/29 9:29
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerLog {

    String description() default "";

}
