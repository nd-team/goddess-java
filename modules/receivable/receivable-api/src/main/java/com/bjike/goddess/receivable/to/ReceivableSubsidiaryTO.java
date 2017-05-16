package com.bjike.goddess.receivable.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.receivable.entity.Contractor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * 回款明细
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 04:09 ]
 * @Description: [ 回款明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReceivableSubsidiaryTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空")
    private String area;

    /**
     * 项目内部名称
     */
    @NotBlank(message = "项目内部名称不能为空")
    private String innerName;

    /**
     * 派工单编号
     */
    private String taskNum;

    /**
     * 派工单价
     */
    @NotNull(message = "地区不能为空")
    private Double taskPrice;

    /**
     * 合同规模数
     */
    @NotNull(message = "合同规模数不能为空")
    private Double pactNum;

    /**
     * 合同规模金额(派工单价*合同规模数)
     */
    @NotNull(message = "合同规模金额不能为空")
    private Double pactMoney;

    /**
     * 已派工量
     */
    @NotNull(message = "已派工量不能为空")
    private Double pactSize;

    /**
     * 中兴派工金额(派工单价*已派工量)
     */
    @NotNull(message = "中兴派工金额不能为空")
    private Double taskMoney;

    /**
     * 已完工量
     */
    @NotNull(message = "已完工量不能为空")
    private Double finishNum;

    /**
     * 已完工金额(派工单价*已完工量)
     */
    @NotNull(message = "已完工金额不能为空")
    private Double finishMoney;

    /**
     * 未完工量
     */
    @NotNull(message = "未完工量不能为空")
    private Double unfinishNum;

    /**
     * 未完工金额(派工单价*未完工量)
     */
    @NotNull(message = "未完工金额不能为空")
    private Double unfinishMoney;

    /**
     * 已交维数量
     */
    @NotNull(message = "已交维数量不能为空")
    private Double payTax;

    /**
     * 未交维数量
     */
    @NotNull(message = "未交维数量不能为空")
    private Double undeal;

    /**
     * 违约金
     */
    @NotNull(message = "违约金不能为空")
    private Double penalty;

    /**
     * 实际结算数量
     */
    @NotNull(message = "实际结算数量不能为空")
    private Double realCountNum;

    /**
     * 实际数量金额
     */
    @NotNull(message = "实际数量金额不能为空")
    private Double realCountMoney;

    /**
     * 完工时间
     */
    @NotBlank(message = "完工时间不能为空")
    private String finishTime;

    /**
     * 验收交维时间
     */
    @NotBlank(message = "验收交维时间不能为空")
    private String checkTime;

    /**
     * 签字审批时间
     */
    @NotBlank(message = "签字审批时间不能为空")
    private String auditTime;

    /**
     * 结算审批时间
     */
    @NotBlank(message = "结算审批时间不能为空")
    private String countTime;

    /**
     * 发票审核时间
     */
    @NotBlank(message = "发票审核时间不能为空")
    private String billTime;

    /**
     * 预计支付时间
     */
    @NotBlank(message = "预计支付时间不能为空")
    private String planTime;

    /**
     * 管理费(实际数量金额*承包商比例)
     */
    @NotNull(message = "管理费不能为空")
    private Double managementFee;

    /**
     * 到账时间
     */
    @NotBlank(message = "到账时间不能为空")
    private String accountTime;

    /**
     * 结算进度:A
     */
    private String progressA;

    /**
     * 结算进度:B
     */
    private String progressB;

    /**
     * 结算进度:C
     */
    private String progressC;

    /**
     * 结算进度:D
     */
    private String progressD;

    /**
     * 到账金额(实际数量金额*管理费)
     */
    @NotNull(message = "到账金额不能为空")
    private Double accountMoney;

    /**
     * 税金(到账金额*6.79%)
     */
    @NotNull(message = "税金不能为空")
    private Double taxes;

    /**
     * 税后金额(到账金额-税金)
     */
    @NotNull(message = "税后金额不能为空")
    private Double afterTax;

    /**
     * 剩余结算量(已派工量-实际结算数量)
     */
    @NotNull(message = "剩余结算量不能为空")
    private Double moreNum;

    /**
     * 剩余结算金额(派工单价*剩余结算量)
     */
    @NotNull(message = "剩余结算金额不能为空")
    private Double moreMoney;

    /**
     * 承包商
     */
    @NotBlank(message = "承包商不能为空")
    private String contractorId;

    /**
     * 是否已支付
     */
    @NotNull(message = "是否已支付不能为空")
    private Boolean ispay;

    /**
     * 是否框架内
     */
    @NotNull(message = "是否框架内不能为空")
    private Boolean isframe;

    /**
     * 是否有单次合同
     */
    @NotNull(message = "是否有单次合同不能为空")
    private Boolean ispact;

    /**
     * 是否已走结算流程
     */
    @NotNull(message = "是否已走结算流程不能为空")
    private Boolean isflow;

    /*//开始时间
    private String startTime;
    //结束时间
    private String endTime;*/


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }

    public String getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum;
    }

    public Double getTaskPrice() {
        return taskPrice;
    }

    public void setTaskPrice(Double taskPrice) {
        this.taskPrice = taskPrice;
    }

    public Double getPactNum() {
        return pactNum;
    }

    public void setPactNum(Double pactNum) {
        this.pactNum = pactNum;
    }

    public Double getPactMoney() {
        return pactMoney;
    }

    public void setPactMoney(Double pactMoney) {
        this.pactMoney = pactMoney;
    }

    public Double getPactSize() {
        return pactSize;
    }

    public void setPactSize(Double pactSize) {
        this.pactSize = pactSize;
    }

    public Double getTaskMoney() {
        return taskMoney;
    }

    public void setTaskMoney(Double taskMoney) {
        this.taskMoney = taskMoney;
    }

    public Double getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(Double finishNum) {
        this.finishNum = finishNum;
    }

    public Double getFinishMoney() {
        return finishMoney;
    }

    public void setFinishMoney(Double finishMoney) {
        this.finishMoney = finishMoney;
    }

    public Double getUnfinishNum() {
        return unfinishNum;
    }

    public void setUnfinishNum(Double unfinishNum) {
        this.unfinishNum = unfinishNum;
    }

    public Double getUnfinishMoney() {
        return unfinishMoney;
    }

    public void setUnfinishMoney(Double unfinishMoney) {
        this.unfinishMoney = unfinishMoney;
    }

    public Double getPayTax() {
        return payTax;
    }

    public void setPayTax(Double payTax) {
        this.payTax = payTax;
    }

    public Double getUndeal() {
        return undeal;
    }

    public void setUndeal(Double undeal) {
        this.undeal = undeal;
    }

    public Double getPenalty() {
        return penalty;
    }

    public void setPenalty(Double penalty) {
        this.penalty = penalty;
    }

    public Double getRealCountNum() {
        return realCountNum;
    }

    public void setRealCountNum(Double realCountNum) {
        this.realCountNum = realCountNum;
    }

    public Double getRealCountMoney() {
        return realCountMoney;
    }

    public void setRealCountMoney(Double realCountMoney) {
        this.realCountMoney = realCountMoney;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getCountTime() {
        return countTime;
    }

    public void setCountTime(String countTime) {
        this.countTime = countTime;
    }

    public String getBillTime() {
        return billTime;
    }

    public void setBillTime(String billTime) {
        this.billTime = billTime;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public Double getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(Double managementFee) {
        this.managementFee = managementFee;
    }

    public String getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(String accountTime) {
        this.accountTime = accountTime;
    }

    public String getProgressA() {
        return progressA;
    }

    public void setProgressA(String progressA) {
        this.progressA = progressA;
    }

    public String getProgressB() {
        return progressB;
    }

    public void setProgressB(String progressB) {
        this.progressB = progressB;
    }

    public String getProgressC() {
        return progressC;
    }

    public void setProgressC(String progressC) {
        this.progressC = progressC;
    }

    public String getProgressD() {
        return progressD;
    }

    public void setProgressD(String progressD) {
        this.progressD = progressD;
    }

    public Double getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(Double accountMoney) {
        this.accountMoney = accountMoney;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public Double getAfterTax() {
        return afterTax;
    }

    public void setAfterTax(Double afterTax) {
        this.afterTax = afterTax;
    }

    public Double getMoreNum() {
        return moreNum;
    }

    public void setMoreNum(Double moreNum) {
        this.moreNum = moreNum;
    }

    public Double getMoreMoney() {
        return moreMoney;
    }

    public void setMoreMoney(Double moreMoney) {
        this.moreMoney = moreMoney;
    }

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public Boolean getIspay() {
        return ispay;
    }

    public void setIspay(Boolean ispay) {
        this.ispay = ispay;
    }

    public Boolean getIsframe() {
        return isframe;
    }

    public void setIsframe(Boolean isframe) {
        this.isframe = isframe;
    }

    public Boolean getIspact() {
        return ispact;
    }

    public void setIspact(Boolean ispact) {
        this.ispact = ispact;
    }

    public Boolean getIsflow() {
        return isflow;
    }

    public void setIsflow(Boolean isflow) {
        this.isflow = isflow;
    }

    /*public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }*/
}