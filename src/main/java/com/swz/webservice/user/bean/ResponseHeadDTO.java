
package com.swz.webservice.user.bean;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseHeadDTO", propOrder = {"status", "message"})
@Data
public class ResponseHeadDTO {

    protected int status;
    protected String message;

}
