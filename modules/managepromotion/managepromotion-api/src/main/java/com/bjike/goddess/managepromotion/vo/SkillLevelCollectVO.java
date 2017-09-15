package com.bjike.goddess.managepromotion.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 技能等级晋升管理汇总
 *
 * @Author: [xiazhili]
 * @Date: [2017-09-12 18:18]
 * @Description: [ 技能等级晋升管理汇总]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SkillLevelCollectVO {
    /**
     * 地区
     */
    private String area;

    /**
     * 部门/项目组
     */
    private String department;


    /**
     * 公司发展需求人数
     */
    private Integer companyNum;

    /**
     * 个人发展需求人数
     */
    private Integer staffNum;

    /**
     * 未申请晋升人数
     */
    private Integer promotedNum;
    /**
     * 正在受理
     */
    private Integer receiving;
    /**
     * 未处理
     */
    private Integer untreated;
    /**
     * 已完成处理
     */
    private Integer completedProcessing;
    /**
     * 通过
     */
    private Integer pass;
    /**
     * 未通过
     */
    private Integer unpass;
    /**
     * 通报结果
     */
    private Integer result;
    /**
     * 未通报结果
     */
    private Integer unresult;
    /**
     * 员工发展成本（元）
     */
    private Integer staffCost;
    /**
     * 月收入（元）
     */
    private Integer monthIncome;
    /**
     * 月人工成本
     */
    private Integer monthCost;
    /**
     * 差异（收入-成本）
     */
    private Integer differences;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(Integer companyNum) {
        this.companyNum = companyNum;
    }

    public Integer getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(Integer staffNum) {
        this.staffNum = staffNum;
    }

    public Integer getPromotedNum() {
        return promotedNum;
    }

    public void setPromotedNum(Integer promotedNum) {
        this.promotedNum = promotedNum;
    }

    public Integer getReceiving() {
        return receiving;
    }

    public void setReceiving(Integer receiving) {
        this.receiving = receiving;
    }

    public Integer getUntreated() {
        return untreated;
    }

    public void setUntreated(Integer untreated) {
        this.untreated = untreated;
    }

    public Integer getCompletedProcessing() {
        return completedProcessing;
    }

    public void setCompletedProcessing(Integer completedProcessing) {
        this.completedProcessing = completedProcessing;
    }

    public Integer getPass() {
        return pass;
    }

    public void setPass(Integer pass) {
        this.pass = pass;
    }

    public Integer getUnpass() {
        return unpass;
    }

    public void setUnpass(Integer unpass) {
        this.unpass = unpass;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getUnresult() {
        return unresult;
    }

    public void setUnresult(Integer unresult) {
        this.unresult = unresult;
    }

    public Integer getStaffCost() {
        return staffCost;
    }

    public void setStaffCost(Integer staffCost) {
        this.staffCost = staffCost;
    }

    public Integer getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(Integer monthIncome) {
        this.monthIncome = monthIncome;
    }

    public Integer getMonthCost() {
        return monthCost;
    }

    public void setMonthCost(Integer monthCost) {
        this.monthCost = monthCost;
    }

    public Integer getDifferences() {
        return differences;
    }

    public void setDifferences(Integer differences) {
        this.differences = differences;
    }
}
