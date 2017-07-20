package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
 * 体系间对赌表B
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 04:52 ]
 * @Description: [ 体系间对赌表B ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SystemBetBTO extends BaseTO {

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
    private String systemBetAId;

    /**
     * 体系间对赌表C
     */
    private List<SystemBetCTO> systemBetCTOS;


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


    public Double getDepartmentTotalScore() {
        return departmentTotalScore;
    }

    public void setDepartmentTotalScore(Double departmentTotalScore) {
        this.departmentTotalScore = departmentTotalScore;
    }

    public Double getRestrictScore() {
        return restrictScore;
    }

    public void setRestrictScore(Double restrictScore) {
        this.restrictScore = restrictScore;
    }

    public String getSystemBetAId() {
        return systemBetAId;
    }

    public void setSystemBetAId(String systemBetAId) {
        this.systemBetAId = systemBetAId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<SystemBetCTO> getSystemBetCTOS() {
        return systemBetCTOS;
    }

    public void setSystemBetCTOS(List<SystemBetCTO> systemBetCTOS) {
        this.systemBetCTOS = systemBetCTOS;
    }
}