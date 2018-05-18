package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
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
public class PlanYearmapsTO extends BaseTO {
//    /**
//     * 更新时间
//     */
//    private String modifyTime;

    /**
     * 年份
     */
    @NotBlank(message = "年份不能为空", groups = {ADD.class, EDIT.class})
    private String year;

    /**
     * 全年目标金额(万元)
     */
    @NotNull(message = "全年目标金额(万元)不能为空", groups = {ADD.class, EDIT.class})
    private Double yearTargetMoney;

    /**
     * 全年计划金额(万元)
     */
    @NotNull(message = "全年计划金额(万元)不能为空", groups = {ADD.class, EDIT.class})
    private Double yearPlanMoney;

    /**
     * 全年实际金额（万元）
     */
    @NotNull(message = "全年实际金额（万元）不能为空", groups = {ADD.class, EDIT.class})
    private Double yearActualMoney;

//    /**
//     * 全年差异金额（万元）
//     */
//    @NotNull(message = "全年差异金额（万元）不能为空", groups = {ADD.class, EDIT.class})
//    private Double yearDiferenceMoney;

//    /**
//     * 状态
//     */
//    private Status status;

    List<PlanYearBusinessTO> planYearBusinessTOs;


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

    public List<PlanYearBusinessTO> getPlanYearBusinessTOs() {
        return planYearBusinessTOs;
    }

    public void setPlanYearBusinessTOs(List<PlanYearBusinessTO> planYearBusinessTOs) {
        this.planYearBusinessTOs = planYearBusinessTOs;
    }
}