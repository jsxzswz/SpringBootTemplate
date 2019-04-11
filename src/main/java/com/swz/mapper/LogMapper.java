package com.swz.mapper;

import com.swz.log.domain.Log;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Package: com.swz.mapper
 * @Description: LogMapper
 * @author: swz
 * @date: 2018/7/23 10:45
 */
@Mapper//声明成mybatis Dao层的Bean，也可以在配置类上使用@MapperScan("com.swz.mapper")注解声明
public interface LogMapper {

    int insert(Log log);

    int update(Log log);

}
