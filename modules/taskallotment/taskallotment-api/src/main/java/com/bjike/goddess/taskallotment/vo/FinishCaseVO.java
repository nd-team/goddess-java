package com.bjike.goddess.taskallotment.vo;

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
public class FinishCaseVO {
    /**
     * 地区
     */
    private String area;
    /**
     * 子对象
     */
    private List<CaseSonVO> caseSonS;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<CaseSonVO> getCaseSonS() {
        return caseSonS;
    }

    public void setCaseSonS(List<CaseSonVO> caseSonS) {
        this.caseSonS = caseSonS;
    }
}
