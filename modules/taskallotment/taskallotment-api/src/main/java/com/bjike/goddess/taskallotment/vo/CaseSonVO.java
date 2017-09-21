package com.bjike.goddess.taskallotment.vo;

import java.util.List;

/**
 * 完成情况汇总儿子
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-18 10:59]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CaseSonVO {
    /**
     * 项目组/部门
     */
    private String depart;
    /**
     * 子对象
     */
    private List<CaseGrandSonVO> caseGrandSonS;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public List<CaseGrandSonVO> getCaseGrandSonS() {
        return caseGrandSonS;
    }

    public void setCaseGrandSonS(List<CaseGrandSonVO> caseGrandSonS) {
        this.caseGrandSonS = caseGrandSonS;
    }
}
