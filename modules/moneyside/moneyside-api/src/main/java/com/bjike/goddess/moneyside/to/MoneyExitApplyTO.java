package com.bjike.goddess.moneyside.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.moneyside.enums.PassStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 资金退出申请表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 10:37 ]
 * @Description: [ 资金退出申请表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MoneyExitApplyTO extends BaseTO {
    public interface TestAdd{}
    public interface TestEdit{}
    /**
     * 投资人
     */
    @NotBlank(groups = {MoneyExitApplyTO.TestAdd.class,MoneyExitApplyTO.TestEdit.class},message = "投资人不能为空")
    private String investor;

    /**
     * 协议投资金额
     */
    @NotNull(groups = {MoneyExitApplyTO.TestAdd.class,MoneyExitApplyTO.TestEdit.class},message = "协议投资金额不能为空")
    private Double amountAagree;

    /**
     * 累计投资金额
     */
    @NotNull(groups = {MoneyExitApplyTO.TestAdd.class,MoneyExitApplyTO.TestEdit.class},message = "累计投资金额不能为空")
    private Double accumulativeInvestMoney;

    /**
     * 投资方式
     */
    @NotBlank(groups = {MoneyExitApplyTO.TestAdd.class,MoneyExitApplyTO.TestEdit.class},message = "投资方式不能为空")
    private String investForm;

    /**
     * 投资项目
     */
    @NotBlank(groups = {MoneyExitApplyTO.TestAdd.class,MoneyExitApplyTO.TestEdit.class},message = "投资项目不能为空")
    private String investProject;

    /**
     * 开工时间
     */
    @NotBlank(groups = {MoneyExitApplyTO.TestAdd.class,MoneyExitApplyTO.TestEdit.class},message = "开工时间不能为空")
    private String startProjectTime;

    /**
     * 完工时间
     */
    @NotBlank(groups = {MoneyExitApplyTO.TestAdd.class,MoneyExitApplyTO.TestEdit.class},message = "完工时间不能为空")
    private String endProjectTime;

    /**
     * 项目进度
     */
    @NotBlank(groups = {MoneyExitApplyTO.TestAdd.class,MoneyExitApplyTO.TestEdit.class},message = "项目进度不能为空")
    private String projectProgress;

    /**
     * 结算进度
     */
    @NotBlank(groups = {MoneyExitApplyTO.TestAdd.class,MoneyExitApplyTO.TestEdit.class},message = "结算进度不能为空")
    private String settlementProgress;

    /**
     * 预计到账时间
     */
    @NotBlank(groups = {MoneyExitApplyTO.TestAdd.class,MoneyExitApplyTO.TestEdit.class},message = "预计到账时间不能为空")
    private String forecastArriveTime;

    /**
     * 退出时间
     */
    @NotBlank(groups = {MoneyExitApplyTO.TestAdd.class,MoneyExitApplyTO.TestEdit.class},message = "退出时间不能为空")
    private String exitTime;

    /**
     * 退出利率
     */
    @NotNull(groups = {MoneyExitApplyTO.TestAdd.class,MoneyExitApplyTO.TestEdit.class},message = "退出利率不能为空")
    private Double exitInterestRate;

    /**
     * 退出金额
     */
    @NotNull(groups = {MoneyExitApplyTO.TestAdd.class,MoneyExitApplyTO.TestEdit.class},message = "退出金额不能为空")
    private Double exitMoney;

    /**
     * 剩余金额（累计投资金额-退出金额）
     */
    private Double residueMoney;

    /**
     * 退出利息
     */
    @NotNull(groups = {MoneyExitApplyTO.TestAdd.class,MoneyExitApplyTO.TestEdit.class},message = "退出利息不能为空")
    private Double exitInterest;

    /**
     * 审批人
     */
    private String approver;

    /**
     * 审批时间
     */
    private String approverTime;

    /**
     * 审批意见
     */
    private String approverOpinion;
    /**
     * 是否通过
     */
    private PassStatus pass;
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

    public Double getAmountAagree() {
        return amountAagree;
    }

    public void setAmountAagree(Double amountAagree) {
        this.amountAagree = amountAagree;
    }

    public Double getAccumulativeInvestMoney() {
        return accumulativeInvestMoney;
    }

    public void setAccumulativeInvestMoney(Double accumulativeInvestMoney) {
        this.accumulativeInvestMoney = accumulativeInvestMoney;
    }

    public String getInvestForm() {
        return investForm;
    }

    public void setInvestForm(String investForm) {
        this.investForm = investForm;
    }

    public String getInvestProject() {
        return investProject;
    }

    public void setInvestProject(String investProject) {
        this.investProject = investProject;
    }

    public String getStartProjectTime() {
        return startProjectTime;
    }

    public void setStartProjectTime(String startProjectTime) {
        this.startProjectTime = startProjectTime;
    }

    public String getEndProjectTime() {
        return endProjectTime;
    }

    public void setEndProjectTime(String endProjectTime) {
        this.endProjectTime = endProjectTime;
    }

    public String getProjectProgress() {
        return projectProgress;
    }

    public void setProjectProgress(String projectProgress) {
        this.projectProgress = projectProgress;
    }

    public String getSettlementProgress() {
        return settlementProgress;
    }

    public void setSettlementProgress(String settlementProgress) {
        this.settlementProgress = settlementProgress;
    }

    public String getForecastArriveTime() {
        return forecastArriveTime;
    }

    public void setForecastArriveTime(String forecastArriveTime) {
        this.forecastArriveTime = forecastArriveTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public Double getExitInterestRate() {
        return exitInterestRate;
    }

    public void setExitInterestRate(Double exitInterestRate) {
        this.exitInterestRate = exitInterestRate;
    }

    public Double getExitMoney() {
        return exitMoney;
    }

    public void setExitMoney(Double exitMoney) {
        this.exitMoney = exitMoney;
    }

    public Double getResidueMoney() {
        return residueMoney;
    }

    public void setResidueMoney(Double residueMoney) {
        this.residueMoney = residueMoney;
    }

    public Double getExitInterest() {
        return exitInterest;
    }

    public void setExitInterest(Double exitInterest) {
        this.exitInterest = exitInterest;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getApproverTime() {
        return approverTime;
    }

    public void setApproverTime(String approverTime) {
        this.approverTime = approverTime;
    }

    public String getApproverOpinion() {
        return approverOpinion;
    }

    public void setApproverOpinion(String approverOpinion) {
        this.approverOpinion = approverOpinion;
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

    public PassStatus getPass() {
        return pass;
    }

    public void setPass(PassStatus pass) {
        this.pass = pass;
    }
}