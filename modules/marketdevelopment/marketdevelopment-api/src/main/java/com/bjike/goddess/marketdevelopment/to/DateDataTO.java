package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 日期数据
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 04:08 ]
 * @Description: [ 日期数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DateDataTO extends BaseTO {

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
     * 周期
     */
    @NotNull(message = "周期不能为空", groups = {ADD.class, EDIT.class})
    private String cycle;


//    /**
//     * 周期id
//     */
//    private String cycleId;


    /**
     * 日期集合
     */
    private List<DateDataCopyTO> dateDataCopyTOs;


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

//    public Double getVariousDifference() {
//        return variousDifference;
//    }
//
//    public void setVariousDifference(Double variousDifference) {
//        this.variousDifference = variousDifference;
//    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public List<DateDataCopyTO> getDateDataCopyTOs() {
        return dateDataCopyTOs;
    }

    public void setDateDataCopyTOs(List<DateDataCopyTO> dateDataCopyTOs) {
        this.dateDataCopyTOs = dateDataCopyTOs;
    }
}