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
public class DepartMonSerperateTO implements Serializable {

    /**
     * 月份
     */
    @NotBlank(message = "月份不能为空")
    private String monthValue;
    /**
     * 目标值
     */
    @NotNull(message = "目标值不能为空")
    private Double target;
    /**
     * 分解目标值
     */
    @NotNull(message = "分解目标值不能为空")
    private Double serparateTarget;

    public String getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(String monthValue) {
        this.monthValue = monthValue;
    }

    public Double getTarget() {
        return target;
    }

    public void setTarget(Double target) {
        this.target = target;
    }

    public Double getSerparateTarget() {
        return serparateTarget;
    }

    public void setSerparateTarget(Double serparateTarget) {
        this.serparateTarget = serparateTarget;
    }
}