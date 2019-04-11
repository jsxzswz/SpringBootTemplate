package com.swz.pojo.dto;

import com.swz.pojo.bo.PersonBO;

import java.io.Serializable;

/**
 * @Package: com.swz.pojo.dto
 * @Description: 数据传输对象， Service 或 Manager 向外传输的对象
 * @author: swz
 * @date: 2018/7/23 10:11
 */
public class PersonDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private PersonBO personBO;

    public PersonBO getPersonBO() {
        return personBO;
    }

    public void setPersonBO(PersonBO personBO) {
        this.personBO = personBO;
    }
}
