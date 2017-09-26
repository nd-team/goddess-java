package com.bjike.goddess.taskallotment.vo;

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
public class PersonCountVO {
    /**
     * 地区
     */
    private String area;
    /**
     * 子表
     */
    private List<PersonSonVO> personSonS;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<PersonSonVO> getPersonSonS() {
        return personSonS;
    }

    public void setPersonSonS(List<PersonSonVO> personSonS) {
        this.personSonS = personSonS;
    }
}
