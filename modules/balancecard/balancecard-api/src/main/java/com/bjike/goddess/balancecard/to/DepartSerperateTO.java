package com.bjike.goddess.balancecard.to;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 年指标分解部门年指标
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:33 ]
 * @Description: [ 年指标分解部门年指标 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DepartSerperateTO implements Serializable{

    /**
     * 责任部门
     */
    @NotBlank(message = "责任部门不能为空")
    private String department;
    /**
     * 年度目标值
     */
    @NotNull(message = "年度目标值不能为空")
    private Double yearTarget;
    /**
     * 指标权重
     */
    @NotNull(message = "指标权重不能为空")
    private Double indexWeight;
    /**
     * 目标值
     */
    @NotNull(message = "目标值不能为空")
    private Double target;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getYearTarget() {
        return yearTarget;
    }

    public void setYearTarget(Double yearTarget) {
        this.yearTarget = yearTarget;
    }

    public Double getIndexWeight() {
        return indexWeight;
    }

    public void setIndexWeight(Double indexWeight) {
        this.indexWeight = indexWeight;
    }

    public Double getTarget() {
        return target;
    }

    public void setTarget(Double target) {
        this.target = target;
    }
}
