package com.bjike.goddess.taskallotment.vo;

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
public class PersonSonVO {
    /**
     * 项目组/部门
     */
    private String depart;
    /**
     * 子表
     */
    private List<PersonLastVO> personLastS;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public List<PersonLastVO> getPersonLastS() {
        return personLastS;
    }

    public void setPersonLastS(List<PersonLastVO> personLastS) {
        this.personLastS = personLastS;
    }
}
