package com.swz.webservice.user.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckUserResponseDTO", propOrder = {"checkUserList"})
public class CheckUserResponseDTO {

    protected List<CheckUserDTO> checkUserList;

    public List<CheckUserDTO> getCheckUserList() {
        return checkUserList;
    }

    public void setCheckUserList(List<CheckUserDTO> checkUserList) {
        this.checkUserList = checkUserList;
    }

}
