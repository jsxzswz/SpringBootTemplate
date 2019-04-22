package com.swz.webservice.user.bean;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckUserResponseDTO", propOrder = {"checkUserList"})
@Data
public class CheckUserResponseDTO {

    protected List<CheckUserDTO> checkUserList;

}
