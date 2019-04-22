package com.swz.webservice.user.bean;

import lombok.Data;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"responseHead", "responseBody"})
@XmlRootElement(name = "CheckUserResponse")
@Data
public class CheckUserResponse {

    @XmlElement(required = true)
    protected ResponseHeadDTO responseHead;

    @XmlElement(required = true)
    protected CheckUserResponseDTO responseBody;

}
