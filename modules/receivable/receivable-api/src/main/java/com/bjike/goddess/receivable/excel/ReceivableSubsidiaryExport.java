package com.bjike.goddess.receivable.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.receivable.entity.Contractor;
import com.bjike.goddess.receivable.vo.ContractorVO;

/**
 * 回款明细表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 04:09 ]
 * @Description: [ 回款明细表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReceivableSubsidiaryExport {

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
     * 派工单编号
     */
    @ExcelHeader(name = "派工单编号",notNull = true)
    private String taskNum;

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
     * 合同规模金额
     */
    @ExcelHeader(name = "合同规模金额",notNull = true)
    private Double pactMoney;

    /**
     * 已派工量
     */
    @ExcelHeader(name = "已派工量",notNull = true)
    private Double pactSize;

    /**
     * 中兴派工金额
     */
    @ExcelHeader(name = "中兴派工金额",notNull = true)
    private Double taskMoney;

    /**
     * 已完工量
     */
    @ExcelHeader(name = "已完工量",notNull = true)
    private Double finishNum;

    /**
     * 已完工金额
     */
    @ExcelHeader(name = "已完工金额",notNull = true)
    private Double finishMoney;

    /**
     * 未完工量
     */
    @ExcelHeader(name = "未完工量",notNull = true)
    private Double unfinishNum;

    /**
     * 未完工金额
     */
    @ExcelHeader(name = "未完工金额",notNull = true)
    private Double unfinishMoney;

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
     * 完工时间
     */
    @ExcelHeader(name = "完工时间",notNull = true)
    private String finishTime;

    /**
     * 验收交维时间
     */
    @ExcelHeader(name = "验收交维时间",notNull = true)
    private String checkTime;

    /**
     * 签字审批时间
     */
    @ExcelHeader(name = "签字审批时间",notNull = true)
    private String auditTime;

    /**
     * 结算审批时间
     */
    @ExcelHeader(name = "结算审批时间",notNull = true)
    private String countTime;

    /**
     * 发票审核时间
     */
    @ExcelHeader(name = "发票审核时间",notNull = true)
    private String billTime;

    /**
     * 预计支付时间
     */
    @ExcelHeader(name = "预计支付时间",notNull = true)
    private String planTime;

    /**
     * 管理费
     */
    @ExcelHeader(name = "管理费",notNull = true)
    private Double managementFee;

    /**
     * 到账时间
     */
    @ExcelHeader(name = "到账时间",notNull = true)
    private String accountTime;

    /**
     * 结算进度:A
     */
    @ExcelHeader(name = "结算进度:A",notNull = true)
    private String progressA;

    /**
     * 结算进度:B
     */
    @ExcelHeader(name = "结算进度:B",notNull = true)
    private String progressB;

    /**
     * 结算进度:C
     */
    @ExcelHeader(name = "结算进度:C",notNull = true)
    private String progressC;

    /**
     * 结算进度:D
     */
    @ExcelHeader(name = "结算进度:D",notNull = true)
    private String progressD;

    /**
     * 到账金额
     */
    @ExcelHeader(name = "到账金额",notNull = true)
    private Double accountMoney;

    /**
     * 税金
     */
    @ExcelHeader(name = "税金",notNull = true)
    private Double taxes;

    /**
     * 税后金额
     */
    @ExcelHeader(name = "税后金额",notNull = true)
    private Double afterTax;

    /**
     * 剩余结算量
     */
    @ExcelHeader(name = "剩余结算量",notNull = true)
    private Double moreNum;

    /**
     * 剩余结算金额
     */
    @ExcelHeader(name = "剩余结算金额",notNull = true)
    private Double moreMoney;

    /**
     * 承包商
     */
    @ExcelHeader(name = "承包商",notNull = true)
    private String contractor;

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

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
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