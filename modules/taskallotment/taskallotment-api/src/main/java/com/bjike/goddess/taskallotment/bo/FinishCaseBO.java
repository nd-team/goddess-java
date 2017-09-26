package com.bjike.goddess.taskallotment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 完成情况汇总
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-18 10:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FinishCaseBO extends BaseBO {
    /**
     * 地区
     */
    private String area;
    /**
     * 子对象
     */
    private List<CaseSonBO> caseSonS;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<CaseSonBO> getCaseSonS() {
        return caseSonS;
    }

    public void setCaseSonS(List<CaseSonBO> caseSonS) {
        this.caseSonS = caseSonS;
    }
}
