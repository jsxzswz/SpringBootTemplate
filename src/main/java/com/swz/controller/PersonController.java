package com.swz.controller;

import com.swz.pojo.ResJsonVO;
import com.swz.log.target.ControllerLog;
import com.swz.pojo.dto.PersonDTO;
import com.swz.pojo.vo.PersonVO;
import com.swz.service.person.PersonService;
import com.swz.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/all")
    public String sort() {
        ResJsonVO resJsonVO = new ResJsonVO();
        PersonVO personVO = new PersonVO();
        personVO.setPersonDTO(personService.findAll());
        resJsonVO.setStatus(1);
        resJsonVO.setMsg("查询用户成功！");
        resJsonVO.setData(personVO);
        return JsonUtil.object2Json(resJsonVO);
    }

    @ControllerLog(description = "分页查询用户")
    @RequestMapping("/page/{pageNo}")
    public String page(@PathVariable("pageNo") int pageNo) {
        ResJsonVO resJsonVO = new ResJsonVO();
        try {
            String pageNo2 = String.valueOf(pageNo);
            if (pageNo2 == null || "".equals(pageNo2)) {
                resJsonVO.setStatus(0);
                resJsonVO.setMsg("请输入页码！");
            } else {
                PersonVO personVO = new PersonVO();
                PersonDTO personDTO = personService.findByPage(pageNo, 6);
                if (personDTO == null) {
                    resJsonVO.setStatus(0);
                    resJsonVO.setMsg("分页查询系统错误！");
                } else {
                    personVO.setPersonDTO(personDTO);
                    if (personVO.getPersonDTO().getPersonBO() == null) {
                        resJsonVO.setStatus(1);
                        resJsonVO.setMsg("用户信息不存在！");
                    } else {
                        resJsonVO.setStatus(1);
                        resJsonVO.setMsg("分页查询用户成功！");
                        resJsonVO.setData(personVO);
                    }
                }
            }
        } catch (Exception e) {
            logger.info("系统错误！", e);
            e.printStackTrace();
            resJsonVO.setStatus(0);
            resJsonVO.setMsg("系统错误！");
        }
        return JsonUtil.object2Json(resJsonVO);
    }

    @ControllerLog(description = "查询用户")
    @RequestMapping("/findUser")
    public String findUser(@RequestParam("userId") Long id) {
        ResJsonVO resJsonVO = new ResJsonVO();
        try {
            if (id == null) {
                resJsonVO.setStatus(0);
                resJsonVO.setMsg("请输入用户id！");
            } else {
                PersonVO personVO = new PersonVO();
                personVO.setPersonDTO(personService.findById(id));
                if (personVO.getPersonDTO().getPersonBO().getPersonDO() == null) {
                    resJsonVO.setStatus(1);
                    resJsonVO.setMsg("用户信息不存在！");
                } else {
                    resJsonVO.setStatus(1);
                    resJsonVO.setMsg("查询用户成功！");
                    resJsonVO.setData(personVO.getPersonDTO().getPersonBO());
                }
            }
        } catch (Exception e) {
            logger.info("系统错误！", e);
            e.printStackTrace();
            resJsonVO.setStatus(0);
            resJsonVO.setMsg("系统错误！");
        }
        return JsonUtil.object2Json(resJsonVO);
    }


}
