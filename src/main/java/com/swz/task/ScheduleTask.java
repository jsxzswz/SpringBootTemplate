package com.swz.task;

import com.alibaba.fastjson.JSONObject;
import com.swz.service.person.PersonService;
import com.swz.service.task.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Package: com.swz.task.hello
 * @Description: 任务类
 * @author: swz
 * @date: 2018/10/19 15:44
 */
@Configuration
@EnableScheduling
public class ScheduleTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskService taskService;

    @Autowired
    private PersonService personService;

    public void Hello() {
        logger.info("######所有的定时任务：{}######", JSONObject.toJSON(taskService.findAll()));
    }

    public void Hello2() {
        logger.info("######查询用户信息：{}######", JSONObject.toJSON(personService.listPersonsAll()));
    }

}
