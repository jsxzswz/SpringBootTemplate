package com.swz.pojo;

/**
 * @Package: com.swz.common
 * @Description: 基础返回类
 * @author: swz
 * @date: 2018/11/1 16:34
 */
public class ResJsonVO {

    //响应业务状态
    private Integer status;
    //响应消息
    private String msg;
    //响应数据
    private Object data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
