package com.swz.webservice.user.bean;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"responseHead", "responseBody"})
@XmlRootElement(name = "CheckUserResponse")
public class CheckUserResponse {

    @XmlElement(required = true)
    protected ResponseHeadDTO responseHead;

    @XmlElement(required = true)
    protected CheckUserResponseDTO responseBody;

    public ResponseHeadDTO getResponseHead() {
        return responseHead;
    }

    public void setResponseHead(ResponseHeadDTO responseHead) {
        this.responseHead = responseHead;
    }

    public CheckUserResponseDTO getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(CheckUserResponseDTO responseBody) {
        this.responseBody = responseBody;
    }

}
