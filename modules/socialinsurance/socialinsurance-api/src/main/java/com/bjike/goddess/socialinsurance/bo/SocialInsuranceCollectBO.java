package com.bjike.goddess.socialinsurance.bo;

/**
 * 社会保险汇总
 * @Author: [caiwenxian]
 * @Date: [2017-12-21 17:42]
 * @Description: [　社会保险汇总 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SocialInsuranceCollectBO {

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 姓名
     */
    private String name;

    /**
     * 单位合计
     */
    private Double companyTotal;

    /**
     * 个人合计
     */
    private Double personalTotal;

    /**
     * 应缴金额
     */
    private Double amountDue;

    /**
     * 部门
     */
    private String department;

    /**
     * 地区
     */
    private String area;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCompanyTotal() {
        return companyTotal;
    }

    public void setCompanyTotal(Double companyTotal) {
        this.companyTotal = companyTotal;
    }

    public Double getPersonalTotal() {
        return personalTotal;
    }

    public void setPersonalTotal(Double personalTotal) {
        this.personalTotal = personalTotal;
    }

    public Double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(Double amountDue) {
        this.amountDue = amountDue;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
