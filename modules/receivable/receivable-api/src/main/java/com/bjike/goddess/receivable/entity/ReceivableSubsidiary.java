package com.bjike.goddess.receivable.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.receivable.enums.AuditStatus;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 回款明细
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 04:09 ]
 * @Description: [ 回款明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "receivable_receivablesubsidiary")
public class ReceivableSubsidiary extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目内部名称
     */
    @Column(name = "innerName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目内部名称'")
    private String innerName;

    /**
     * 派工单编号
     */
    @Column(name = "taskNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '派工单编号'")
    private String taskNum;

    /**
     * 派工单价
     */
    @Column(name = "taskPrice", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '派工单价'")
    private Double taskPrice;

    /**
     * 合同规模数
     */
    @Column(name = "pactNum", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '合同规模数'")
    private Double pactNum;

    /**
     * 合同规模金额(派工单价*合同规模数)
     */
    @Column(name = "pactMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '合同规模金额(派工单价*合同规模数)'")
    private Double pactMoney;

    /**
     * 已派工量
     */
    @Column(name = "pactSize", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '已派工量'")
    private Double pactSize;

    /**
     * 中兴派工金额(派工单价*已派工量)
     */
    @Column(name = "taskMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '中兴派工金额(派工单价*已派工量)'")
    private Double taskMoney;

    /**
     * 已完工量
     */
    @Column(name = "finishNum", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '已完工量'")
    private Double finishNum;

    /**
     * 已完工金额(派工单价*已完工量)
     */
    @Column(name = "finishMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '已完工金额(派工单价*已完工量)'")
    private Double finishMoney;

    /**
     * 未完工量
     */
    @Column(name = "unfinishNum", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '未完工量'")
    private Double unfinishNum;

    /**
     * 未完工金额(派工单价*未完工量)
     */
    @Column(name = "unfinishMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '未完工金额(派工单价*未完工量)'")
    private Double unfinishMoney;

    /**
     * 已交维数量
     */
    @Column(name = "paytax", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '已交维数量'")
    private Double paytax;

    /**
     * 未交维数量
     */
    @Column(name = "undeal", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '未交维数量'")
    private Double undeal;

    /**
     * 违约金
     */
    @Column(name = "penalty", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '违约金'")
    private Double penalty;

    /**
     * 实际结算数量
     */
    @Column(name = "realCountNum", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际结算数量'")
    private Double realCountNum;

    /**
     * 实际数量金额
     */
    @Column(name = "realCountMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际数量金额'")
    private Double realCountMoney;

    /**
     * 完工时间
     */
    @Column(name = "finishDate", nullable = false, columnDefinition = "DATE   COMMENT '完工时间'")
    private LocalDate finishDate;

    /**
     * 验收交维时间
     */
    @Column(name = "checkDate", nullable = false, columnDefinition = "DATE   COMMENT '验收交维时间'")
    private LocalDate checkDate;

    /**
     * 签字审批时间
     */
    @Column(name = "auditDate", nullable = false, columnDefinition = "DATE   COMMENT '签字审批时间'")
    private LocalDate auditDate;

    /**
     * 结算审批时间
     */
    @Column(name = "countDate", nullable = false, columnDefinition = "DATE   COMMENT '结算审批时间'")
    private LocalDate countDate;

    /**
     * 发票审核时间
     */
    @Column(name = "billDate", nullable = false, columnDefinition = "DATE   COMMENT '发票审核时间'")
    private LocalDate billDate;

    /**
     * 预计支付时间
     */
    @Column(name = "planDate", nullable = false, columnDefinition = "DATE   COMMENT '预计支付时间'")
    private LocalDate planDate;

    /**
     * 管理费(实际数量金额*承包商比例)
     */
    @Column(name = "managementFee", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '管理费(实际数量金额*承包商比例)'")
    private Double managementFee;

    /**
     * 到账时间
     */
    @Column(name = "accountDate", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '到账时间'")
    private Double accountDate;

    /**
     * 结算进度:A
     */
    @Column(name = "progressA", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '结算进度:A'")
    private String progressA;

    /**
     * 结算进度:B
     */
    @Column(name = "progressB", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '结算进度:B'")
    private String progressB;

    /**
     * 结算进度:C
     */
    @Column(name = "progressC", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '结算进度:C'")
    private String progressC;

    /**
     * 结算进度:D
     */
    @Column(name = "progressD", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '结算进度:D'")
    private String progressD;

    /**
     * 到账金额(实际数量金额-管理费)
     */
    @Column(name = "accountMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '到账金额(实际数量金额*管理费)'")
    private Double accountMoney;

    /**
     * 税金(到账金额*6.79%)
     */
    @Column(name = "taxes", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '税金(到账金额*6.79%)'")
    private Double taxes;

    /**
     * 税后金额(到账金额-税金)
     */
    @Column(name = "afterTax", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '税后金额(到账金额-税金)'")
    private Double afterTax;

    /**
     * 剩余结算量(已派工量-实际结算数量)
     */
    @Column(name = "moreNum", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '剩余结算量(已派工量-实际结算数量)'")
    private Double moreNum;

    /**
     * 剩余结算金额(派工单价*剩余结算量)
     */
    @Column(name = "moreMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '剩余结算金额(派工单价*剩余结算量)'")
    private Double moreMoney;

    /**
     * 承包商
     */
    @Column(name = "contractor", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '承包商'")
    private Contractor contractor;

    /**
     * 是否已支付
     */
    @Column(name = "is_ispay", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否已支付'", insertable = false)
    private Boolean ispay;

    /**
     * 是否框架内
     */
    @Column(name = "is_isframe", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否框架内'", insertable = false)
    private Boolean isframe;

    /**
     * 是否有单次合同
     */
    @Column(name = "is_ispact", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否有单次合同'", insertable = false)
    private Boolean ispact;

    /**
     * 是否已走结算流程
     */
    @Column(name = "is_isflow", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否已走结算流程'", insertable = false)
    private Boolean isflow;



    private AuditStatus auditStatus;//是否签字审核
    private AuditStatus countStatus;//是否结算审核
    private AuditStatus billStatus;//是否发票审核
    private AuditStatus planStatus;//是否预支付



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

    public Double getPaytax() {
        return paytax;
    }

    public void setPaytax(Double paytax) {
        this.paytax = paytax;
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

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public LocalDate getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(LocalDate auditDate) {
        this.auditDate = auditDate;
    }

    public LocalDate getCountDate() {
        return countDate;
    }

    public void setCountDate(LocalDate countDate) {
        this.countDate = countDate;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public LocalDate getPlanDate() {
        return planDate;
    }

    public void setPlanDate(LocalDate planDate) {
        this.planDate = planDate;
    }

    public Double getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(Double managementFee) {
        this.managementFee = managementFee;
    }

    public Double getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(Double accountDate) {
        this.accountDate = accountDate;
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

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
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

    public AuditStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public AuditStatus getCountStatus() {
        return countStatus;
    }

    public void setCountStatus(AuditStatus countStatus) {
        this.countStatus = countStatus;
    }

    public AuditStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(AuditStatus billStatus) {
        this.billStatus = billStatus;
    }

    public AuditStatus getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(AuditStatus planStatus) {
        this.planStatus = planStatus;
    }

    public ReceivableSubsidiary(String area, Double managementFee, Double accountMoney, Double taxes, Double taskMoney) {
        super();
        this.area  = area;
        this.managementFee = managementFee;
        this.accountMoney = accountMoney;
        this.taxes = taxes;
        this.taskMoney = taskMoney;
    }
}