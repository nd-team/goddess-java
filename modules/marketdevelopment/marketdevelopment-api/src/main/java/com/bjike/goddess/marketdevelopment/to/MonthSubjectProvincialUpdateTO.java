package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 月计划省市方向
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 10:29 ]
 * @Description: [ 月计划省市方向 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MonthSubjectProvincialUpdateTO extends BaseTO {

    /**
     * 年份
     */
    @NotBlank(message = "年份不能为空", groups = {ADD.class, EDIT.class})
    private String year;

    /**
     * 月份
     */
    @NotBlank(message = "月份不能为空", groups = {ADD.class, EDIT.class})
    private String month;

    /**
     * 目标金额（万元）
     */
    @NotNull(message = "目标金额（万元）不能为空", groups = {ADD.class, EDIT.class})
    private Double targetMoney;

    /**
     * 计划金额（万元）
     */
    @NotNull(message = "计划金额（万元）不能为空", groups = {ADD.class, EDIT.class})
    private Double planMoney;

    /**
     * 实际金额（万元）
     */
    @NotNull(message = "实际金额（万元）不能为空", groups = {ADD.class, EDIT.class})
    private Double actualMoney;

//    /**
//     * 差异金额（万元）
//     */
//    private Double diferenceMoney;


//    /**
//     * 月份金额id
//     */
//    private String monthMoneyId;

    /**
     * 业务方向类型id
     */
    @NotBlank(message = "业务方向类型id不能为空", groups = {ADD.class, EDIT.class})
    private String businessDataId;

    /**
     * 业务方向类型
     */
    @NotBlank(message = "业务方向类型不能为空", groups = {ADD.class, EDIT.class})
    private String businessType;

    /**
     * 工作量权重(%)
     */
    @NotNull(message = "工作量权重(%)不能为空", groups = {ADD.class, EDIT.class})
    private Double workWeight;

    /**
     * 各业务类型目标金额（万元）/年
     */
    @NotNull(message = "各业务类型目标金额（万元）/年不能为空", groups = {ADD.class, EDIT.class})
//    private Double targerMoney;
    private Double variousTargerMoney;

    /**
     * 各业务类型实际金额（万元）/年
     */
    @NotNull(message = "各业务类型实际金额（万元）/年不能为空", groups = {ADD.class, EDIT.class})
//    private Double actualMoney;
    private Double variousActualMoney;

//    /**
//     * 各业务类型差异金额（万元）/年
//     */
//    @NotNull(message = "各业务类型差异金额（万元）/年不能为空", groups = {ADD.class, EDIT.class})
////    private Double difference;
//    private Double variousDifference;


    /**
     * 省市分类id
     */
    @NotBlank(message = "省市分类id不能为空", groups = {ADD.class, EDIT.class})
    private String provincialId;

    /**
     * 省市分类
     */
    @NotBlank(message = "省市分类不能为空", groups = {ADD.class, EDIT.class})
    private String provincial;

    /**
     * 各省市工作量权重(%)
     */
    @NotBlank(message = "各省市工作量权重(%)不能为空", groups = {ADD.class, EDIT.class})
    private String provincialWeight;

//    /**
//     * 状态
//     */
//    private Status status;


    /**
     * 各地区分类id
     */
    @NotBlank(message = "各地区分类id不能为空", groups = {ADD.class, EDIT.class})
    private String provincialAreaId;

    /**
     * 各地区分类
     */
    @NotBlank(message = "各地区分类不能为空", groups = {ADD.class, EDIT.class})
    private String areaClassify;

    /**
     * 各地区工作量权重(%)
     */
    @NotBlank(message = "各地区工作量权重(%)不能为空", groups = {ADD.class, EDIT.class})
    private String areaWeight;

//    /**
//     * 状态
//     */
//    private Status status;


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(Double targetMoney) {
        this.targetMoney = targetMoney;
    }

    public Double getPlanMoney() {
        return planMoney;
    }

    public void setPlanMoney(Double planMoney) {
        this.planMoney = planMoney;
    }

    public Double getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(Double actualMoney) {
        this.actualMoney = actualMoney;
    }

    public String getBusinessDataId() {
        return businessDataId;
    }

    public void setBusinessDataId(String businessDataId) {
        this.businessDataId = businessDataId;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Double getWorkWeight() {
        return workWeight;
    }

    public void setWorkWeight(Double workWeight) {
        this.workWeight = workWeight;
    }

    public Double getVariousTargerMoney() {
        return variousTargerMoney;
    }

    public void setVariousTargerMoney(Double variousTargerMoney) {
        this.variousTargerMoney = variousTargerMoney;
    }

    public Double getVariousActualMoney() {
        return variousActualMoney;
    }

    public void setVariousActualMoney(Double variousActualMoney) {
        this.variousActualMoney = variousActualMoney;
    }

    public String getProvincialId() {
        return provincialId;
    }

    public void setProvincialId(String provincialId) {
        this.provincialId = provincialId;
    }

    public String getProvincial() {
        return provincial;
    }

    public void setProvincial(String provincial) {
        this.provincial = provincial;
    }

    public String getProvincialWeight() {
        return provincialWeight;
    }

    public void setProvincialWeight(String provincialWeight) {
        this.provincialWeight = provincialWeight;
    }

    public String getProvincialAreaId() {
        return provincialAreaId;
    }

    public void setProvincialAreaId(String provincialAreaId) {
        this.provincialAreaId = provincialAreaId;
    }

    public String getAreaClassify() {
        return areaClassify;
    }

    public void setAreaClassify(String areaClassify) {
        this.areaClassify = areaClassify;
    }

    public String getAreaWeight() {
        return areaWeight;
    }

    public void setAreaWeight(String areaWeight) {
        this.areaWeight = areaWeight;
    }
}