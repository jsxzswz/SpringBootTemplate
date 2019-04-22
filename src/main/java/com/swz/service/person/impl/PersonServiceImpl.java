package com.swz.service.person.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swz.mapper.PersonMapper;
import com.swz.pojo.domain.PersonDO;
import com.swz.pojo.dto.request.PersonReqDTO;
import com.swz.pojo.dto.response.PersonResDTO;
import com.swz.redis.RedisUtil;
import com.swz.redis.key.PersonKey;
import com.swz.service.person.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    private final long DEADLINE = 24 * 60 * 60;//缓存有效期

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional(readOnly = false)
    public int insertPerson(PersonReqDTO personReqDTO) {
        int result = personMapper.insertPerson(personReqDTO.getPerson());
        if (result != 0) {
            String key = PersonKey.personList.getPrefix();
            boolean haskey = redisUtil.hasKey(key);
            if (haskey) {
                List personDOList = personMapper.listPersonsAll();
                PersonDO personDO2 = (PersonDO) personDOList.get(0);
                //将值添加到list中
                redisUtil.lSet(key, personDO2);
            } else {
                List personDOList = personMapper.listPersonsAll();
                // 写入缓存
                redisUtil.lSet(key, personDOList);
            }
        }
        return result;
    }

    @Override
    public PersonResDTO listPersonsAll() {
        PersonResDTO personResDTO = new PersonResDTO();
        String key = PersonKey.personList.getPrefix();
        boolean hasKey = redisUtil.hasKey(key);
        if (hasKey) {
            List personList = redisUtil.lGet(key, 0, -1);
            personResDTO.setPersonList(personList);
            logger.info("==========从缓存中获得数据=========");
            logger.info(personList.toString());
            logger.info("==============================");
        } else {
            List personList = personMapper.listPersonsAll();
            personResDTO.setPersonList(personList);
            logger.info("==========从数据表中获得数据=========");
            logger.info(personList.toString());
            logger.info("==============================");
            // 写入缓存
            redisUtil.lSet(key, personList);
        }
        return personResDTO;
    }

    @Override
    public PersonResDTO getPerson(PersonReqDTO personReqDTO) {
        PersonResDTO personResDTO = new PersonResDTO();
        String key = PersonKey.personId.getPrefix() + personReqDTO.getId();
        boolean hasKey = redisUtil.hasKey(key);
        if (hasKey) {
            PersonDO person = (PersonDO) redisUtil.get(key);
            personResDTO.setPerson(person);
            logger.info("==========从缓存中获得数据=========");
            logger.info(person.toString());
            logger.info("==============================");
        } else {
            PersonDO person = personMapper.getPerson(personReqDTO.getId());
            personResDTO.setPerson(person);
            logger.info("==========从数据表中获得数据=========");
            logger.info(person.toString());
            logger.info("==============================");
            // 写入缓存
            redisUtil.set(key, person, DEADLINE);
        }
        return personResDTO;
    }

    @Override
    @Transactional(readOnly = false)
    public int updatePerson(PersonReqDTO personReqDTO) {
        int result = personMapper.updatePerson(personReqDTO.getPerson());
        if (result != 0) {
            String key = PersonKey.personId.getPrefix() + personReqDTO.getPerson().getId();
            boolean haskey = redisUtil.hasKey(key);
            if (haskey) {
                redisUtil.del(key);
                logger.info("删除缓存中的key=========>" + key);
            }
            // 再将更新后的数据加入缓存
            PersonDO personDO = personMapper.getPerson(personReqDTO.getPerson().getId());
            if (personDO != null) {
                redisUtil.set(key, personDO, DEADLINE);
            }
            //更新缓存里的list
            List personDOList = personMapper.listPersonsAll();
            redisUtil.lSet(PersonKey.personList.getPrefix(), personDOList);
        }
        return result;
    }

    @Override
    @Transactional(readOnly = false)
    public int deletePerson(PersonReqDTO personReqDTO) {
        int result = personMapper.deletePerson(personReqDTO.getId());
        if (result != 0) {
            String key = PersonKey.personId.getPrefix() + personReqDTO.getId();
            boolean haskey = redisUtil.hasKey(key);
            if (haskey) {
                redisUtil.del(key);
                redisUtil.del(PersonKey.personList.getPrefix());
                logger.info("删除缓存中的key=========>" + key);
                logger.info("删除缓存中的key=========>" + PersonKey.personList.getPrefix());
            }
            //更新缓存里的list
            List personDOList = personMapper.listPersonsAll();
            redisUtil.lSet(PersonKey.personList.getPrefix(), personDOList);
        }
        return result;
    }

    @Override
    @Transactional(readOnly = false)
    public int deletePersons(PersonReqDTO personReqDTO) {
        int result = personMapper.deletePersons(personReqDTO.getIds());
        if (result != 0) {
            for (int i = 0; i < personReqDTO.getIds().size(); i++) {
                String key = PersonKey.personId.getPrefix() + personReqDTO.getIds().get(i);
                boolean haskey = redisUtil.hasKey(key);
                if (haskey) {
                    redisUtil.del(key);
                    logger.info("删除缓存中的key=========>" + key);
                }
            }
            //删除keylist
            redisUtil.del(PersonKey.personList.getPrefix());
            //更新缓存里的list
            List personDOList = personMapper.listPersonsAll();
            redisUtil.lSet(PersonKey.personList.getPrefix(), personDOList);
        }
        return result;
    }

    @Override
    public PersonResDTO listPersonsPage(PersonReqDTO personReqDTO) {
        PersonResDTO personResDTO = null;
        try {
            personResDTO = new PersonResDTO();
            PageHelper.startPage(personReqDTO.getPageNo(), personReqDTO.getPageSize());
            personResDTO.setPageInfo(new PageInfo<>(personMapper.listPersonsPage()));
        } catch (Exception e) {
            logger.info("分页查询用户异常！", e);
            e.printStackTrace();
        }
        return personResDTO;
    }

}
