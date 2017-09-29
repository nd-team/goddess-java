package com.bjike.goddess.royalty.vo;

import java.util.List;

/**
 * 部门间对赌表C表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表C表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DepartmentBetCVO {
    /**
     * id
     */
    private String id;
    /**
     * 部门
     */
    private String department;

    /**
     * 目标-部门分配基础权重（%）
     */
    private Double baseWeight;

    /**
     * 目标基础得分（分值（利润额）*目标-部门分配基础权重）
     */
    private Double basesScore;
    /**
     * 计划基础得分（计划分值（利润额）*目标-部门分配基础权重）
     */
    private Double basesScorePlan;
    /**
     * 实际基础得分（实际分值（利润额）*目标-部门分配基础权重）
     */
    private Double basesScorePractice;
    /**
     * 目标制约得分
     */
    private Double restrictScore;
    /**
     * 计划制约得分
     */
    private Double restrictScorePlan;
    /**
     * 实际制约得分
     */
    private Double restrictScorePractice;

    /**
     * 体系目标总得分（目标制约得分+目标基础得分）
     */
    private Double departmentTotalScore;
    /**
     * 体系计划总得分（计划制约得分+计划基础得分）
     */
    private Double departmentTotalScorePlan;
    /**
     * 体系实际总得分（实际制约得分+实际基础得分）
     */
    private Double departmentTotalScorePractice;

    /*
     * 部门间对赌表D
     */
    private List<DepartmentBetDVO> departmentBetDBOS;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getBaseWeight() {
        return baseWeight;
    }

    public void setBaseWeight(Double baseWeight) {
        this.baseWeight = baseWeight;
    }

    public Double getBasesScore() {
        return basesScore;
    }

    public void setBasesScore(Double basesScore) {
        this.basesScore = basesScore;
    }

    public Double getBasesScorePlan() {
        return basesScorePlan;
    }

    public void setBasesScorePlan(Double basesScorePlan) {
        this.basesScorePlan = basesScorePlan;
    }

    public Double getBasesScorePractice() {
        return basesScorePractice;
    }

    public void setBasesScorePractice(Double basesScorePractice) {
        this.basesScorePractice = basesScorePractice;
    }

    public Double getRestrictScore() {
        return restrictScore;
    }

    public void setRestrictScore(Double restrictScore) {
        this.restrictScore = restrictScore;
    }

    public Double getRestrictScorePlan() {
        return restrictScorePlan;
    }

    public void setRestrictScorePlan(Double restrictScorePlan) {
        this.restrictScorePlan = restrictScorePlan;
    }

    public Double getRestrictScorePractice() {
        return restrictScorePractice;
    }

    public void setRestrictScorePractice(Double restrictScorePractice) {
        this.restrictScorePractice = restrictScorePractice;
    }

    public Double getDepartmentTotalScore() {
        return departmentTotalScore;
    }

    public void setDepartmentTotalScore(Double departmentTotalScore) {
        this.departmentTotalScore = departmentTotalScore;
    }

    public Double getDepartmentTotalScorePlan() {
        return departmentTotalScorePlan;
    }

    public void setDepartmentTotalScorePlan(Double departmentTotalScorePlan) {
        this.departmentTotalScorePlan = departmentTotalScorePlan;
    }

    public Double getDepartmentTotalScorePractice() {
        return departmentTotalScorePractice;
    }

    public void setDepartmentTotalScorePractice(Double departmentTotalScorePractice) {
        this.departmentTotalScorePractice = departmentTotalScorePractice;
    }

    public List<DepartmentBetDVO> getDepartmentBetDBOS() {
        return departmentBetDBOS;
    }

    public void setDepartmentBetDBOS(List<DepartmentBetDVO> departmentBetDBOS) {
        this.departmentBetDBOS = departmentBetDBOS;
    }
}