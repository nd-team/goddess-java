package com.bjike.goddess.royalty.vo;

import java.util.List;

/**
 * 部门间对赌表B表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表B表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DepartmentBetBVO {

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
     * 基础得分（部门总得分*目标-部门分配基础权重）
     */
    private Double basesScore;
    /**
     * 制约得分
     */
    private Double restrictScore;

    /**
     * 部门总得分（制约得分+基础得分）
     */
    private Double departmentTotalScore;
    /**
     * 部门间对赌表C
     */
    private List<DepartmentBetCVO> departmentBetCVOS;


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

    public Double getRestrictScore() {
        return restrictScore;
    }

    public void setRestrictScore(Double restrictScore) {
        this.restrictScore = restrictScore;
    }

    public Double getDepartmentTotalScore() {
        return departmentTotalScore;
    }

    public void setDepartmentTotalScore(Double departmentTotalScore) {
        this.departmentTotalScore = departmentTotalScore;
    }


    public List<DepartmentBetCVO> getDepartmentBetCVOS() {
        return departmentBetCVOS;
    }

    public void setDepartmentBetCVOS(List<DepartmentBetCVO> departmentBetCVOS) {
        this.departmentBetCVOS = departmentBetCVOS;
    }
}