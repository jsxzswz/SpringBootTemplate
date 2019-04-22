package com.swz.webservice.user.intf;

import com.swz.webservice.user.bean.CheckUserRequest;
import com.swz.webservice.user.bean.CheckUserResponse;
import com.swz.webservice.user.bean.ObjectFactory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(targetNamespace = "http://service.ccic.com/checkUser/intf", name = "CheckUserService", portName = "CheckUserServiceEndpoint")
@XmlSeeAlso({ObjectFactory.class, ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface CheckUserService {

    @WebResult(name = "CheckUserResponse", targetNamespace = "http://service.ccic.com/checkUser/pojo", partName = "CheckUserResponse")
    @WebMethod(operationName = "CheckUser", action = "/checkUser")
    CheckUserResponse checkUser(
            @WebParam(partName = "CheckUserRequest", name = "CheckUserRequest", targetNamespace = "http://service.ccic.com/checkUser/pojo")
            CheckUserRequest checkUserRequest
    );
}
