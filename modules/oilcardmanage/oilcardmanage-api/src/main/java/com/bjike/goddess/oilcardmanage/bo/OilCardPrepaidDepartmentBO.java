package com.bjike.goddess.oilcardmanage.bo;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-16 09:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OilCardPrepaidDepartmentBO {

    /**
     * 部门
     */
    private String department;

    /**
     * 基础信息
     */
    private OilCardPrepaidUseSummaryBO oilCardPrepaidUseSummary;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public OilCardPrepaidUseSummaryBO getOilCardPrepaidUseSummary() {
        return oilCardPrepaidUseSummary;
    }

    public void setOilCardPrepaidUseSummary(OilCardPrepaidUseSummaryBO oilCardPrepaidUseSummary) {
        this.oilCardPrepaidUseSummary = oilCardPrepaidUseSummary;
    }
}
