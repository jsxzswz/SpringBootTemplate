package com.swz.pojo.vo;

import com.swz.pojo.dto.PersonDTO;

import java.io.Serializable;

/**
 * @Package: com.swz.pojo.vo
 * @Description: 显示层对象，通常是 Web 向模板渲染引擎层传输的对象
 * @author: swz
 * @date: 2018/7/23 10:11
 */
public class PersonVO implements Serializable{
    private static final long serialVersionUID = 1L;

   private PersonDTO personDTO;

    public PersonDTO getPersonDTO() {
        return personDTO;
    }

    public void setPersonDTO(PersonDTO personDTO) {
        this.personDTO = personDTO;
    }
}
