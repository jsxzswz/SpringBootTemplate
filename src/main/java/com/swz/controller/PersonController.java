package com.swz.controller;

import com.swz.log.target.ControllerLog;
import com.swz.pojo.ResJsonVO;
import com.swz.pojo.domain.PersonDO;
import com.swz.pojo.dto.request.PersonReqDTO;
import com.swz.pojo.vo.PersonVO;
import com.swz.service.person.PersonService;
import com.swz.utils.JsonUtil;
import com.swz.utils.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.swz.controller
 * @Description: PersonController
 * @author: swz
 * @date: 2018/7/23 10:36
 */
@RestController
@RequestMapping("/SpringBootDemo/user")
@SuppressWarnings("SpringJavaAutowiringInspection")
public class PersonController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 1 Spring Data JPA已自动为你注册bean，所以可自动注入
    @Autowired
    PersonService personService;

    @RequestMapping("/getPersonsAll")
    public String getPersonsAll() {
        ResJsonVO resJsonVO = new ResJsonVO();
        try {
            PersonVO personVO = new PersonVO();
            personVO.setPersonResDTO(personService.listPersonsAll());
            resJsonVO.setStatus(1);
            resJsonVO.setMsg("获取用户列表成功！");
            resJsonVO.setData(personVO);
        } catch (Exception e) {
            logger.info("系统错误！", e);
            e.printStackTrace();
            resJsonVO.setStatus(0);
            resJsonVO.setMsg("系统错误！");
        }
        return JsonUtil.object2Json(resJsonVO);
    }

    @RequestMapping("/getPersonsPage")
    public String getPersonsPage(@RequestBody PersonReqDTO personReqDTO) {
        ResJsonVO resJsonVO = new ResJsonVO();
        try {
            PersonVO personVO = new PersonVO();
            personVO.setPersonResDTO(personService.listPersonsPage(personReqDTO));
            resJsonVO.setStatus(1);
            resJsonVO.setMsg("获取用户列表成功！");
            resJsonVO.setData(personVO);
        } catch (Exception e) {
            logger.info("系统错误！", e);
            e.printStackTrace();
            resJsonVO.setStatus(0);
            resJsonVO.setMsg("系统错误！");
        }
        return JsonUtil.object2Json(resJsonVO);
    }

    @RequestMapping("/addPerson")
    @ControllerLog(description = "新增用户")
    public String addPerson(@RequestParam("personReqDTO") PersonReqDTO personReqDTO) {
        ResJsonVO resJsonVO = new ResJsonVO();
        try {
            PersonDO person = new PersonDO();
            person.setName("杨永信");
            person.setAge(12);
            person.setAddress("日本");
            person.setUserUuid(UuidUtil.getUUID());
            personService.insertPerson(personReqDTO);
            resJsonVO.setStatus(1);
            resJsonVO.setMsg("添加用户成功！");
        } catch (Exception e) {
            logger.info("系统错误！", e);
            e.printStackTrace();
            resJsonVO.setStatus(0);
            resJsonVO.setMsg("系统错误！");
        }
        return JsonUtil.object2Json(resJsonVO);
    }

    @RequestMapping("/getPerson")
    public String getPerson(@RequestParam("personReqDTO") PersonReqDTO personReqDTO) {
        ResJsonVO resJsonVO = new ResJsonVO();
        try {
            PersonVO personVO = new PersonVO();
            personVO.setPersonResDTO(personService.getPerson(personReqDTO));
            resJsonVO.setStatus(1);
            resJsonVO.setMsg("获取用户成功！");
            resJsonVO.setData(personVO);
        } catch (Exception e) {
            logger.info("系统错误！", e);
            e.printStackTrace();
            resJsonVO.setStatus(0);
            resJsonVO.setMsg("系统错误！");
        }
        return JsonUtil.object2Json(resJsonVO);
    }

    @RequestMapping("/updatePerson")
    @ControllerLog(description = "更新用户")
    public String updatePerson(@RequestParam("personReqDTO") PersonReqDTO personReqDTO) {
        ResJsonVO resJsonVO = new ResJsonVO();
        try {
            PersonDO person = new PersonDO();
            person.setId(2L);
            person.setName("杨永恶");
            person.setAge(20);
            personService.updatePerson(personReqDTO);
            resJsonVO.setStatus(1);
            resJsonVO.setMsg("修改用户成功！");
        } catch (Exception e) {
            logger.info("系统错误！", e);
            e.printStackTrace();
            resJsonVO.setStatus(0);
            resJsonVO.setMsg("系统错误！");
        }
        return JsonUtil.object2Json(resJsonVO);
    }

    @RequestMapping("/deletePerson")
    @ControllerLog(description = "删除用户")
    public String deletePerson(@RequestParam("personReqDTO") PersonReqDTO personReqDTO) {
        ResJsonVO resJsonVO = new ResJsonVO();
        try {
            personService.deletePerson(personReqDTO);
            resJsonVO.setStatus(1);
            resJsonVO.setMsg("删除用户成功！");
        } catch (Exception e) {
            logger.info("系统错误！", e);
            e.printStackTrace();
            resJsonVO.setStatus(0);
            resJsonVO.setMsg("系统错误！");
        }
        return JsonUtil.object2Json(resJsonVO);
    }

    @RequestMapping("/deletePersons")
    @ControllerLog(description = "批量删除用户")
    public String deletePersons(@RequestParam("personReqDTO") PersonReqDTO personReqDTO) {
        ResJsonVO resJsonVO = new ResJsonVO();
        try {
            personService.deletePersons(personReqDTO);
            resJsonVO.setStatus(1);
            resJsonVO.setMsg("删除用户成功！");
        } catch (Exception e) {
            logger.info("系统错误！", e);
            e.printStackTrace();
            resJsonVO.setStatus(0);
            resJsonVO.setMsg("系统错误！");
        }
        return JsonUtil.object2Json(resJsonVO);
    }

}
