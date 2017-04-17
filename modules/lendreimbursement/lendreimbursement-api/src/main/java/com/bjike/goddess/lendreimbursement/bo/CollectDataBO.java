package com.bjike.goddess.lendreimbursement.bo;

import java.io.Serializable;
import java.time.LocalDate;

/** 汇总数据
 * @Author: [tanghaixiang]
 * @Date: [2017-04-10 11:57]
 * @Description: [汇总数据]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectDataBO implements Serializable {

    /**
     * 借款人
     */
    private String lender;
    /**
     * 报销日期
     */
    private LocalDate reimDate;
    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 一级科目
     */
    private String firstSubject;

    /**
     * 二级科目
     */
    private String secondSubject;

    /**
     * 三级科目
     */
    private String thirdSubject;

    /**
     * 是否付款（是/否）
     */
    private String payCondition;

    /**
     * 金额
     */
    private Double money;

    public String getLender() {
        return lender;
    }

    public void setLender(String lender) {
        this.lender = lender;
    }

    public LocalDate getReimDate() {
        return reimDate;
    }

    public void setReimDate(LocalDate reimDate) {
        this.reimDate = reimDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }

    public String getSecondSubject() {
        return secondSubject;
    }

    public void setSecondSubject(String secondSubject) {
        this.secondSubject = secondSubject;
    }

    public String getThirdSubject() {
        return thirdSubject;
    }

    public void setThirdSubject(String thirdSubject) {
        this.thirdSubject = thirdSubject;
    }

    public String getPayCondition() {
        return payCondition;
    }

    public void setPayCondition(String payCondition) {
        this.payCondition = payCondition;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
