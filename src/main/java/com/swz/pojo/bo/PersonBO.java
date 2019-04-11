package com.swz.pojo.bo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.swz.pojo.domain.PersonDO;

import java.io.Serializable;
import java.util.List;

/**
 * @Package: com.swz.pojo.bo
 * @Description: 业务对象， 由 Service 层输出的封装业务逻辑的对象
 * @author: swz
 * @date: 2018/11/1 9:28
 */
public class PersonBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private PersonDO personDO;

    private List<PersonDO> personDOList;

    private Page<PersonDO> personDOPage;

    private PageInfo<PersonDO> pageInfo;

    public PersonDO getPersonDO() {
        return personDO;
    }

    public void setPersonDO(PersonDO personDO) {
        this.personDO = personDO;
    }

    public List<PersonDO> getPersonDOList() {
        return personDOList;
    }

    public void setPersonDOList(List<PersonDO> personDOList) {
        this.personDOList = personDOList;
    }

    public Page<PersonDO> getPersonDOPage() {
        return personDOPage;
    }

    public void setPersonDOPage(Page<PersonDO> personDOPage) {
        this.personDOPage = personDOPage;
    }

    public PageInfo<PersonDO> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<PersonDO> pageInfo) {
        this.pageInfo = pageInfo;
    }
}
