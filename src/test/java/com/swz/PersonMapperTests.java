package com.swz;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.swz.pojo.ResJsonVO;
import com.swz.pojo.bo.PersonBO;
import com.swz.pojo.domain.PersonDO;
import com.swz.pojo.dto.PersonDTO;
import com.swz.service.person.PersonService;
import com.swz.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Package: com.swz
 * @Description: Person测试
 * @author: swz
 * @date: 2018/10/16 15:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonMapperTests {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonService personService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private PersonBO personBO =null;

    private PersonDO personDO = null;

    @Before
    public void testInsert() {
        personBO = new PersonBO();
        personDO = new PersonDO();
        personDO.setName("测试");
        personDO.setAddress("address");
        personDO.setAge(10);
        personBO.setPersonDO(personDO);
        personService.insert(personBO);

        Assert.assertNotNull(personDO.getId());
        logger.debug(JSON.toJSONString(personDO));
    }

    @Test
    public void testFindAll() {
        PersonDTO personDTO = personService.findAll();

        Assert.assertNotNull(personDTO);
        logger.debug(JSON.toJSONString(personDTO));
    }

    @Test
    public void testFindByPage() {
        ResJsonVO resJsonVO = new ResJsonVO();
        PersonDTO personDTO = personService.findByPage(1, 8);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<PersonDO> pageInfo = personDTO.getPersonBO().getPageInfo();
        Assert.assertNotNull(pageInfo);
        resJsonVO.setStatus(1);
        resJsonVO.setMsg("分页查询用户成功！");
        resJsonVO.setData(pageInfo);
        logger.debug(JsonUtil.object2Json(resJsonVO));
    }

    // 测试mybatis缓存
    @Test
    public void testCache() {
        long begin = System.currentTimeMillis();
        PersonDTO personDTO = personService.findAll();
        long ing = System.currentTimeMillis();
        logger.debug("第一次请求时间：" + (ing - begin) + "ms");
        personService.findAll();
        long end = System.currentTimeMillis();
        personService.findAll();
        logger.debug("第二次请求时间:" + (end - ing) + "ms, list: "+JSON.toJSONString(personDTO));
        personDO.setAge(20);
        personBO = new PersonBO();
        personBO.setPersonDO(personDO);
        personService.update(personBO);
        long begin2 = System.currentTimeMillis();
        PersonDTO personDTO2 = personService.findAll();
        long end2= System.currentTimeMillis();
        logger.debug("第三次请求时间:" + (end2 - begin2) + "ms, list: "+JSON.toJSONString(personDTO2));
    }

    // 测试Redis存储和获取一个List
    @SuppressWarnings("unchecked")
    @Test
    public void testRedisCacheSetList() {
        List<PersonDO> personDOs = new ArrayList<>();
        personDOs.add(personDO);
        personDOs.add(personDO);
        personDOs.add(personDO);
        redisTemplate.opsForValue().set(personDO.getId() + "", personDOs, 2, TimeUnit.MINUTES);
        personDOs = (List<PersonDO>) redisTemplate.opsForValue().get(personDO.getId() + "");
        logger.debug(JSON.toJSONString(personDOs));
    }

    // 测试Redis存储和获取一个Object
    @Test
    public void testRedisCacheSetObject() {
        redisTemplate.opsForValue().set(personDO.getId() + "", personDO, 2, TimeUnit.DAYS);
        Object p = redisTemplate.opsForValue().get(personDO.getId() + "");
        if (p instanceof PersonDO) {
            PersonDO personDO1 = (PersonDO) p;
            logger.debug(JSON.toJSONString(personDO1));
        }
    }

}
