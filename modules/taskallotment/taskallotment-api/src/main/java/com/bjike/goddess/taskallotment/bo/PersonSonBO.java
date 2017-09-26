package com.bjike.goddess.taskallotment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 个人汇总子表
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-18 19:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PersonSonBO extends BaseBO {
    /**
     * 项目组/部门
     */
    private String depart;
    /**
     * 子表
     */
    private List<PersonLastBO> personLastS;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public List<PersonLastBO> getPersonLastS() {
        return personLastS;
    }

    public void setPersonLastS(List<PersonLastBO> personLastS) {
        this.personLastS = personLastS;
    }
}
