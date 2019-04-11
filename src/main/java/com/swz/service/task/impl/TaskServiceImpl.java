package com.swz.service.task.impl;

import com.swz.pojo.domain.TaskDO;
import com.swz.mapper.TaskMapper;
import com.swz.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Package: com.swz.service.task.impl
 * @Description: 任务service实现类
 * @author: swz
 * @date: 2018/10/19 16:01
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<TaskDO> findAll() {
        return taskMapper.findAll();
    }

    @Override
    public TaskDO findByJobName(String jobName) {
        return taskMapper.selectByJobName(jobName);
    }
}
