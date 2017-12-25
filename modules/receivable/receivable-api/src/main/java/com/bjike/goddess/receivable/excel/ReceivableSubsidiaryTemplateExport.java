package com.bjike.goddess.receivable.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;

/**
 * 回款明细表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 04:09 ]
 * @Description: [ 回款明细表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReceivableSubsidiaryTemplateExport {

    /**
     * 地区
     */
    @ExcelHeader(name = "地区",notNull = true)
    private String area;

    /**
     * 项目内部名称
     */
    @ExcelHeader(name = "项目内部名称",notNull = true)
    private String innerName;


    /**
     * 派工单价
     */
    @ExcelHeader(name = "派工单价",notNull = true)
    private Double taskPrice;

    /**
     * 合同规模数
     */
    @ExcelHeader(name = "合同规模数",notNull = true)
    private Double pactNum;

    /**
     * 已派工量
     */
    @ExcelHeader(name = "已派工量",notNull = true)
    private Double pactSize;


    /**
     * 已完工量
     */
    @ExcelHeader(name = "已完工量",notNull = true)
    private Double finishNum;

    /**
     * 未完工量
     */
    @ExcelHeader(name = "未完工量",notNull = true)
    private Double unfinishNum;


    /**
     * 已交维数量
     */
    @ExcelHeader(name = "已交维数量",notNull = true)
    private Double payTax;

    /**
     * 未交维数量
     */
    @ExcelHeader(name = "未交维数量",notNull = true)
    private Double undeal;

    /**
     * 违约金
     */
    @ExcelHeader(name = "违约金",notNull = true)
    private Double penalty;

    /**
     * 实际结算数量
     */
    @ExcelHeader(name = "实际结算数量",notNull = true)
    private Double realCountNum;

    /**
     * 实际数量金额
     */
    @ExcelHeader(name = "实际数量金额",notNull = true)
    private Double realCountMoney;


    /**
     * 是否已支付
     */
    @ExcelHeader(name = "是否已支付",notNull = true)
    private String pay;

    /**
     * 是否框架内
     */
    @ExcelHeader(name = "是否框架内",notNull = true)
    private String frame;

    /**
     * 是否有单次合同
     */
    @ExcelHeader(name = "是否有单次合同",notNull = true)
    private String pact;

    /**
     * 是否已走结算流程
     */
    @ExcelHeader(name = "是否已走结算流程",notNull = true)
    private String flow;
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

    /**
     * 到账时间
     */
    @ExcelHeader(name = "到账时间")
    private LocalDate accountTime;

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

    public LocalDate getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(LocalDate accountTime) {
        this.accountTime = accountTime;
    }

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

    public Double getPactSize() {
        return pactSize;
    }

    public void setPactSize(Double pactSize) {
        this.pactSize = pactSize;
    }

    public Double getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(Double finishNum) {
        this.finishNum = finishNum;
    }

    public Double getUnfinishNum() {
        return unfinishNum;
    }

    public void setUnfinishNum(Double unfinishNum) {
        this.unfinishNum = unfinishNum;
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