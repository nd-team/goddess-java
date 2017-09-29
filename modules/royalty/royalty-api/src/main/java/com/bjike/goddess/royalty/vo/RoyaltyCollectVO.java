package com.bjike.goddess.royalty.vo;

/**
 * 管理提成管理汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 管理提成管理汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RoyaltyCollectVO {
    /**
     * 地区
     */
    private String area;

    /**
     * 部门
     */
    private String department;

    /**
     * 已立项
     */
    private Integer hasProject;

    /**
     * 已确定提成分配比例项目数
     */
    private Integer commission;
    /**
     * 已对赌项目数
     */
    private Integer bet;
    /**
     * 计划利润额
     */
    private Double plan;
    /**
     * 实际分值(利润额)
     */
    private Double practice;
    /**
     * 差异利润额
     */
    private Double balance;
    /**
     * 已完工项目
     */
    private Integer completed;

    /**
     * 已回款项目
     */
    private Integer back;
    /**
     * 实际所得利润额
     */
    private Double practiceProfit;
    /**
     * 综合资源部实际所得利润额
     */
    private Double resourceProfit;

    /**
     * 财务发展部实际所得利润额
     */
    private Double accountProfit;

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

    public Integer getHasProject() {
        return hasProject;
    }

    public void setHasProject(Integer hasProject) {
        this.hasProject = hasProject;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Integer getBet() {
        return bet;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }

    public Double getPlan() {
        return plan;
    }

    public void setPlan(Double plan) {
        this.plan = plan;
    }

    public Double getPractice() {
        return practice;
    }

    public void setPractice(Double practice) {
        this.practice = practice;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Integer getBack() {
        return back;
    }

    public void setBack(Integer back) {
        this.back = back;
    }

    public Double getPracticeProfit() {
        return practiceProfit;
    }

    public void setPracticeProfit(Double practiceProfit) {
        this.practiceProfit = practiceProfit;
    }

    public Double getResourceProfit() {
        return resourceProfit;
    }

    public void setResourceProfit(Double resourceProfit) {
        this.resourceProfit = resourceProfit;
    }

    public Double getAccountProfit() {
        return accountProfit;
    }

    public void setAccountProfit(Double accountProfit) {
        this.accountProfit = accountProfit;
    }
}