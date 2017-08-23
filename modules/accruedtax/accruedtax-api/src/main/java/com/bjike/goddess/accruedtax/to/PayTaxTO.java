package com.bjike.goddess.accruedtax.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 应交税金
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-24 09:17 ]
 * @Description: [ 应交税金 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PayTaxTO extends BaseTO {
    public interface TestAdd{}
    public interface TestSplit{}

    /**
     * 公司
     */
    @NotBlank(groups = {PayTaxTO.TestAdd.class} , message = "公司不能为空")
    private String company;

    /**
     * 日期
     */
    @NotBlank(groups = {PayTaxTO.TestAdd.class} ,message = "日期不能为空，格式为年月日")
    private String taxDate;

    /**
     * 税种
     */
    @NotBlank(groups = {PayTaxTO.TestAdd.class} ,message = "税种不能为空")
    private String taxType;

    /**
     * 税率
     */
    @NotNull(groups = {PayTaxTO.TestAdd.class} ,message = "税率不能为空,且为数字")
    private Double taxRate;

    /**
     * 目标税金
     */
    @NotNull(groups = {PayTaxTO.TestAdd.class} ,message = "目标税金不能为空,且为数字")
    private Double targetTax;

    /**
     * 计划税金
     */
    @NotNull(groups = {PayTaxTO.TestAdd.class} ,message = "计划税金不能为空,且为数字")
    private Double planTax;

    /**
     * 实际税金
     */
    @NotNull(groups = {PayTaxTO.TestAdd.class} ,message = "实际税金不能为空,且为数字")
    private Double actualTax;

    /**
     * 比率
     */
    private Double rate;

    /**
     * 差额
     */
    private Double balance;

    /**
     * 项目名称
     */
    @NotBlank(groups = {PayTaxTO.TestSplit.class} , message = "项目名称不能为空")
    private String project;

    /**
     * 分摊比率
     */
    @NotNull(groups = {PayTaxTO.TestSplit.class} , message = "分摊比率不能为空,且为数字")
    private Double splitRate;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTaxDate() {
        return taxDate;
    }

    public void setTaxDate(String taxDate) {
        this.taxDate = taxDate;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getTargetTax() {
        return targetTax;
    }

    public void setTargetTax(Double targetTax) {
        this.targetTax = targetTax;
    }

    public Double getPlanTax() {
        return planTax;
    }

    public void setPlanTax(Double planTax) {
        this.planTax = planTax;
    }

    public Double getActualTax() {
        return actualTax;
    }

    public void setActualTax(Double actualTax) {
        this.actualTax = actualTax;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Double getSplitRate() {
        return splitRate;
    }

    public void setSplitRate(Double splitRate) {
        this.splitRate = splitRate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}