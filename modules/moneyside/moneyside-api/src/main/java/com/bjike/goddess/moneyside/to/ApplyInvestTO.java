package com.bjike.goddess.moneyside.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 申请投资
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:23 ]
 * @Description: [ 申请投资 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ApplyInvestTO extends BaseTO {

    /**
     * 投资人
     */
    @NotBlank(message = "投资人不能为空",groups = {ADD.class, EDIT.class})
    private String investor;

    /**
     * 投资对象
     */
    @NotBlank(message = "投资对象不能为空",groups = {ADD.class, EDIT.class})
    private String investObject;

    /**
     * 投资形式
     */
    @NotBlank(message = "投资形式不能为空",groups = {ADD.class, EDIT.class})
    private String investForm;

    /**
     * 投资总额
     */
    @NotNull(message = "投资总额不能为空",groups = {ADD.class, EDIT.class})
    private Double investTotal;

    /**
     * 投资占比（投资总额/筹资总额）
     */
    private Double investProportion;

    /**
     * 投资日期
     */
    @NotBlank(message = "投资日期不能为空",groups = {ADD.class, EDIT.class})
    private String investDate;

    /**
     * 本次投资额
     */
    @NotNull(message = "本次投资额不能为空",groups = {ADD.class, EDIT.class})
    private Double thisInvestMoney;


    /**
     * 累计投资额
     */
    private Double accumulativeInvestMoney;

    /**
     * 累计投资比（累计投资额/投资总额）
     */
    private Double accumulativeInvestProportion;

    /**
     * 提取风险控制保证金（本次投资额/提取风险准备金率）
     */
    private Double extractRiskControlMargin;

    /**
     * 打款账户
     */
    private String moneyAccountName;

    /**
     * 打款账号
     */
    private String moneyAccount;

    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;



    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getInvestObject() {
        return investObject;
    }

    public void setInvestObject(String investObject) {
        this.investObject = investObject;
    }

    public String getInvestForm() {
        return investForm;
    }

    public void setInvestForm(String investForm) {
        this.investForm = investForm;
    }

    public Double getInvestTotal() {
        return investTotal;
    }

    public void setInvestTotal(Double investTotal) {
        this.investTotal = investTotal;
    }

    public Double getInvestProportion() {
        return investProportion;
    }

    public void setInvestProportion(Double investProportion) {
        this.investProportion = investProportion;
    }

    public String getInvestDate() {
        return investDate;
    }

    public void setInvestDate(String investDate) {
        this.investDate = investDate;
    }

    public Double getThisInvestMoney() {
        return thisInvestMoney;
    }

    public void setThisInvestMoney(Double thisInvestMoney) {
        this.thisInvestMoney = thisInvestMoney;
    }


    public Double getAccumulativeInvestMoney() {
        return accumulativeInvestMoney;
    }

    public void setAccumulativeInvestMoney(Double accumulativeInvestMoney) {
        this.accumulativeInvestMoney = accumulativeInvestMoney;
    }

    public Double getAccumulativeInvestProportion() {
        return accumulativeInvestProportion;
    }

    public void setAccumulativeInvestProportion(Double accumulativeInvestProportion) {
        this.accumulativeInvestProportion = accumulativeInvestProportion;
    }

    public Double getExtractRiskControlMargin() {
        return extractRiskControlMargin;
    }

    public void setExtractRiskControlMargin(Double extractRiskControlMargin) {
        this.extractRiskControlMargin = extractRiskControlMargin;
    }

    public String getMoneyAccountName() {
        return moneyAccountName;
    }

    public void setMoneyAccountName(String moneyAccountName) {
        this.moneyAccountName = moneyAccountName;
    }

    public String getMoneyAccount() {
        return moneyAccount;
    }

    public void setMoneyAccount(String moneyAccount) {
        this.moneyAccount = moneyAccount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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