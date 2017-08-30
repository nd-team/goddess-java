package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
 * 部门间对赌表B
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表B ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DepartmentBetBTO extends BaseTO {

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
    private List<DepartmentBetCTO> departmentBetCTOS;

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


    public List<DepartmentBetCTO> getDepartmentBetCTOS() {
        return departmentBetCTOS;
    }

    public void setDepartmentBetCTOS(List<DepartmentBetCTO> departmentBetCTOS) {
        this.departmentBetCTOS = departmentBetCTOS;
    }
}