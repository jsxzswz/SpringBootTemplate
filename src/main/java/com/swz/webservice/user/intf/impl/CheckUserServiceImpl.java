package com.swz.webservice.user.intf.impl;

import com.swz.pojo.domain.PersonDO;
import com.swz.pojo.dto.response.PersonResDTO;
import com.swz.service.person.PersonService;
import com.swz.webservice.user.bean.*;
import com.swz.webservice.user.intf.CheckUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@WebService(targetNamespace = "http://service.ccic.com/checkUser/intf", serviceName = "CheckUserService", portName = "CheckUserServiceEndpoint")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class CheckUserServiceImpl implements CheckUserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonService personService;

    @Override
    public CheckUserResponse checkUser(CheckUserRequest checkUserRequest) {

        // 获取请求对象
        // CheckUserRequest request = new CheckUserRequest();
        // 定义请求头部信息
        // RequestHeadDTO requestHead = checkUserRequest.getRequestHead();
        // 定义请求消息体
        CheckUserRequestDTO requestBody = checkUserRequest.getRequestBody();

        // 定义响应对象
        CheckUserResponse response = new CheckUserResponse();
        // 定义响应头部信息
        ResponseHeadDTO responseHead = new ResponseHeadDTO();
        // 定义响应体信息
        CheckUserResponseDTO responseBody = new CheckUserResponseDTO();

        logger.info("******************请求查询用户信息，开始*******************");
        try {
            if (requestBody == null) {
                responseHead.setStatus(0);
                responseHead.setMessage("请求体不能为空！");
                response.setResponseHead(responseHead);
            } else {
                if (requestBody.getName() == null || "".equals(requestBody.getName())) {
                    responseHead.setStatus(0);
                    responseHead.setMessage("传送的姓名不能为空！");
                    response.setResponseHead(responseHead);
                } else {
                    PersonResDTO personResDTO = personService.listPersonsAll();
                    List<PersonDO> userList = personResDTO.getPersonList();
                    if (userList != null && userList.size() != 0) {
                        responseHead.setStatus(1);
                        responseHead.setMessage("获取用户信息成功！");
                        List<CheckUserDTO> checkUserList = this.convertToCheckUserDTO(userList);
                        responseBody.setCheckUserList(checkUserList);
                        response.setResponseHead(responseHead);
                        response.setResponseBody(responseBody);
                    } else {
                        responseHead.setStatus(1);
                        responseHead.setMessage("未查到用户信息！");
                        response.setResponseHead(responseHead);
                    }
                }
            }
        } catch (Exception e) {
            logger.info("获取用户信息异常！", e);
            responseHead.setStatus(0);
            responseHead.setMessage("获取用户信息异常！");
            response.setResponseHead(responseHead);
        }
        logger.info("******************请求查询用户信息，结束*******************");
        return response;
    }

    /**
     * 转换列表
     *
     * @param userList
     * @return
     */
    private List<CheckUserDTO> convertToCheckUserDTO(List<PersonDO> userList) {
        List<CheckUserDTO> checkUserList = new ArrayList<CheckUserDTO>();
        for (PersonDO personDO : userList) {
            CheckUserDTO checkUserDTO = new CheckUserDTO();
            checkUserDTO.setName(personDO.getName());
            checkUserDTO.setAge(personDO.getAge());
            checkUserDTO.setAddress(personDO.getAddress());
            checkUserList.add(checkUserDTO);
        }
        return checkUserList;
    }

}
