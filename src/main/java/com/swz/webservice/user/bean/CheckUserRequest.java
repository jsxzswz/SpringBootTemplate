package com.swz.webservice.user.bean;

import lombok.Data;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "requestBody" })
@XmlRootElement(name = "CheckUserRequest")
@Data
public class CheckUserRequest {

	@XmlElement(required = true)
	protected CheckUserRequestDTO requestBody;

}
