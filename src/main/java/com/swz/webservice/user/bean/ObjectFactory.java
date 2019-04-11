package com.swz.webservice.user.bean;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

	private final static QName _RequestBody_QNAME = new QName(
			"http://service.ccic.com/checkUser/pojo", "requestBody");
	private final static QName _ResponseBody_QNAME = new QName(
			"http://service.ccic.com/checkUser/pojo", "responseBody");

	public ObjectFactory() {
	}

	public CheckUserRequestDTO createCheckUserRequestDTO() {
		return new CheckUserRequestDTO();
	}

	public CheckUserResponse CheckUserResponse() {
		return new CheckUserResponse();
	}

	public CheckUserRequest createCheckUserRequest() {
		return new CheckUserRequest();
	}

	public CheckUserResponseDTO createCheckUserResponseDTO() {
		return new CheckUserResponseDTO();
	}

	@XmlElementDecl(namespace = "http://service.ccic.com/checkUser/pojo", name = "requestBody")
	public JAXBElement<CheckUserRequestDTO> createRequestBody(
			CheckUserRequestDTO value) {
		return new JAXBElement<CheckUserRequestDTO>(_RequestBody_QNAME,
				CheckUserRequestDTO.class, null, value);
	}

	@XmlElementDecl(namespace = "http://service.ccic.com/checkUser/pojo", name = "responseBody")
	public JAXBElement<CheckUserResponseDTO> createRequestBody(
			CheckUserResponseDTO value) {
		return new JAXBElement<CheckUserResponseDTO>(_ResponseBody_QNAME,
				CheckUserResponseDTO.class, null, value);
	}
}
