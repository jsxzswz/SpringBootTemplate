package com.swz.pojo.dto.request;

import com.swz.pojo.domain.PersonDO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Package: com.swz.pojo.dto.request
 * @Description: 数据传输对象， Service 或 Manager 向外传输的对象
 * @author: swz
 * @date: 2019/4/22 11:32
 */
@Data
public class PersonReqDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;//用户id

    private PersonDO person;//更新的用户对象

    private List ids;//删除的用户id列表

    private int pageNo;//页码

    private int pageSize;//每页数目

}
