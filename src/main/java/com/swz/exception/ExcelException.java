package com.swz.exception;

/**
 * @Package: com.swz.exception
 * @Description: excel异常类
 * @author: swz
 * @date: 2018/7/24 9:40
 */
public class ExcelException extends Exception{
    public ExcelException() {
        // TODO Auto-generated constructor stub
    }

    public ExcelException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public ExcelException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public ExcelException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }
}
