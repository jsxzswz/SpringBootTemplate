package com.swz.service.person;

import com.swz.pojo.dto.request.PersonReqDTO;
import com.swz.pojo.dto.response.PersonResDTO;

/**
 * @Package: com.swz.service
 * @Description: LogService
 * @author: swz
 * @date: 2018/7/23 10:09
 */
public interface PersonService {

    /**
     * @param personReqDTO
     * @Description: 新增用户策略：先插入数据表，成功之后，删除原来的缓存，再更新缓存
     * @method: insertPersonDO
     * @return: int
     */
    int insertPerson(PersonReqDTO personReqDTO);

    /**
     * @param
     * @Description: 获取用户策略：先从缓存中获取用户，没有则取数据表中数据，再将数据写入缓存
     * @method: listPersonsAll
     * @return: com.swz.pojo.dto.response.PersonResDTO
     */
    PersonResDTO listPersonsAll();

    /**
     * @param personReqDTO
     * @Description: 获取用户策略：先从缓存中获取用户，没有则取数据表中数据，再将数据写入缓存
     * @method: getPerson
     * @return: com.swz.pojo.dto.response.PersonResDTO
     */
    PersonResDTO getPerson(PersonReqDTO personReqDTO);

    /**
     * @param personReqDTO
     * @Description: 更新用户策略：先更新数据表，成功之后，删除原来的缓存，再更新缓存
     * @method: updatePerson
     * @return: int
     */
    int updatePerson(PersonReqDTO personReqDTO);

    /**
     * @param personReqDTO
     * @Description: 删除用户策略：删除数据表中数据，然后删除缓存
     * @method: deletePerson
     * @return: int
     */
    int deletePerson(PersonReqDTO personReqDTO);

    /**
     * @param personReqDTO
     * @Description: 删除多个用户策略：删除数据表中数据，然后删除缓存
     * @method: deletePersons
     * @return: int
     */
    int deletePersons(PersonReqDTO personReqDTO);

    /**
     * @param personReqDTO
     * @Description: 分页查询
     * @method: findByPage
     * @return: Page<PersonDO>
     */
    PersonResDTO listPersonsPage(PersonReqDTO personReqDTO);

}
