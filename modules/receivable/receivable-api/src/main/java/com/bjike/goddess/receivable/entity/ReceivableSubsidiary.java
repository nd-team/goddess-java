package com.bjike.goddess.receivable.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.receivable.enums.AuditStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


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
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目内部名称
     */
    @Column(name = "innerName", columnDefinition = "VARCHAR(255)   COMMENT '项目内部名称'")
    private String innerName;

    /**
     * 派工单编号
     */
    @Column(name = "taskNum", columnDefinition = "VARCHAR(255)   COMMENT '派工单编号'")
    private String taskNum;

    /**
     * 派工单价
     */
    @Column(name = "taskPrice", columnDefinition = "DECIMAL(10,2)   COMMENT '派工单价'")
    private Double taskPrice;

    /**
     * 合同规模数
     */
    @Column(name = "pactNum", columnDefinition = "DECIMAL(10,2)   COMMENT '合同规模数'")
    private Double pactNum;

    /**
     * 合同规模金额(派工单价*合同规模数)
     */
    @Column(name = "pactMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '合同规模金额(派工单价*合同规模数)'")
    private Double pactMoney;

    /**
     * 已派工量
     */
    @Column(name = "pactSize", columnDefinition = "DECIMAL(10,2)   COMMENT '已派工量'")
    private Double pactSize;

    /**
     * 中兴派工金额(派工单价*已派工量)
     */
    @Column(name = "taskMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '中兴派工金额(派工单价*已派工量)'")
    private Double taskMoney;

    /**
     * 已完工量
     */
    @Column(name = "finishNum", columnDefinition = "DECIMAL(10,2)   COMMENT '已完工量'")
    private Double finishNum;

    /**
     * 已完工金额(派工单价*已完工量)
     */
    @Column(name = "finishMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '已完工金额(派工单价*已完工量)'")
    private Double finishMoney;

    /**
     * 未完工量
     */
    @Column(name = "unfinishNum", columnDefinition = "DECIMAL(10,2)   COMMENT '未完工量'")
    private Double unfinishNum;

    /**
     * 未完工金额(派工单价*未完工量)
     */
    @Column(name = "unfinishMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '未完工金额(派工单价*未完工量)'")
    private Double unfinishMoney;

    /**
     * 已交维数量
     */
    @Column(name = "payTax", columnDefinition = "DECIMAL(10,2)   COMMENT '已交维数量'")
    private Double payTax;

    /**
     * 未交维数量
     */
    @Column(name = "undeal", columnDefinition = "DECIMAL(10,2)   COMMENT '未交维数量'")
    private Double undeal;

    /**
     * 违约金
     */
    @Column(name = "penalty", columnDefinition = "DECIMAL(10,2)   COMMENT '违约金'")
    private Double penalty;

    /**
     * 实际结算数量
     */
    @Column(name = "realCountNum", columnDefinition = "DECIMAL(10,2)   COMMENT '实际结算数量'")
    private Double realCountNum;

    /**
     * 实际数量金额
     */
    @Column(name = "realCountMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '实际数量金额'")
    private Double realCountMoney;

    /**
     * 完工时间
     */
    @Column(name = "finishTime", columnDefinition = "DATE   COMMENT '完工时间'")
    private LocalDate finishTime;

    /**
     * 验收交维时间
     */
    @Column(name = "checkTime", columnDefinition = "DATE   COMMENT '验收交维时间'")
    private LocalDate checkTime;

    /**
     * 签字审批时间
     */
    @Column(name = "auditTime", columnDefinition = "DATE   COMMENT '签字审批时间'")
    private LocalDate auditTime;

    /**
     * 结算审批时间
     */
    @Column(name = "countTime", columnDefinition = "DATE   COMMENT '结算审批时间'")
    private LocalDate countTime;

    /**
     * 发票审核时间
     */
    @Column(name = "billTime", columnDefinition = "DATE   COMMENT '发票审核时间'")
    private LocalDate billTime;

    /**
     * 预计支付时间
     */
    @Column(name = "planTime", columnDefinition = "DATE   COMMENT '预计支付时间'")
    private LocalDate planTime;

    /**
     * 管理费(实际数量金额*承包商比例)
     */
    @Column(name = "managementFee", columnDefinition = "DECIMAL(10,2)   COMMENT '管理费(实际数量金额*承包商比例)'")
    private Double managementFee;

    /**
     * 到账时间
     */
    @Column(name = "accountTime", columnDefinition = "DATE   COMMENT '到账时间'")
    private LocalDate accountTime;

    /**
     * 结算进度:A
     */
    @Column(name = "progressA", columnDefinition = "VARCHAR(255)   COMMENT '结算进度:A'")
    private String progressA;

    /**
     * 结算进度:B
     */
    @Column(name = "progressB", columnDefinition = "VARCHAR(255)   COMMENT '结算进度:B'")
    private String progressB;

    /**
     * 结算进度:C
     */
    @Column(name = "progressC", columnDefinition = "VARCHAR(255)   COMMENT '结算进度:C'")
    private String progressC;

    /**
     * 结算进度:D
     */
    @Column(name = "progressD", columnDefinition = "VARCHAR(255)   COMMENT '结算进度:D'")
    private String progressD;

    /**
     * 到账金额(实际数量金额-管理费)
     */
    @Column(name = "accountMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '到账金额(实际数量金额*管理费)'")
    private Double accountMoney;

    /**
     * 税金(到账金额*6.79%)
     */
    @Column(name = "taxes", columnDefinition = "DECIMAL(10,2)   COMMENT '税金(到账金额*6.79%)'")
    private Double taxes;

    /**
     * 税后金额(到账金额-税金)
     */
    @Column(name = "afterTax", columnDefinition = "DECIMAL(10,2)   COMMENT '税后金额(到账金额-税金)'")
    private Double afterTax;

    /**
     * 剩余结算量(已派工量-实际结算数量)
     */
    @Column(name = "moreNum", columnDefinition = "DECIMAL(10,2)   COMMENT '剩余结算量(已派工量-实际结算数量)'")
    private Double moreNum;

    /**
     * 剩余结算金额(派工单价*剩余结算量)
     */
    @Column(name = "moreMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '剩余结算金额(派工单价*剩余结算量)'")
    private Double moreMoney;

    /**
     * 承包商
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinColumn(name = "contractor_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '承包商'")
    private Contractor contractor;

    /**
     * 是否已支付
     */
    @Column(name = "is_pay", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否已支付'", insertable = false)
    private Boolean ispay;

    /**
     * 是否框架内
     */
    @Column(name = "is_frame", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否框架内'", insertable = false)
    private Boolean isframe;

    /**
     * 是否有单次合同
     */
    @Column(name = "is_pact", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否有单次合同'", insertable = false)
    private Boolean ispact;

    /**
     * 是否已走结算流程
     */
    @Column(name = "is_flow", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否已走结算流程'", insertable = false)
    private Boolean isflow;
    /**
     * 描述
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String remark;


    private AuditStatus auditStatus;//是否签字审核
    private AuditStatus countStatus;//是否结算审核
    private AuditStatus billStatus;//是否发票审核
    private AuditStatus planStatus;//是否预支付

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Double getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(Double managementFee) {
        this.managementFee = managementFee;
    }

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

}