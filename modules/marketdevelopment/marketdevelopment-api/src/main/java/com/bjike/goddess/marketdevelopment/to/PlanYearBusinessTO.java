package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 年计划(业务方向)
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-08 03:25 ]
 * @Description: [ 年计划(业务方向) ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PlanYearBusinessTO extends BaseTO {

//    /**
//     * 年份id
//     */
//    private String yearId;

    /**
     * 业务方向分类
     */
    @NotBlank(message = "业务方向分类不能为空", groups = {ADD.class, EDIT.class})
    private String businessType;

    /**
     * 工作量权重(%)
     */
    @NotNull(message = "工作量权重(%)不能为空", groups = {ADD.class, EDIT.class})
    private Double workWeight;

    /**
     * 各业务类型计划金额（万元）
     */
    @NotNull(message = "各业务类型计划金额（万元）不能为空", groups = {ADD.class, EDIT.class})
    private Double planMoney;

    /**
     * 各业务类型实际金额（万元）
     */
    @NotNull(message = "各业务类型实际金额（万元）不能为空", groups = {ADD.class, EDIT.class})
    private Double actualMoney;

//    /**
//     * 状态
//     */
//    private Status status;

    private List<PlanYearSubjectTO> planYearSubjectTOs;

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

    public List<PlanYearSubjectTO> getPlanYearSubjectTOs() {
        return planYearSubjectTOs;
    }

    public void setPlanYearSubjectTOs(List<PlanYearSubjectTO> planYearSubjectTOs) {
        this.planYearSubjectTOs = planYearSubjectTOs;
    }
}