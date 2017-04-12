package com.bjike.goddess.account.bo;

import com.bjike.goddess.common.api.bo.BaseBO;


/**
 * 明细账信息业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-06 11:25 ]
 * @Description: [ 明细账信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AccountInfoBO extends BaseBO {

    /**
     * 日期
     */
    private String date;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目组/部门
     */
    private String projectGroup;

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
     * 摘要
     */
    private String digest;

    /**
     * 借方金额
     */
    private Double debitAmount;

    /**
     * 贷方金额
     */
    private Double creditAmount;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
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

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(Double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public AccountInfoBO(String area, String projectName, String projectGroup,
                                  Double debitAmount, Double creditAmount) {
        this.area = area;
        this.projectName = projectName;
        this.projectGroup = projectGroup;
        this.debitAmount = debitAmount;
        this.creditAmount = creditAmount;

    }
}