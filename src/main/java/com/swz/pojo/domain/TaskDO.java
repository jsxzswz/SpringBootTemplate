package com.swz.pojo.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Package: com.swz.pojo.domain
 * @Description: 任务表实体类
 * @author: swz
 * @date: 2018/10/19 15:32
 */
@Data
public class TaskDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;//id

    private String jobName;//任务名称

    private String method;//任务方法

    private String cron;//cron表达式

    private String status;//状态

    private String jobDesc;//任务描述

    private Date createDate;//创建时间

    private Date updateDate;//更新时间

}