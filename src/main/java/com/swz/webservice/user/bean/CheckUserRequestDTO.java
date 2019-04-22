package com.swz.webservice.user.bean;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckUserRequestDTO", propOrder = {"name"})
@Data
public class CheckUserRequestDTO {

    @XmlElement(required = true)
    protected String name;

}
