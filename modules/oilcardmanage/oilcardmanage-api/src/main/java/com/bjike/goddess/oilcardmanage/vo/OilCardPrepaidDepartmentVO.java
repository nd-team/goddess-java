package com.bjike.goddess.oilcardmanage.vo;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-16 09:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OilCardPrepaidDepartmentVO {

    /**
     * 部门
     */
    private String department;

    /**
     * 基础信息
     */
    private OilCardPrepaidUseSummaryVO oilCardPrepaidUseSummary;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public OilCardPrepaidUseSummaryVO getOilCardPrepaidUseSummary() {
        return oilCardPrepaidUseSummary;
    }

    public void setOilCardPrepaidUseSummary(OilCardPrepaidUseSummaryVO oilCardPrepaidUseSummary) {
        this.oilCardPrepaidUseSummary = oilCardPrepaidUseSummary;
    }
}
