package com.swz.pojo.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Package: com.swz.pojo.domain
 * @Description: 此对象与数据库表结构一一对应，通过 DAO 层向上传输数据源对象
 * @author: swz
 * @date: 2018/7/23 10:11
 */
@Data
public class PersonDO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;//id

    private String name;//姓名

    private Integer age;//年龄

    private String address;//地址

}
