package com.swz.service.task;

import com.swz.pojo.domain.TaskDO;

import java.util.List;

/**
 * @Package: com.swz.service.task
 * @Description: 任务service
 * @author: swz
 * @date: 2018/10/19 15:58
 */
public interface TaskService {

    List<TaskDO> findAll();

    TaskDO findByJobName(String jobName);

}
