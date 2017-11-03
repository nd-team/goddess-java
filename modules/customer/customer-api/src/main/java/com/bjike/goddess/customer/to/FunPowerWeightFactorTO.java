package com.bjike.goddess.customer.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 职权因素层设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:37 ]
 * @Description: [ 职权因素层设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FunPowerWeightFactorTO extends BaseTO {

    /**
     * 低低比
     */
    @NotNull(message = "低低比不能为空",groups = {ADD.class,EDIT.class})
    private Double lowLow;

    /**
     * 低中比
     */
    @NotNull(message = "低中比不能为空",groups = {ADD.class,EDIT.class})
    private Double lowMedium;

    /**
     * 低高比
     */
    @NotNull(message = "低高比不能为空",groups = {ADD.class,EDIT.class})
    private Double lowHeight;

    /**
     * 中低比
     */
    @NotNull(message = "中低比不能为空",groups = {ADD.class,EDIT.class})
    private Double mediumLow;

    /**
     * 中中比
     */
    @NotNull(message = "中中比不能为空",groups = {ADD.class,EDIT.class})
    private Double mediumMedium;

    /**
     * 中高比
     */
    @NotNull(message = "中高比不能为空",groups = {ADD.class,EDIT.class})
    private Double mediumHeight;

    /**
     * 高低比
     */
    @NotNull(message = "高低比不能为空",groups = {ADD.class,EDIT.class})
    private Double heightLow;

    /**
     * 高中比
     */
    @NotNull(message = "高中比不能为空",groups = {ADD.class,EDIT.class})
    private Double heightMedium;

    /**
     * 高高比
     */
    @NotNull(message = "高高比不能为空",groups = {ADD.class,EDIT.class})
    private Double heightHeight;


    public Double getLowLow() {
        return lowLow;
    }

    public void setLowLow(Double lowLow) {
        this.lowLow = lowLow;
    }

    public Double getLowMedium() {
        return lowMedium;
    }

    public void setLowMedium(Double lowMedium) {
        this.lowMedium = lowMedium;
    }

    public Double getLowHeight() {
        return lowHeight;
    }

    public void setLowHeight(Double lowHeight) {
        this.lowHeight = lowHeight;
    }

    public Double getMediumLow() {
        return mediumLow;
    }

    public void setMediumLow(Double mediumLow) {
        this.mediumLow = mediumLow;
    }

    public Double getMediumMedium() {
        return mediumMedium;
    }

    public void setMediumMedium(Double mediumMedium) {
        this.mediumMedium = mediumMedium;
    }

    public Double getMediumHeight() {
        return mediumHeight;
    }

    public void setMediumHeight(Double mediumHeight) {
        this.mediumHeight = mediumHeight;
    }

    public Double getHeightLow() {
        return heightLow;
    }

    public void setHeightLow(Double heightLow) {
        this.heightLow = heightLow;
    }

    public Double getHeightMedium() {
        return heightMedium;
    }

    public void setHeightMedium(Double heightMedium) {
        this.heightMedium = heightMedium;
    }

    public Double getHeightHeight() {
        return heightHeight;
    }

    public void setHeightHeight(Double heightHeight) {
        this.heightHeight = heightHeight;
    }
}