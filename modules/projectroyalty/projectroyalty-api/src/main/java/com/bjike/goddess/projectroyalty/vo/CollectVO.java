package com.bjike.goddess.projectroyalty.vo;

/**
 * 汇总
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:48 ]
 * @Description: [ 汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectVO {

    /**
     * ID
     */
    private String id;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String department;

    /**
     * 已立项
     */
    private Boolean isBuild;

    /**
     * 已确定提成分配比例数
     */
    private Long num;

    /**
     * 目标利润额
     */
    private Double aimProfit;

    /**
     * 计划利润额
     */
    private Double planProfit;

    /**
     * 实际利润额
     */
    private Double actualProfit;

    /**
     * 已完工项目
     */
    private String completeProject;

    /**
     * 已回款项目
     */
    private String project;

    /**
     * 实际干股和公司预留利润提成额
     */
    private Double amountProfit;

    /**
     * 实际业务提成额
     */
    private Double business;

    /**
     * 实际管理提成额
     */
    private Double menage;

    /**
     * 实际资金方提成额
     */
    private Double capital;

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

    public Boolean getBuild() {
        return isBuild;
    }

    public void setBuild(Boolean build) {
        isBuild = build;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Double getAimProfit() {
        return aimProfit;
    }

    public void setAimProfit(Double aimProfit) {
        this.aimProfit = aimProfit;
    }

    public Double getPlanProfit() {
        return planProfit;
    }

    public void setPlanProfit(Double planProfit) {
        this.planProfit = planProfit;
    }

    public Double getActualProfit() {
        return actualProfit;
    }

    public void setActualProfit(Double actualProfit) {
        this.actualProfit = actualProfit;
    }

    public String getCompleteProject() {
        return completeProject;
    }

    public void setCompleteProject(String completeProject) {
        this.completeProject = completeProject;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Double getAmountProfit() {
        return amountProfit;
    }

    public void setAmountProfit(Double amountProfit) {
        this.amountProfit = amountProfit;
    }

    public Double getBusiness() {
        return business;
    }

    public void setBusiness(Double business) {
        this.business = business;
    }

    public Double getMenage() {
        return menage;
    }

    public void setMenage(Double menage) {
        this.menage = menage;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}