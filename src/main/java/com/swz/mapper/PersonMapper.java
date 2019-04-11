package com.swz.mapper;

import com.github.pagehelper.Page;
import com.swz.pojo.domain.PersonDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Package: com.swz.mapper
 * @Description: PersonMapper
 * @author: swz
 * @date: 2018/7/23 10:45
 */
@Mapper//声明成mybatis Dao层的Bean，也可以在配置类上使用@MapperScan("com.swz.mapper")注解声明
public interface PersonMapper {

    int deleteByPrimaryKey(Long id);

    int insert(PersonDO record);

    int insertSelective(PersonDO record);

    PersonDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PersonDO record);

    int updateByPrimaryKey(PersonDO record);

    List<PersonDO> findByName(String name);

    /**
     * 获取所有数据
     *
     * @return
     */
    List<PersonDO> findAll();

    /**
     * 分页查询数据
     *
     * @return
     */
    Page<PersonDO> findByPage();

}
