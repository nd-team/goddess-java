package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 年计划(科目方向)
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-08 03:34 ]
 * @Description: [ 年计划(科目方向) ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PlanYearSubjectTO extends BaseTO {

//    /**
//     * 业务方向类型id
//     */
//    private String businessDataId;

    /**
     * 业务方向科目
     */
    @NotBlank(message = "业务方向科目不能为空", groups = {ADD.class, EDIT.class})
    private String subject;

    /**
     * 各业务科目同一业务类型中占比(%)
     */
    @NotNull(message = "各业务科目同一业务类型中占比(%)不能为空", groups = {ADD.class, EDIT.class})
    private Double proportion;

    /**
     * 各业务科目年度占比(%)
     */
    @NotNull(message = "各业务科目年度占比(%)不能为空", groups = {ADD.class, EDIT.class})
    private Double yearProportion;

    /**
     * 各业务科目计划金额（万元）
     */
    @NotNull(message = "各业务科目计划金额（万元）不能为空", groups = {ADD.class, EDIT.class})
    private Double planMoney;

    /**
     * 各业务科目实际金额（万元）
     */
    @NotNull(message = "各业务科目实际金额（万元）不能为空", groups = {ADD.class, EDIT.class})
    private Double actualMoney;

//    /**
//     * 各业务科目差异金额（万元）
//     */
//    @NotNull(message = "各业务科目差异金额（万元）不能为空", groups = {ADD.class, EDIT.class})
//    private Double diffMoney;

    /**
     * 现有业务可发展对象
     */
    @NotNull(message = "现有业务可发展对象不能为空", groups = {ADD.class, EDIT.class})
    private Integer deveBusiness;

    /**
     * 各科目年度总任务量
     */
    @NotNull(message = "各科目年度总任务量不能为空", groups = {ADD.class, EDIT.class})
    private Integer total;

//    /**
//     * 状态
//     */
//    private Status status;
//


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

    public Double getYearProportion() {
        return yearProportion;
    }

    public void setYearProportion(Double yearProportion) {
        this.yearProportion = yearProportion;
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

    public Integer getDeveBusiness() {
        return deveBusiness;
    }

    public void setDeveBusiness(Integer deveBusiness) {
        this.deveBusiness = deveBusiness;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}