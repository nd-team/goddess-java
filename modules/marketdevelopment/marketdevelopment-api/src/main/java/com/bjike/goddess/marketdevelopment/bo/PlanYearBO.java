package com.bjike.goddess.marketdevelopment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.marketdevelopment.enums.Status;

import java.util.List;

/**
 * 年计划业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 05:13 ]
 * @Description: [ 年计划业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PlanYearBO extends BaseBO {

    /**
     * 更新时间
     */
    private String modifyTime;

    /**
     * 年份
     */
    private String year;

    /**
     * 全年目标金额(万元)
     */
    private Double yearTargetMoney;

    /**
     * 全年计划金额(万元)
     */
    private Double yearPlanMoney;

    /**
     * 全年实际金额（万元）
     */
    private Double yearActualMoney;

    /**
     * 全年差异金额（万元）
     */
    private Double yearDiferenceMoney;

    /**
     * 状态
     */
    private Status status;

    /**
     * 金额类型集合
     */
    private List<PlanYearType1BO> planYearTypeVOs;


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

    public Double getYearDiferenceMoney() {
        return yearDiferenceMoney;
    }

    public void setYearDiferenceMoney(Double yearDiferenceMoney) {
        this.yearDiferenceMoney = yearDiferenceMoney;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<PlanYearType1BO> getPlanYearTypeVOs() {
        return planYearTypeVOs;
    }

    public void setPlanYearTypeVOs(List<PlanYearType1BO> planYearTypeVOs) {
        this.planYearTypeVOs = planYearTypeVOs;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}