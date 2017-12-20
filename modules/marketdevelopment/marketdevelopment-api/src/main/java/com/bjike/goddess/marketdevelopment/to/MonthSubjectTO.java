package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 月计划的业务科目
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-06 05:04 ]
 * @Description: [ 月计划的业务科目 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MonthSubjectTO extends BaseTO {

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


//    /**
//     * 业务方向类型id
//     */
//    private String businessDataId;

    /**
     * 业务方向科目
     */
    @NotNull(message = "业务方向科目不能为空", groups = {ADD.class, EDIT.class})
    private String subject;

//    /**
//     * 业务方向科目id
//     */
//    private String subjectDataId;

    /**
     * 各业务科目同一业务类型中占比(%)
     */
    @NotNull(message = "各业务科目同一业务类型中占比(%)不能为空", groups = {ADD.class, EDIT.class})
    private Double proportion;

//    /**
//     * 各业务科目年度占比(%)
//     */
//    @NotNull(message = "各业务科目年度占比(%)不能为空", groups = {ADD.class, EDIT.class})
//    private Double yearProportion;

    /**
     * 现有业务可发展对象
     */
    @NotNull(message = "现有业务可发展对象不能为空", groups = {ADD.class, EDIT.class})
    private Integer deveBusiness;

    /**
     * 各科目月度计划总任务量
     */
    @NotNull(message = "各科目月度计划总任务量不能为空", groups = {ADD.class, EDIT.class})
    private Integer planTotal;

    /**
     * 各科目月度实际总任务量
     */
    @NotNull(message = "各科目月度实际总任务量不能为空", groups = {ADD.class, EDIT.class})
    private Integer actualTotal;

//    /**
//     * 各科目月度差异总任务量
//     */
//    private Integer diffTotal;


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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public Integer getDeveBusiness() {
        return deveBusiness;
    }

    public void setDeveBusiness(Integer deveBusiness) {
        this.deveBusiness = deveBusiness;
    }

    public Integer getPlanTotal() {
        return planTotal;
    }

    public void setPlanTotal(Integer planTotal) {
        this.planTotal = planTotal;
    }

    public Integer getActualTotal() {
        return actualTotal;
    }

    public void setActualTotal(Integer actualTotal) {
        this.actualTotal = actualTotal;
    }
}