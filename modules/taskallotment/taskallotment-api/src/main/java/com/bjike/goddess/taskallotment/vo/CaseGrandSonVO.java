package com.bjike.goddess.taskallotment.vo;

import java.util.List;

/**
 * 完成情况汇总孙子
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-18 11:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CaseGrandSonVO {
    /**
     * 姓名
     */
    private String name;
    /**
     * 子对象
     */
    private List<CaseLastVO> caseLastS;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CaseLastVO> getCaseLastS() {
        return caseLastS;
    }

    public void setCaseLastS(List<CaseLastVO> caseLastS) {
        this.caseLastS = caseLastS;
    }
}
