package com.bjike.goddess.costdetail.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 成本明细业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 成本明细业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostDetailsBO extends BaseBO {

    /**
     * 日期
     */
    private String costTime;

    /**
     * 部门
     */
    private String department;

    /**
     * 初期余额合计
     */
    private Double beginBalanceSum;

    /**
     * 初期余额十日
     */
    private Double beginBalanceTen;

    /**
     * 初期余额十五日
     */
    private Double beginBalanceFift;

    /**
     * 初期余额二十日
     */
    private Double beginBalanceTwtenty;

    /**
     * 初期余额三十日
     */
    private Double beginBalanceThirty;

    /**
     * 营业成本合计
     */
    private Double operatingSum;

    /**
     * 营业成本十日
     */
    private Double operatingTen;

    /**
     * 营业成本十五日
     */
    private Double operatingFift;

    /**
     * 营业成本二十日
     */
    private Double operatingTwtenty;

    /**
     * 营业成本三十日
     */
    private Double operatingThirty;

    /**
     * 劳务成本合计
     */
    private Double laborCostSum;

    /**
     * 劳务成本十日
     */
    private Double laborCostTen;

    /**
     * 劳务成本十五日
     */
    private Double laborCostFift;

    /**
     * 劳务成本二十日
     */
    private Double laborCostTwtenty;

    /**
     * 劳务成本三十日
     */
    private Double laborCostThirty;

    /**
     * 应交税金合计
     */
    private Double payableTaxSum;

    /**
     * 应交税金十日
     */
    private Double payableTaxTen;

    /**
     * 应交税金十五日
     */
    private Double payableTaxFift;

    /**
     * 应交税金二十日
     */
    private Double payableTaxTwtenty;

    /**
     * 应交税金三十日
     */
    private Double payableTaxThirty;

    /**
     * 公司借入合计
     */
    private Double companyBorrowedSum;

    /**
     * 公司借入十日
     */
    private Double companyBorrowedTen;

    /**
     * 公司借入十五日
     */
    private Double companyBorrowedFift;

    /**
     * 公司借入二十日
     */
    private Double companyBorrowedTwtenty;

    /**
     * 公司借入三十日
     */
    private Double companyBorrowedThirty;

    /**
     * 实收资本合计
     */
    private Double paidCapitalSum;

    /**
     * 实收资本十日
     */
    private Double paidCapitalTen;

    /**
     * 实收资本十五日
     */
    private Double paidCapitalFift;

    /**
     * 实收资本二十日
     */
    private Double paidCapitalTwtenty;

    /**
     * 实收资本三十日
     */
    private Double paidCapitalThirty;

    /**
     * 公司借出合计
     */
    private Double companyLendSum;

    /**
     * 公司借出十日
     */
    private Double companyLendTen;

    /**
     * 公司借出十五日
     */
    private Double companyLendFift;

    /**
     * 公司借出二十日
     */
    private Double companyLendTwtenty;

    /**
     * 公司借出三十日
     */
    private Double companyLendThirty;

    /**
     * 主营业务收入合计
     */
    private Double businessIncomeSum;

    /**
     * 主营业务收入十日
     */
    private Double businessIncomeTen;

    /**
     * 主营业务收入十五日
     */
    private Double businessIncomeFift;

    /**
     * 主营业务收入二十日
     */
    private Double businessIncomeTwtenty;

    /**
     * 主营业务收入三十日
     */
    private Double businessIncomeThirty;

    /**
     * 预估应收账款合计
     */
    private Double forecastAccountSum;

    /**
     * 预估应收账款十日
     */
    private Double forecastAccountTen;

    /**
     * 预估应收账款十五日
     */
    private Double forecastAccountFift;

    /**
     * 预估应收账款二十日
     */
    private Double forecastAccountTwtenty;

    /**
     * 预估应收账款三十日
     */
    private Double forecastAccountThirty;

    /**
     * 实际资金缺口合计
     */
    private Double actualGapSum;

    /**
     * 实际资金缺口十日
     */
    private Double actualGapTen;

    /**
     * 实际资金缺口十五日
     */
    private Double actualGapFift;

    /**
     * 实际资金缺口二十日
     */
    private Double actualGapTwtenty;

    /**
     * 实际资金缺口三十日
     */
    private Double actualGapThirty;

    /**
     * 按时回款预估结余资金合计
     */
    private Double forecastBalanceMoSum;

    /**
     * 按时回款预估结余资金十日
     */
    private Double forecastBalanceMoGapTen;

    /**
     * 按时回款预估结余资金十五日
     */
    private Double forecastBalanceMoGapFift;

    /**
     * 按时回款预估结余资金二十日
     */
    private Double forecastBalanceMoTwtenty;

    /**
     * 按时回款预估结余资金三十日
     */
    private Double forecastBalanceMoThirty;


    public String getCostTime() {
        return costTime;
    }

    public void setCostTime(String costTime) {
        this.costTime = costTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getBeginBalanceSum() {
        return beginBalanceSum;
    }

    public void setBeginBalanceSum(Double beginBalanceSum) {
        this.beginBalanceSum = beginBalanceSum;
    }

    public Double getBeginBalanceTen() {
        return beginBalanceTen;
    }

    public void setBeginBalanceTen(Double beginBalanceTen) {
        this.beginBalanceTen = beginBalanceTen;
    }

    public Double getBeginBalanceFift() {
        return beginBalanceFift;
    }

    public void setBeginBalanceFift(Double beginBalanceFift) {
        this.beginBalanceFift = beginBalanceFift;
    }

    public Double getBeginBalanceTwtenty() {
        return beginBalanceTwtenty;
    }

    public void setBeginBalanceTwtenty(Double beginBalanceTwtenty) {
        this.beginBalanceTwtenty = beginBalanceTwtenty;
    }

    public Double getBeginBalanceThirty() {
        return beginBalanceThirty;
    }

    public void setBeginBalanceThirty(Double beginBalanceThirty) {
        this.beginBalanceThirty = beginBalanceThirty;
    }

    public Double getOperatingSum() {
        return operatingSum;
    }

    public void setOperatingSum(Double operatingSum) {
        this.operatingSum = operatingSum;
    }

    public Double getOperatingTen() {
        return operatingTen;
    }

    public void setOperatingTen(Double operatingTen) {
        this.operatingTen = operatingTen;
    }

    public Double getOperatingFift() {
        return operatingFift;
    }

    public void setOperatingFift(Double operatingFift) {
        this.operatingFift = operatingFift;
    }

    public Double getOperatingTwtenty() {
        return operatingTwtenty;
    }

    public void setOperatingTwtenty(Double operatingTwtenty) {
        this.operatingTwtenty = operatingTwtenty;
    }

    public Double getOperatingThirty() {
        return operatingThirty;
    }

    public void setOperatingThirty(Double operatingThirty) {
        this.operatingThirty = operatingThirty;
    }

    public Double getLaborCostSum() {
        return laborCostSum;
    }

    public void setLaborCostSum(Double laborCostSum) {
        this.laborCostSum = laborCostSum;
    }

    public Double getLaborCostTen() {
        return laborCostTen;
    }

    public void setLaborCostTen(Double laborCostTen) {
        this.laborCostTen = laborCostTen;
    }

    public Double getLaborCostFift() {
        return laborCostFift;
    }

    public void setLaborCostFift(Double laborCostFift) {
        this.laborCostFift = laborCostFift;
    }

    public Double getLaborCostTwtenty() {
        return laborCostTwtenty;
    }

    public void setLaborCostTwtenty(Double laborCostTwtenty) {
        this.laborCostTwtenty = laborCostTwtenty;
    }

    public Double getLaborCostThirty() {
        return laborCostThirty;
    }

    public void setLaborCostThirty(Double laborCostThirty) {
        this.laborCostThirty = laborCostThirty;
    }

    public Double getPayableTaxSum() {
        return payableTaxSum;
    }

    public void setPayableTaxSum(Double payableTaxSum) {
        this.payableTaxSum = payableTaxSum;
    }

    public Double getPayableTaxTen() {
        return payableTaxTen;
    }

    public void setPayableTaxTen(Double payableTaxTen) {
        this.payableTaxTen = payableTaxTen;
    }

    public Double getPayableTaxFift() {
        return payableTaxFift;
    }

    public void setPayableTaxFift(Double payableTaxFift) {
        this.payableTaxFift = payableTaxFift;
    }

    public Double getPayableTaxTwtenty() {
        return payableTaxTwtenty;
    }

    public void setPayableTaxTwtenty(Double payableTaxTwtenty) {
        this.payableTaxTwtenty = payableTaxTwtenty;
    }

    public Double getPayableTaxThirty() {
        return payableTaxThirty;
    }

    public void setPayableTaxThirty(Double payableTaxThirty) {
        this.payableTaxThirty = payableTaxThirty;
    }

    public Double getCompanyBorrowedSum() {
        return companyBorrowedSum;
    }

    public void setCompanyBorrowedSum(Double companyBorrowedSum) {
        this.companyBorrowedSum = companyBorrowedSum;
    }

    public Double getCompanyBorrowedTen() {
        return companyBorrowedTen;
    }

    public void setCompanyBorrowedTen(Double companyBorrowedTen) {
        this.companyBorrowedTen = companyBorrowedTen;
    }

    public Double getCompanyBorrowedFift() {
        return companyBorrowedFift;
    }

    public void setCompanyBorrowedFift(Double companyBorrowedFift) {
        this.companyBorrowedFift = companyBorrowedFift;
    }

    public Double getCompanyBorrowedTwtenty() {
        return companyBorrowedTwtenty;
    }

    public void setCompanyBorrowedTwtenty(Double companyBorrowedTwtenty) {
        this.companyBorrowedTwtenty = companyBorrowedTwtenty;
    }

    public Double getCompanyBorrowedThirty() {
        return companyBorrowedThirty;
    }

    public void setCompanyBorrowedThirty(Double companyBorrowedThirty) {
        this.companyBorrowedThirty = companyBorrowedThirty;
    }

    public Double getPaidCapitalSum() {
        return paidCapitalSum;
    }

    public void setPaidCapitalSum(Double paidCapitalSum) {
        this.paidCapitalSum = paidCapitalSum;
    }

    public Double getPaidCapitalTen() {
        return paidCapitalTen;
    }

    public void setPaidCapitalTen(Double paidCapitalTen) {
        this.paidCapitalTen = paidCapitalTen;
    }

    public Double getPaidCapitalFift() {
        return paidCapitalFift;
    }

    public void setPaidCapitalFift(Double paidCapitalFift) {
        this.paidCapitalFift = paidCapitalFift;
    }

    public Double getPaidCapitalTwtenty() {
        return paidCapitalTwtenty;
    }

    public void setPaidCapitalTwtenty(Double paidCapitalTwtenty) {
        this.paidCapitalTwtenty = paidCapitalTwtenty;
    }

    public Double getPaidCapitalThirty() {
        return paidCapitalThirty;
    }

    public void setPaidCapitalThirty(Double paidCapitalThirty) {
        this.paidCapitalThirty = paidCapitalThirty;
    }

    public Double getCompanyLendSum() {
        return companyLendSum;
    }

    public void setCompanyLendSum(Double companyLendSum) {
        this.companyLendSum = companyLendSum;
    }

    public Double getCompanyLendTen() {
        return companyLendTen;
    }

    public void setCompanyLendTen(Double companyLendTen) {
        this.companyLendTen = companyLendTen;
    }

    public Double getCompanyLendFift() {
        return companyLendFift;
    }

    public void setCompanyLendFift(Double companyLendFift) {
        this.companyLendFift = companyLendFift;
    }

    public Double getCompanyLendTwtenty() {
        return companyLendTwtenty;
    }

    public void setCompanyLendTwtenty(Double companyLendTwtenty) {
        this.companyLendTwtenty = companyLendTwtenty;
    }

    public Double getCompanyLendThirty() {
        return companyLendThirty;
    }

    public void setCompanyLendThirty(Double companyLendThirty) {
        this.companyLendThirty = companyLendThirty;
    }

    public Double getBusinessIncomeSum() {
        return businessIncomeSum;
    }

    public void setBusinessIncomeSum(Double businessIncomeSum) {
        this.businessIncomeSum = businessIncomeSum;
    }

    public Double getBusinessIncomeTen() {
        return businessIncomeTen;
    }

    public void setBusinessIncomeTen(Double businessIncomeTen) {
        this.businessIncomeTen = businessIncomeTen;
    }

    public Double getBusinessIncomeFift() {
        return businessIncomeFift;
    }

    public void setBusinessIncomeFift(Double businessIncomeFift) {
        this.businessIncomeFift = businessIncomeFift;
    }

    public Double getBusinessIncomeTwtenty() {
        return businessIncomeTwtenty;
    }

    public void setBusinessIncomeTwtenty(Double businessIncomeTwtenty) {
        this.businessIncomeTwtenty = businessIncomeTwtenty;
    }

    public Double getBusinessIncomeThirty() {
        return businessIncomeThirty;
    }

    public void setBusinessIncomeThirty(Double businessIncomeThirty) {
        this.businessIncomeThirty = businessIncomeThirty;
    }

    public Double getForecastAccountSum() {
        return forecastAccountSum;
    }

    public void setForecastAccountSum(Double forecastAccountSum) {
        this.forecastAccountSum = forecastAccountSum;
    }

    public Double getForecastAccountTen() {
        return forecastAccountTen;
    }

    public void setForecastAccountTen(Double forecastAccountTen) {
        this.forecastAccountTen = forecastAccountTen;
    }

    public Double getForecastAccountFift() {
        return forecastAccountFift;
    }

    public void setForecastAccountFift(Double forecastAccountFift) {
        this.forecastAccountFift = forecastAccountFift;
    }

    public Double getForecastAccountTwtenty() {
        return forecastAccountTwtenty;
    }

    public void setForecastAccountTwtenty(Double forecastAccountTwtenty) {
        this.forecastAccountTwtenty = forecastAccountTwtenty;
    }

    public Double getForecastAccountThirty() {
        return forecastAccountThirty;
    }

    public void setForecastAccountThirty(Double forecastAccountThirty) {
        this.forecastAccountThirty = forecastAccountThirty;
    }

    public Double getActualGapSum() {
        return actualGapSum;
    }

    public void setActualGapSum(Double actualGapSum) {
        this.actualGapSum = actualGapSum;
    }

    public Double getActualGapTen() {
        return actualGapTen;
    }

    public void setActualGapTen(Double actualGapTen) {
        this.actualGapTen = actualGapTen;
    }

    public Double getActualGapFift() {
        return actualGapFift;
    }

    public void setActualGapFift(Double actualGapFift) {
        this.actualGapFift = actualGapFift;
    }

    public Double getActualGapTwtenty() {
        return actualGapTwtenty;
    }

    public void setActualGapTwtenty(Double actualGapTwtenty) {
        this.actualGapTwtenty = actualGapTwtenty;
    }

    public Double getActualGapThirty() {
        return actualGapThirty;
    }

    public void setActualGapThirty(Double actualGapThirty) {
        this.actualGapThirty = actualGapThirty;
    }

    public Double getForecastBalanceMoSum() {
        return forecastBalanceMoSum;
    }

    public void setForecastBalanceMoSum(Double forecastBalanceMoSum) {
        this.forecastBalanceMoSum = forecastBalanceMoSum;
    }

    public Double getForecastBalanceMoGapTen() {
        return forecastBalanceMoGapTen;
    }

    public void setForecastBalanceMoGapTen(Double forecastBalanceMoGapTen) {
        this.forecastBalanceMoGapTen = forecastBalanceMoGapTen;
    }

    public Double getForecastBalanceMoGapFift() {
        return forecastBalanceMoGapFift;
    }

    public void setForecastBalanceMoGapFift(Double forecastBalanceMoGapFift) {
        this.forecastBalanceMoGapFift = forecastBalanceMoGapFift;
    }

    public Double getForecastBalanceMoTwtenty() {
        return forecastBalanceMoTwtenty;
    }

    public void setForecastBalanceMoTwtenty(Double forecastBalanceMoTwtenty) {
        this.forecastBalanceMoTwtenty = forecastBalanceMoTwtenty;
    }

    public Double getForecastBalanceMoThirty() {
        return forecastBalanceMoThirty;
    }

    public void setForecastBalanceMoThirty(Double forecastBalanceMoThirty) {
        this.forecastBalanceMoThirty = forecastBalanceMoThirty;
    }
}