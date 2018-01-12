package com.bjike.goddess.rotation.vo;

/**
 * 岗位轮换详细汇总
 * @Author: [caiwenxian]
 * @Date: [2018-01-09 15:33]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RotationDetailsCollectVO {

    /**
     * 地区
     */
    private String area;

    /**
     * 部门
     */
    private String department;

    /**
     * 公司发展需求管理人数
     */
    private Integer companyNeedManage;

    /**
     * 个人发展需求管理人数
     */
    private Integer personalNeedManage;

    /**
     * 管理层人数
     */
    private Integer manager;

    /**
     * 管理层应轮岗人数
     */
    private Integer managerRotation;

    /**
     * 管理层储备期人数
     */
    private Integer managerReserve;

    /**
     * 管理层储备期应轮岗人数
     */
    private Integer managerReserveRotation;

    /**
     * 管理层实习期人数
     */
    private Integer managerIntern;

    /**
     * 管理层实习期应轮岗人数
     */
    private Integer managerInternRotation;




    /**
     * 公司发展需求决策人数
     */
    private Integer companyNeedDecision;

    /**
     * 个人发展需求决策人数
     */
    private Integer personalNeedDecision;

    /**
     * 决策层人数
     */
    private Integer decision;

    /**
     * 决策层应轮岗人数
     */
    private Integer decisionRotation;

    /**
     * 决策层储备期人数
     */
    private Integer decisionReserve;

    /**
     * 决策层储备期应轮岗人数
     */
    private Integer decisionReserveRotation;


    /**
     * 决策层实习期人数
     */
    private Integer decisionIntern;

    /**
     * 决策层实习期应轮岗人数
     */
    private Integer decisionInternRotation;

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

    public Integer getCompanyNeedManage() {
        return companyNeedManage;
    }

    public void setCompanyNeedManage(Integer companyNeedManage) {
        this.companyNeedManage = companyNeedManage;
    }

    public Integer getPersonalNeedManage() {
        return personalNeedManage;
    }

    public void setPersonalNeedManage(Integer personalNeedManage) {
        this.personalNeedManage = personalNeedManage;
    }

    public Integer getManager() {
        return manager;
    }

    public void setManager(Integer manager) {
        this.manager = manager;
    }

    public Integer getManagerRotation() {
        return managerRotation;
    }

    public void setManagerRotation(Integer managerRotation) {
        this.managerRotation = managerRotation;
    }

    public Integer getManagerReserve() {
        return managerReserve;
    }

    public void setManagerReserve(Integer managerReserve) {
        this.managerReserve = managerReserve;
    }

    public Integer getManagerReserveRotation() {
        return managerReserveRotation;
    }

    public void setManagerReserveRotation(Integer managerReserveRotation) {
        this.managerReserveRotation = managerReserveRotation;
    }

    public Integer getManagerIntern() {
        return managerIntern;
    }

    public void setManagerIntern(Integer managerIntern) {
        this.managerIntern = managerIntern;
    }

    public Integer getManagerInternRotation() {
        return managerInternRotation;
    }

    public void setManagerInternRotation(Integer managerInternRotation) {
        this.managerInternRotation = managerInternRotation;
    }

    public Integer getCompanyNeedDecision() {
        return companyNeedDecision;
    }

    public void setCompanyNeedDecision(Integer companyNeedDecision) {
        this.companyNeedDecision = companyNeedDecision;
    }

    public Integer getPersonalNeedDecision() {
        return personalNeedDecision;
    }

    public void setPersonalNeedDecision(Integer personalNeedDecision) {
        this.personalNeedDecision = personalNeedDecision;
    }

    public Integer getDecision() {
        return decision;
    }

    public void setDecision(Integer decision) {
        this.decision = decision;
    }

    public Integer getDecisionRotation() {
        return decisionRotation;
    }

    public void setDecisionRotation(Integer decisionRotation) {
        this.decisionRotation = decisionRotation;
    }

    public Integer getDecisionReserve() {
        return decisionReserve;
    }

    public void setDecisionReserve(Integer decisionReserve) {
        this.decisionReserve = decisionReserve;
    }

    public Integer getDecisionReserveRotation() {
        return decisionReserveRotation;
    }

    public void setDecisionReserveRotation(Integer decisionReserveRotation) {
        this.decisionReserveRotation = decisionReserveRotation;
    }

    public Integer getDecisionIntern() {
        return decisionIntern;
    }

    public void setDecisionIntern(Integer decisionIntern) {
        this.decisionIntern = decisionIntern;
    }

    public Integer getDecisionInternRotation() {
        return decisionInternRotation;
    }

    public void setDecisionInternRotation(Integer decisionInternRotation) {
        this.decisionInternRotation = decisionInternRotation;
    }

    @Override
    public String toString() {
        return "RotationDetailsCollectBO{" +
                "area='" + area + '\'' +
                ", department='" + department + '\'' +
                ", companyNeedManage=" + companyNeedManage +
                ", personalNeedManage=" + personalNeedManage +
                ", manager=" + manager +
                ", managerRotation=" + managerRotation +
                ", managerReserve=" + managerReserve +
                ", managerReserveRotation=" + managerReserveRotation +
                ", managerIntern=" + managerIntern +
                ", managerInternRotation=" + managerInternRotation +
                ", companyNeedDecision=" + companyNeedDecision +
                ", personalNeedDecision=" + personalNeedDecision +
                ", decision=" + decision +
                ", decisionRotation=" + decisionRotation +
                ", decisionReserve=" + decisionReserve +
                ", decisionReserveRotation=" + decisionReserveRotation +
                ", decisionIntern=" + decisionIntern +
                ", decisionInternRotation=" + decisionInternRotation +
                '}';
    }
}
