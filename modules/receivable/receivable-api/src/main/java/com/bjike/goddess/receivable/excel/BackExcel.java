package com.bjike.goddess.receivable.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;

/**
 * @Author: [xiazhili]
 * @Date: [2017-11-09 16:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BackExcel {
    /**
     * 地区
     */
    @ExcelHeader(name = "地区")
    private String area;

    /**
     * 项目内部名称
     */
    @ExcelHeader(name = "项目内部名称")
    private String innerName;

    /**
     * 派工单编号
     */
    @ExcelHeader(name = "派工单编号")
    private String taskNum;
    /**
     * 派工合同号
     */
    @ExcelHeader(name = "派工合同号")
    private String contractNum;
    /**
     * 外包合同号
     */
    @ExcelHeader(name = "外包合同号")
    private String outsourcingNum;
    /**
     * 派工单价
     */
    @ExcelHeader(name = "派工单价")
    private Double taskPrice;
    /**
     * 合同规模数
     */
    @ExcelHeader(name = "合同规模数")
    private Double pactNum;

    /**
     * 合同规模金额(派工单价*合同规模数)
     */
    @ExcelHeader(name = "合同规模金额")
    private Double pactMoney;

    /**
     * 已派工量
     */
    @ExcelHeader(name = "已派工量")
    private Double pactSize;

    /**
     * 中兴派工金额(派工单价*已派工量)
     */
    @ExcelHeader(name = "中兴派工金额")
    private Double taskMoney;

    /**
     * 已完工量
     */
    @ExcelHeader(name = "已完工量")
    private Double finishNum;

    /**
     * 已完工金额(派工单价*已完工量)
     */
    @ExcelHeader(name = "已完工金额")
    private Double finishMoney;

    /**
     * 未完工量
     */
    @ExcelHeader(name = "未完工量")
    private Double unfinishNum;

    /**
     * 未完工金额(派工单价*未完工量)
     */
    @ExcelHeader(name = "未完工金额")
    private Double unfinishMoney;

    /**
     * 已交维数量
     */
    @ExcelHeader(name = "已交维数量")
    private Double payTax;

    /**
     * 未交维数量
     */
    @ExcelHeader(name = "未交维数量")
    private Double undeal;

    /**
     * 违约金
     */
    @ExcelHeader(name = "违约金")
    private Double penalty;

    /**
     * 实际结算数量
     */
    @ExcelHeader(name = "实际结算数量")
    private Double realCountNum;

    /**
     * 实际数量金额
     */
    @ExcelHeader(name = "实际数量金额")
    private Double realCountMoney;

    /**
     * 完工时间
     */
    @ExcelHeader(name = "完工时间")
    private LocalDate finishTime;

    /**
     * 验收交维时间
     */
    @ExcelHeader(name = "验收交维时间")
    private LocalDate checkTime;

    /**
     * 签字审批时间
     */
    @ExcelHeader(name = "签字审批时间")
    private LocalDate auditTime;

    /**
     * 结算审批时间
     */
    @ExcelHeader(name = "结算审批时间")
    private LocalDate countTime;

    /**
     * 发票审核时间
     */
    @ExcelHeader(name = "发票审核时间")
    private LocalDate billTime;

    /**
     * 预计支付时间
     */
    @ExcelHeader(name = "预计支付时间")
    private LocalDate planTime;

//    /**
//     * 管理费(实际数量金额*承包商比例)
//     */
//    @ExcelHeader(name = "管理费")
//    private Double managementFee;

    /**
     * 到账时间
     */
    @ExcelHeader(name = "到账时间")
    private LocalDate accountTime;

    /**
     * 结算进度:A
     */
    @ExcelHeader(name = "结算进度:A")
    private String progressA;
    /**
     * 结算进度:B
     */
    @ExcelHeader(name = "结算进度:B")
    private String progressB;

    /**
     * 结算进度:C
     */
    @ExcelHeader(name = "结算进度:C")
    private String progressC;

    /**
     * 结算进度:D
     */
    @ExcelHeader(name = "结算进度:D")
    private String progressD;

//    /**
//     * 到账金额(实际数量金额*管理费)
//     */
//    @ExcelHeader(name = "到账金额")
//    private Double accountMoney;

    /**
     * 税金(到账金额*6.79%)
     */
    @ExcelHeader(name = "税金")
    private Double taxes;

    /**
     * 税后金额(到账金额-税金)
     */
    @ExcelHeader(name = "税后金额")
    private Double afterTax;

    /**
     * 剩余结算量(已派工量-实际结算数量)
     */
    @ExcelHeader(name = "剩余结算量")
    private Double moreNum;

    /**
     * 剩余结算金额(派工单价*剩余结算量)
     */
    @ExcelHeader(name = "剩余结算金额")
    private Double moreMoney;

    /**
     * 是否已支付
     */
    @ExcelHeader(name = "是否已支付")
    private String pay;

    /**
     * 是否框架内
     */
    @ExcelHeader(name = "是否框架内")
    private String frame;

    /**
     * 是否有单次合同
     */
    @ExcelHeader(name = "是否有单次合同")
    private String pact;

    /**
     * 是否已走结算流程
     */
    @ExcelHeader(name = "是否已走结算流程")
    private String flow;

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

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getOutsourcingNum() {
        return outsourcingNum;
    }

    public void setOutsourcingNum(String outsourcingNum) {
        this.outsourcingNum = outsourcingNum;
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

    public LocalDate getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDate finishTime) {
        this.finishTime = finishTime;
    }

    public LocalDate getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(LocalDate checkTime) {
        this.checkTime = checkTime;
    }

    public LocalDate getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(LocalDate auditTime) {
        this.auditTime = auditTime;
    }

    public LocalDate getCountTime() {
        return countTime;
    }

    public void setCountTime(LocalDate countTime) {
        this.countTime = countTime;
    }

    public LocalDate getBillTime() {
        return billTime;
    }

    public void setBillTime(LocalDate billTime) {
        this.billTime = billTime;
    }

    public LocalDate getPlanTime() {
        return planTime;
    }

    public void setPlanTime(LocalDate planTime) {
        this.planTime = planTime;
    }

//    public Double getManagementFee() {
//        return managementFee;
//    }
//
//    public void setManagementFee(Double managementFee) {
//        this.managementFee = managementFee;
//    }

    public LocalDate getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(LocalDate accountTime) {
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

//    public Double getAccountMoney() {
//        return accountMoney;
//    }
//
//    public void setAccountMoney(Double accountMoney) {
//        this.accountMoney = accountMoney;
//    }

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

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getPact() {
        return pact;
    }

    public void setPact(String pact) {
        this.pact = pact;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }
}
