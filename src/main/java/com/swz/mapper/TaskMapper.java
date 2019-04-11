package com.swz.mapper;

import com.swz.pojo.domain.TaskDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Package: com.swz.mapper
 * @Description: 任务查询接口
 * @author: swz
 * @date: 2018/10/19 15:47
 */
@Mapper
public interface TaskMapper {

    TaskDO selectByJobName(String jobName);

    List<TaskDO> findAll();
}
