package com.swz.log.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Package: com.swz.log.domain
 * @Description: 日志实体类
 * @author: swz
 * @date: 2019/3/29 9:30
 */
@Data
public class Log implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;           //日志主键
    private String type;         //日志类型
    private String title;        //日志标题
    private String remoteAddr;   //请求IP地址
    private String requestUri;   //URI
    private String method;       //请求方式
    private String params;       //提交参数
    private String resultParams; //返回参数
    private String excep;    //异常
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime operateDate;    //开始时间
    private Long timeout;      //请求时长
    private String userId;       //用户ID

}

