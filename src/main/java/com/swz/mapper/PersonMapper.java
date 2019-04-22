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

    int insertPerson(PersonDO person);

    int updatePerson(PersonDO person);

    int deletePerson(Long id);

    int deletePersons(List ids);

    PersonDO getPerson(Long id);

    List<PersonDO> listPersonsAll();

    Page<PersonDO> listPersonsPage();
    
}
