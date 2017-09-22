package com.bjike.goddess.salarymanage.vo;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-19 14:06]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ResultWorkAgeVO {
    /**
     * 工作年限
     */
    private String workAge;

    /**
     * 基础信息
     */
    private SalaryCalculateResultVO salaryCalculateResult;

    public String getWorkAge() {
        return workAge;
    }

    public void setWorkAge(String workAge) {
        this.workAge = workAge;
    }

    public SalaryCalculateResultVO getSalaryCalculateResult() {
        return salaryCalculateResult;
    }

    public void setSalaryCalculateResult(SalaryCalculateResultVO salaryCalculateResult) {
        this.salaryCalculateResult = salaryCalculateResult;
    }
}
