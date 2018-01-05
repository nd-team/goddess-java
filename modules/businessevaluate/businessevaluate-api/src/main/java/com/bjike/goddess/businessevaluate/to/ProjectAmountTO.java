package com.bjike.goddess.businessevaluate.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 项目金额
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 项目金额 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectAmountTO extends BaseTO {

    /**
     * 成本
     */
    @NotNull(message = "成本不能为空",groups = {ADD.class, EDIT.class})
    private Double cost;

    /**
     * 预算成本
     */
    @NotNull(message = "预算成本不能为空",groups = {ADD.class, EDIT.class})
    private Double budgetCost;

    /**
     * 费用
     */
    @NotNull(message = "费用不能为空",groups = {ADD.class, EDIT.class})
    private Double fee;

    /**
     * 预算费用
     */
    @NotNull(message = "预算费用不能为空",groups = {ADD.class, EDIT.class})
    private Double budgetFee;

    /**
     * 管理费
     */
    @NotNull(message = "管理费不能为空",groups = {ADD.class, EDIT.class})
    private Double manageFee;

    /**
     * 预算管理费
     */
    @NotNull(message = "预算管理费不能为空",groups = {ADD.class, EDIT.class})
    private Double budgetManageFee;

    /**
     * 税金
     */
    @NotNull(message = "税金不能为空",groups = {ADD.class, EDIT.class})
    private Double taxes;

    /**
     * 预算税金
     */
    @NotNull(message = "预算税金不能为空",groups = {ADD.class, EDIT.class})
    private Double budgetTaxes;

    /**
     * 利润
     */
    @NotNull(message = "利润不能为空",groups = {ADD.class, EDIT.class})
    private Double profit;

    /**
     * 预算利润
     */
    @NotNull(message = "预算利润不能为空",groups = {ADD.class, EDIT.class})
    private Double budgetProfit;

    /**
     * 项目信息Id
     */
    @NotBlank(message = "项目不能为空",groups = {ADD.class, EDIT.class})
    private String projectInfoId;

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getBudgetCost() {
        return budgetCost;
    }

    public void setBudgetCost(Double budgetCost) {
        this.budgetCost = budgetCost;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getBudgetFee() {
        return budgetFee;
    }

    public void setBudgetFee(Double budgetFee) {
        this.budgetFee = budgetFee;
    }

    public Double getManageFee() {
        return manageFee;
    }

    public void setManageFee(Double manageFee) {
        this.manageFee = manageFee;
    }

    public Double getBudgetManageFee() {
        return budgetManageFee;
    }

    public void setBudgetManageFee(Double budgetManageFee) {
        this.budgetManageFee = budgetManageFee;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public Double getBudgetTaxes() {
        return budgetTaxes;
    }

    public void setBudgetTaxes(Double budgetTaxes) {
        this.budgetTaxes = budgetTaxes;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getBudgetProfit() {
        return budgetProfit;
    }

    public void setBudgetProfit(Double budgetProfit) {
        this.budgetProfit = budgetProfit;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
    }
}