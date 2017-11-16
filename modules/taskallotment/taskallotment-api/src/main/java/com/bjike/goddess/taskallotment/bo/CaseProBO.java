package com.bjike.goddess.taskallotment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 完成情况汇总项目层
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-27 10:39]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CaseProBO extends BaseBO {
    /**
     * 项目名称
     */
    private String proName;
    /**
     * 项目表
     */
    private List<CaseTableBO> caseTableBOS;

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public List<CaseTableBO> getCaseTableBOS() {
        return caseTableBOS;
    }

    public void setCaseTableBOS(List<CaseTableBO> caseTableBOS) {
        this.caseTableBOS = caseTableBOS;
    }
}
