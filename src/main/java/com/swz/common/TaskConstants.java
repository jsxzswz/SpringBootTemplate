package com.swz.common;

/**
 * @Package: com.swz.common
 * @Description: 任务静态配置类
 * @author: swz
 * @date: 2018/10/22 11:31
 */
public final class TaskConstants {

    /*定时任务JobName名称*/
    public final static String JOBNAME = "jobDetail";
    public final static String JOBNAME2 = "jobDetail2";

    /*是否并发执行*/
    public final static Boolean CONCURRENT = false;

    /*设置任务启动延迟*/
    public final static int STARTDELAY = 0;

    /*应用延时启动任务*/
    public final static int STARTUPDELAY = 10;

}
