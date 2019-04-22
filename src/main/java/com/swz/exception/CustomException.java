package com.swz.exception;

import lombok.Data;

/**
 * @Package: com.swz.exception
 * @Description: 自定义异常
 * @author: swz
 * @date: 2019/4/2 16:39
 */
@Data
public class CustomException extends Exception {

    private int errorCode;
    private String errorMsg;

}
