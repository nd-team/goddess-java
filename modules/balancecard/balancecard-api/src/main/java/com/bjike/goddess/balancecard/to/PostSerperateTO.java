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
public class PostSerperateTO implements Serializable {

    /**
     * 责任岗位
     */
    @NotBlank(message = "责任岗位不能为空")
    private String postName;
    /**
     * 责任人
     */
    @NotBlank(message = "责任人不能为空")
    private String charger;
    /**
     * 本月目标值
     */
    @NotNull(message = "本月目标值不能为空")
    private Double target;
    /**
     * 岗位指标权重
     */
    @NotNull(message = "岗位指标权重不能为空")
    private Double weight;
    /**
     * 岗位指标目标值
     */
    @NotNull(message = "岗位指标目标值不能为空")
    private Double serparateTarget;


    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getCharger() {
        return charger;
    }

    public void setCharger(String charger) {
        this.charger = charger;
    }

    public Double getTarget() {
        return target;
    }

    public void setTarget(Double target) {
        this.target = target;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getSerparateTarget() {
        return serparateTarget;
    }

    public void setSerparateTarget(Double serparateTarget) {
        this.serparateTarget = serparateTarget;
    }
}