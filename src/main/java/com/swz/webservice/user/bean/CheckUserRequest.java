package com.swz.webservice.user.bean;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "requestBody" })
@XmlRootElement(name = "CheckUserRequest")
public class CheckUserRequest {

	@XmlElement(required = true)
	protected CheckUserRequestDTO requestBody;

	public CheckUserRequestDTO getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(CheckUserRequestDTO requestBody) {
		this.requestBody = requestBody;
	}

}
