package com.bjike.goddess.taskallotment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 个人汇总
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-18 19:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PersonCountBO extends BaseBO {
    /**
     * 地区
     */
    private String area;
    /**
     * 子表
     */
    private List<PersonSonBO> personSonS;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<PersonSonBO> getPersonSonS() {
        return personSonS;
    }

    public void setPersonSonS(List<PersonSonBO> personSonS) {
        this.personSonS = personSonS;
    }
}
