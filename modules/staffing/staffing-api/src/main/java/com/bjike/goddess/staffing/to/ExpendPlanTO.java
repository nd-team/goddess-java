package com.bjike.goddess.staffing.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * 人工成本计划
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 11:47 ]
 * @Description: [ 人工成本计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ExpendPlanTO extends BaseTO {

    /**
     * 时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "时间不能为空")
    private String time;

    /**
     * 计划人工成本
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "计划人工成本不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "计划人工成本不能小于0")
    private Double planExpend;

    /**
     * 人工成本计划子表信息
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "人工成本计划子表信息不能为空")
    private SonTO sonTO;

    public SonTO getSonTO() {
        return sonTO;
    }

    public void setSonTO(SonTO sonTO) {
        this.sonTO = sonTO;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getPlanExpend() {
        return planExpend;
    }

    public void setPlanExpend(Double planExpend) {
        this.planExpend = planExpend;
    }
}