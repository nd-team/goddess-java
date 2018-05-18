package com.bjike.goddess.projectprocing.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 类型对象的数据
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:19 ]
 * @Description: [ 类型对象的数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PeoperDataBO extends BaseBO {

    /**
     * 负责人
     */
    private String respPerson;
    /**
     * 对应数据
     */
    private List<TypeDataBO> typeDataBOS;

    public String getRespPerson() {
        return respPerson;
    }

    public void setRespPerson(String respPerson) {
        this.respPerson = respPerson;
    }

    public List<TypeDataBO> getTypeDataBOS() {
        return typeDataBOS;
    }

    public void setTypeDataBOS(List<TypeDataBO> typeDataBOS) {
        this.typeDataBOS = typeDataBOS;
    }
}