package com.bjike.goddess.marketdevelopment.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.marketdevelopment.enums.MoneyType;

/**
 * 年计划业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 05:13 ]
 * @Description: [ 年计划业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PlanYearExportExcel extends BaseBO {

    /**
     * 更新时间
     */
    @ExcelHeader(name = "更新时间",notNull = true)
    private String modifyTime;

    /**
     * 年份
     */
    @ExcelHeader(name = "年份",notNull = true)
    private String year;

    /**
     * 全年目标金额(万元)
     */
    @ExcelHeader(name = "全年目标金额(万元)",notNull = true)
    private Double yearTargetMoney;

    /**
     * 全年计划金额(万元)
     */
    @ExcelHeader(name = "全年计划金额(万元)",notNull = true)
    private Double yearPlanMoney;

    /**
     * 全年实际金额（万元）
     */
    @ExcelHeader(name = "全年实际金额（万元）",notNull = true)
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

    //    /**
//     * 年份id
//     */
//    private String yearId;

    /**
     * 类型
     */
    @ExcelHeader(name = "类型", notNull = true)
    private MoneyType moneyType;

//    /**
//     * 月份
//     */
//    private MonthType monthType;

    /**
     * 一月
     */
    @ExcelHeader(name = "一月", notNull = true)
    private Double jan;

    /**
     * 二月
     */
    @ExcelHeader(name = "二月", notNull = true)
    private Double feb;

    /**
     * 三月
     */
    @ExcelHeader(name = "三月", notNull = true)
    private Double mar;

    /**
     * 四月
     */
    @ExcelHeader(name = "四月", notNull = true)
    private Double apr;

    /**
     * 五月
     */
    @ExcelHeader(name = "五月", notNull = true)
    private Double may;

    /**
     * 六月
     */
    @ExcelHeader(name = "六月", notNull = true)
    private Double jun;

    /**
     * 七月
     */
    @ExcelHeader(name = "七月", notNull = true)
    private Double jul;

    /**
     * 八月
     */
    @ExcelHeader(name = "八月", notNull = true)
    private Double aug;

    /**
     * 九月
     */
    @ExcelHeader(name = "九月", notNull = true)
    private Double sep;

    /**
     * 十月
     */
    @ExcelHeader(name = "十月", notNull = true)
    private Double oct;

    /**
     * 十一月
     */
    @ExcelHeader(name = "十一月", notNull = true)
    private Double nov;

    /**
     * 十二月
     */
    @ExcelHeader(name = "十二月", notNull = true)
    private Double dec;

//    /**
//     * 金额
//     */
//    private Double money;

    /**
     * 合计
     */
    @ExcelHeader(name = "合计", notNull = true)
    private String total;
//
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

    public MoneyType getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(MoneyType moneyType) {
        this.moneyType = moneyType;
    }

    public Double getJan() {
        return jan;
    }

    public void setJan(Double jan) {
        this.jan = jan;
    }

    public Double getFeb() {
        return feb;
    }

    public void setFeb(Double feb) {
        this.feb = feb;
    }

    public Double getMar() {
        return mar;
    }

    public void setMar(Double mar) {
        this.mar = mar;
    }

    public Double getApr() {
        return apr;
    }

    public void setApr(Double apr) {
        this.apr = apr;
    }

    public Double getMay() {
        return may;
    }

    public void setMay(Double may) {
        this.may = may;
    }

    public Double getJun() {
        return jun;
    }

    public void setJun(Double jun) {
        this.jun = jun;
    }

    public Double getJul() {
        return jul;
    }

    public void setJul(Double jul) {
        this.jul = jul;
    }

    public Double getAug() {
        return aug;
    }

    public void setAug(Double aug) {
        this.aug = aug;
    }

    public Double getSep() {
        return sep;
    }

    public void setSep(Double sep) {
        this.sep = sep;
    }

    public Double getOct() {
        return oct;
    }

    public void setOct(Double oct) {
        this.oct = oct;
    }

    public Double getNov() {
        return nov;
    }

    public void setNov(Double nov) {
        this.nov = nov;
    }

    public Double getDec() {
        return dec;
    }

    public void setDec(Double dec) {
        this.dec = dec;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Double getYearDiferenceMoney() {
        return yearDiferenceMoney;
    }

    public void setYearDiferenceMoney(Double yearDiferenceMoney) {
        this.yearDiferenceMoney = yearDiferenceMoney;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}