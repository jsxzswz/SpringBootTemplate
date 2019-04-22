package com.swz.pojo.dto.response;

import com.github.pagehelper.PageInfo;
import com.swz.pojo.domain.PersonDO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Package: com.swz.pojo.dto
 * @Description: 数据传输对象， Service 或 Manager 向外传输的对象
 * @author: swz
 * @date: 2018/7/23 10:11
 */
@Data
public class PersonResDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private PersonDO person;//用户对象

    private List<PersonDO> personList;//用户列表

    private PageInfo<PersonDO> pageInfo;//用户分页

}
