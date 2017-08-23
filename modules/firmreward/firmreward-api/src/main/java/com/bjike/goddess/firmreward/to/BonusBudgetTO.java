package com.bjike.goddess.firmreward.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 奖金预算
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BonusBudgetTO extends BaseTO {

    /**
     * 月份
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "月份不能为空")
    private String month;

    /**
     * 总预算
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "总预算不能为空")
    private Double totalBudget;

    /**
     * 奖金:经验值
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "奖金:经验值不能为空")
    private String ratio;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }
}