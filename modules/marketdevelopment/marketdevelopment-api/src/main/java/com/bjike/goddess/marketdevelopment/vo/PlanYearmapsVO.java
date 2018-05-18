package com.bjike.goddess.marketdevelopment.vo;

import com.bjike.goddess.marketdevelopment.enums.Status;

import java.io.Serializable;
import java.util.List;

/**
 * 年计划表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 05:13 ]
 * @Description: [ 年计划表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PlanYearmapsVO implements Serializable {
    /**
     * 更新时间
     */
    private String modifyTime;

    /**
     * id
     */
    private String id;
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

    List<PlanYearBusinessVO> planYearBusinessVOs;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<PlanYearBusinessVO> getPlanYearBusinessVOs() {
        return planYearBusinessVOs;
    }

    public void setPlanYearBusinessVOs(List<PlanYearBusinessVO> planYearBusinessVOs) {
        this.planYearBusinessVOs = planYearBusinessVOs;
    }
}