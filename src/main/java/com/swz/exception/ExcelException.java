package com.swz.exception;

/**
 * @Package: com.swz.exception
 * @Description: excel异常类
 * @author: swz
 * @date: 2018/7/24 9:40
 */
public class ExcelException extends Exception {
    public ExcelException() {
    }

    public ExcelException(String message) {
        super(message);
    }

    public ExcelException(Throwable cause) {
        super(cause);
    }

    public ExcelException(String message, Throwable cause) {
        super(message, cause);
    }
}
