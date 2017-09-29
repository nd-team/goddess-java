package com.bjike.goddess.businesscommission.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 业务提成分配比例表子表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-23 11:26 ]
 * @Description: [ 业务提成分配比例表子表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProportionRatioTO extends BaseTO {

    /**
     * 影响因素
     */
    @NotNull(groups = {WeightAllotTO.TestAdd.class}, message = "影响因素不能为空")
    private String factors;

    /**
     * 类型影响因素权重
     */
    @NotNull(groups = {WeightAllotTO.TestAdd.class}, message = "类型影响因素权重不能为空")
    private Double typeFactors;

    /**
     * 信息提供人
     */
    @NotNull(groups = {WeightAllotTO.TestAdd.class}, message = "信息提供人不能为空")
    private Double messageProportion;

    /**
     * 介绍关系揽接
     */
    @NotNull(groups = {WeightAllotTO.TestAdd.class}, message = "介绍关系揽接不能为空")
    private Double businessProportion;

    /**
     * 出面接洽
     */
    @NotNull(groups = {WeightAllotTO.TestAdd.class}, message = "出面接洽不能为空")
    private Double talkProportion;

    /**
     * 维护
     */
    @NotNull(groups = {WeightAllotTO.TestAdd.class}, message = "维护不能为空")
    private Double maintainProportion;

    public String getFactors() {
        return factors;
    }

    public void setFactors(String factors) {
        this.factors = factors;
    }

    public Double getTypeFactors() {
        return typeFactors;
    }

    public void setTypeFactors(Double typeFactors) {
        this.typeFactors = typeFactors;
    }

    public Double getMessageProportion() {
        return messageProportion;
    }

    public void setMessageProportion(Double messageProportion) {
        this.messageProportion = messageProportion;
    }

    public Double getBusinessProportion() {
        return businessProportion;
    }

    public void setBusinessProportion(Double businessProportion) {
        this.businessProportion = businessProportion;
    }

    public Double getTalkProportion() {
        return talkProportion;
    }

    public void setTalkProportion(Double talkProportion) {
        this.talkProportion = talkProportion;
    }

    public Double getMaintainProportion() {
        return maintainProportion;
    }

    public void setMaintainProportion(Double maintainProportion) {
        this.maintainProportion = maintainProportion;
    }
}