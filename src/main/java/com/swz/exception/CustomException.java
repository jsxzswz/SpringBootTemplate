package com.swz.exception;

/**
 * @Package: com.swz.exception
 * @Description: 自定义异常
 * @author: swz
 * @date: 2019/4/2 16:39
 */
public class CustomException extends Exception {

    private int errorCode;
    private String errorMsg;

    public CustomException(String message) {
        this.errorMsg = message;
    }

    public CustomException(int errorCode, String message) {
        this.errorMsg = message;
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
