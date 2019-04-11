package com.swz.service.person;

import com.swz.pojo.bo.PersonBO;
import com.swz.pojo.dto.PersonDTO;

/**
 * @Package: com.swz.service
 * @Description: LogService
 * @author: swz
 * @date: 2018/7/23 10:09
 */
public interface PersonService {

    PersonDTO findAll();

    /**
     * @Description: 分页查询
     * @method: findByPage
     * @param pageNo
     * @param pageSize
     * @return: Page<PersonDO>
     */
    PersonDTO findByPage(int pageNo, int pageSize);

    void insert(PersonBO personBO);

    void update(PersonBO personBO);

    PersonDTO findById(Long id);

    PersonDTO findByName(String name);

}
