package com.bjike.goddess.royalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 体系间对赌表B业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 04:52 ]
 * @Description: [ 体系间对赌表B业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SystemBetBBO extends BaseBO {

    /**
     * 体系
     */
    private String system;

    /**
     * 目标-部门分配基础权重（%）
     */
    private Double baseWeight;


    /**
     * 基础得分（分值*目标-部门分配基础权重）
     */
    private Double basesScore;


    /**
     * 制约得分
     */
    private Double restrictScore;
    /**
     * 部门
     */
    private String department;

    /**
     * 部门总得分（制约得分+基础得分）
     */
    private Double departmentTotalScore;
    /**
     * 体系间对赌表A
     */
    private SystemBetABO systemBetABO;
    /**
     * 体系间对赌C
     */
    private List<SystemBetCBO> systemBetCBOS;

    public List<SystemBetCBO> getSystemBetCBOS() {
        return systemBetCBOS;
    }

    public void setSystemBetCBOS(List<SystemBetCBO> systemBetCBOS) {
        this.systemBetCBOS = systemBetCBOS;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public SystemBetABO getSystemBetABO() {
        return systemBetABO;
    }

    public void setSystemBetABO(SystemBetABO systemBetABO) {
        this.systemBetABO = systemBetABO;
    }
}