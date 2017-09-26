package com.bjike.goddess.taskallotment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

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
public class CaseGrandSonBO extends BaseBO {
    /**
     * 姓名
     */
    private String name;
    /**
     * 子对象
     */
    private List<CaseLastBO> caseLastS;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CaseLastBO> getCaseLastS() {
        return caseLastS;
    }

    public void setCaseLastS(List<CaseLastBO> caseLastS) {
        this.caseLastS = caseLastS;
    }
}
