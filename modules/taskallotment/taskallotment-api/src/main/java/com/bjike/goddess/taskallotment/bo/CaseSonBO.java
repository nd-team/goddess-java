package com.bjike.goddess.taskallotment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

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
public class CaseSonBO extends BaseBO {
    /**
     * 项目组/部门
     */
    private String depart;
    /**
     * 子对象
     */
    private List<CaseTableBO> caseTableS;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public List<CaseTableBO> getCaseTableS() {
        return caseTableS;
    }

    public void setCaseTableS(List<CaseTableBO> caseTableS) {
        this.caseTableS = caseTableS;
    }
}
