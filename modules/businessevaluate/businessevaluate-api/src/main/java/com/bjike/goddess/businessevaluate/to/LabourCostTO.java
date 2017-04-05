package com.bjike.goddess.businessevaluate.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 劳动成本
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 09:16 ]
 * @Description: [ 劳动成本 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LabourCostTO extends BaseTO {

    /**
     * 正常工资
     */
    private Double normalSalary;

    /**
     * 加班工资
     */
    private Double overtimeSalary;

    /**
     * 人员租赁费
     */
    private Double staffLease;

    /**
     * 其他支出
     */
    private Double another;

    /**
     * 项目信息id
     */
    private String projectInfoId;

    public Double getNormalSalary() {
        return normalSalary;
    }

    public void setNormalSalary(Double normalSalary) {
        this.normalSalary = normalSalary;
    }

    public Double getOvertimeSalary() {
        return overtimeSalary;
    }

    public void setOvertimeSalary(Double overtimeSalary) {
        this.overtimeSalary = overtimeSalary;
    }

    public Double getStaffLease() {
        return staffLease;
    }

    public void setStaffLease(Double staffLease) {
        this.staffLease = staffLease;
    }

    public Double getAnother() {
        return another;
    }

    public void setAnother(Double another) {
        this.another = another;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
    }
}