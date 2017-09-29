package com.bjike.goddess.businesscommission.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 业务提成权重分配表数据传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-26 11:38 ]
 * @Description: [ 业务提成权重分配表数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class QuotaDTO extends BaseDTO {
    /**
     * 内部项目名称
     */
    private String projectName;
    /**
     * 目标业务提成额
     */
    private Double aimAmount;

    /**
     * 计划业务提成额
     */
    private Double planAmount;

    /**
     * 实际业务提成额
     */
    private Double actualAmount;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getAimAmount() {
        return aimAmount;
    }

    public void setAimAmount(Double aimAmount) {
        this.aimAmount = aimAmount;
    }

    public Double getPlanAmount() {
        return planAmount;
    }

    public void setPlanAmount(Double planAmount) {
        this.planAmount = planAmount;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }
}