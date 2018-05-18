package com.bjike.goddess.marketdevelopment.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 年计划表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 05:13 ]
 * @Description: [ 年计划表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PlanYearmapsExportExcel extends BaseTO {
    /**
     * 更新时间
     */
    @ExcelHeader(name = "更新时间", notNull = true)
    private String modifyTime;

    /**
     * 年份
     */
    @ExcelHeader(name = "年份", notNull = true)
    private String year;

    /**
     * 全年目标金额(万元)
     */
    @ExcelHeader(name = "全年目标金额(万元)", notNull = true)
    private Double yearTargetMoney;

    /**
     * 全年计划金额(万元)
     */
    @ExcelHeader(name = "全年计划金额(万元)", notNull = true)
    private Double yearPlanMoney;

    /**
     * 全年实际金额（万元）
     */
    @ExcelHeader(name = "全年实际金额（万元）", notNull = true)
    private Double yearActualMoney;

    /**
     * 全年差异金额（万元）
     */
    @ExcelHeader(name = "全年差异金额（万元）", notNull = true)
    private Double yearDiferenceMoney;

//    /**
//     * 状态
//     */
//    private Status status;

    /**
     //     * 年份id
     //     */
//    private String yearId;

    /**
     * 业务方向分类
     */
    @ExcelHeader(name = "业务方向分类", notNull = true)
    private String businessType;

    /**
     * 工作量权重(%)
     */
    @ExcelHeader(name = "工作量权重(%)", notNull = true)
    private Double workWeight;

    /**
     * 各业务类型计划金额（万元）
     */
    @ExcelHeader(name = "各业务类型计划金额（万元）", notNull = true)
    private Double variousPlanMoney;

    /**
     * 各业务类型实际金额（万元）
     */
    @ExcelHeader(name = "各业务类型实际金额（万元）", notNull = true)
    private Double variousActualMoney;

//    /**
//     * 状态
//     */
//    private Status status;


    /**
     //     * 业务方向类型id
     //     */
//    private String businessDataId;

    /**
     * 业务方向科目
     */
    @ExcelHeader(name = "业务方向科目", notNull = true)
    private String subject;

    /**
     * 各业务科目同一业务类型中占比(%)
     */
    @ExcelHeader(name = "各业务科目同一业务类型中占比(%)", notNull = true)
    private Double proportion;

    /**
     * 各业务科目年度占比(%)
     */
    @ExcelHeader(name = "各业务科目年度占比(%)", notNull = true)
    private Double yearProportion;

    /**
     * 各业务科目计划金额（万元）
     */
    @ExcelHeader(name = "各业务科目计划金额（万元）", notNull = true)
    private Double planMoney;

    /**
     * 各业务科目实际金额（万元）
     */
    @ExcelHeader(name = "各业务科目实际金额（万元）", notNull = true)
    private Double actualMoney;

    /**
     * 各业务科目差异金额（万元）
     */
    @ExcelHeader(name = "各业务科目差异金额（万元）", notNull = true)
    private Double diffMoney;

    /**
     * 现有业务可发展对象
     */
    @ExcelHeader(name = "现有业务可发展对象", notNull = true)
    private Integer deveBusiness;

    /**
     * 各科目年度总任务量
     */
    @ExcelHeader(name = "各科目年度总任务量", notNull = true)
    private Integer total;

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

    public Double getYearTargetMoney() {
        return yearTargetMoney;
    }

    public void setYearTargetMoney(Double yearTargetMoney) {
        this.yearTargetMoney = yearTargetMoney;
    }

    public Double getYearPlanMoney() {
        return yearPlanMoney;
    }

    public void setYearPlanMoney(Double yearPlanMoney) {
        this.yearPlanMoney = yearPlanMoney;
    }

    public Double getYearActualMoney() {
        return yearActualMoney;
    }

    public void setYearActualMoney(Double yearActualMoney) {
        this.yearActualMoney = yearActualMoney;
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

    public Double getVariousPlanMoney() {
        return variousPlanMoney;
    }

    public void setVariousPlanMoney(Double variousPlanMoney) {
        this.variousPlanMoney = variousPlanMoney;
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