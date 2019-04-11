package com.swz.service.person.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swz.mapper.PersonMapper;
import com.swz.pojo.bo.PersonBO;
import com.swz.pojo.dto.PersonDTO;
import com.swz.service.person.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Package: com.swz.service.personDO.impl
 * @Description: LogService
 * @author: swz
 * @date: 2018/7/23 10:35
 */
@Service
@Transactional(readOnly = true)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class PersonServiceImpl implements PersonService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonMapper personMapper;

    private PersonDTO personDTO = null;
    private PersonBO personBO = null;

    @Override
    public PersonDTO findAll() {
        personDTO = new PersonDTO();
        personBO = new PersonBO();
        personBO.setPersonDOList(personMapper.findAll());
        personDTO.setPersonBO(personBO);
        return personDTO;
    }

    @Override
    public PersonDTO findByPage(int pageNo, int pageSize) {
        try {
            PageHelper.startPage(pageNo, pageSize);
            personDTO = new PersonDTO();
            personBO = new PersonBO();
            personBO.setPageInfo(new PageInfo<>(personMapper.findByPage()));
            personDTO.setPersonBO(personBO);
            return personDTO;
        } catch (Exception e) {
            logger.info("分页查询用户异常！", e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public void insert(PersonBO personBO) {
        personMapper.insert(personBO.getPersonDO());
    }

    @Override
    @Transactional
    public void update(PersonBO personBO) {
        personMapper.updateByPrimaryKey(personBO.getPersonDO());
    }

    @Override
    public PersonDTO findById(Long id) {
        personDTO = new PersonDTO();
        personBO = new PersonBO();
        personBO.setPersonDO(personMapper.selectByPrimaryKey(id));
        personDTO.setPersonBO(personBO);
        return personDTO;
    }

    @Override
    public PersonDTO findByName(String name) {
        personDTO = new PersonDTO();
        personBO = new PersonBO();
        personBO.setPersonDOList(personMapper.findByName(name));
        personDTO.setPersonBO(personBO);
        return personDTO;
    }
}
