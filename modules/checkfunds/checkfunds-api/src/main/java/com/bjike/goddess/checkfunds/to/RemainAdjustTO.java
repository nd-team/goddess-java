package com.bjike.goddess.checkfunds.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 余额调节
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 04:11 ]
 * @Description: [ 余额调节 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RemainAdjustTO extends BaseTO {
    public interface AddFund {
    }

    public interface RemoveFund {
    }

    public interface AddBank {
    }

    public interface RemoveBank {
    }

    /**
     * 资金流水项目
     */
    @NotBlank(groups = {RemainAdjustTO.AddFund.class, RemainAdjustTO.RemoveFund.class}, message = "资金流水项目不能为空")
    private String moneyWaterProject;

    /**
     * 资金流水金额
     */
    @NotNull(groups = {RemainAdjustTO.AddFund.class, RemainAdjustTO.RemoveFund.class}, message = "资金流水金额不能为空")
    private Double moneyWaterSum;

    /**
     * 银行流水项目
     */
    @NotBlank(groups = {RemainAdjustTO.AddBank.class, RemainAdjustTO.RemoveBank.class}, message = "银行流水项目不能为空")
    private String bankWaterProject;

    /**
     * 银行流水金额
     */
    @NotNull(groups = {RemainAdjustTO.AddBank.class, RemainAdjustTO.RemoveBank.class}, message = "银行流水金额不能为空")
    private Double bankWaterSum;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 类型
     */
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoneyWaterProject() {
        return moneyWaterProject;
    }

    public void setMoneyWaterProject(String moneyWaterProject) {
        this.moneyWaterProject = moneyWaterProject;
    }

    public Double getMoneyWaterSum() {
        return moneyWaterSum;
    }

    public void setMoneyWaterSum(Double moneyWaterSum) {
        this.moneyWaterSum = moneyWaterSum;
    }

    public String getBankWaterProject() {
        return bankWaterProject;
    }

    public void setBankWaterProject(String bankWaterProject) {
        this.bankWaterProject = bankWaterProject;
    }

    public Double getBankWaterSum() {
        return bankWaterSum;
    }

    public void setBankWaterSum(Double bankWaterSum) {
        this.bankWaterSum = bankWaterSum;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}