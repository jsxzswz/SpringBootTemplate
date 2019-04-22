package com.swz.webservice.user.bean;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckUserDTO", propOrder = {"name", "age", "address"})
@Data
public class CheckUserDTO {

    protected String name;//姓名
    protected Integer age;//年龄
    protected String address;//地址

}
