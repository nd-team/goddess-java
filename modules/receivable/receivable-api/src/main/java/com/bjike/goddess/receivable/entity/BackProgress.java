package com.bjike.goddess.receivable.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 回款进度
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-10 02:52 ]
 * @Description: [ 回款进度 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "receivable_backprogress")
public class BackProgress extends BaseEntity {

    /**
     * 运营商名称
     */
    @Column(name = "operatorName",  columnDefinition = "VARCHAR(255)   COMMENT '运营商名称'")
    private String operatorName;

    /**
     * 地区
     */
    @Column(name = "area",  columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 外包单位
     */
    @Column(name = "contractor",  columnDefinition = "VARCHAR(255)   COMMENT '外包单位'")
    private String contractor;

    /**
     * 类型
     */
    @Column(name = "type",  columnDefinition = "VARCHAR(255)   COMMENT '类型'")
    private String type;

    /**
     * 专业
     */
    @Column(name = "major",  columnDefinition = "VARCHAR(255)   COMMENT '专业'")
    private String major;

    /**
     * 派工名称
     */
    @Column(name = "taskName",  columnDefinition = "VARCHAR(255)   COMMENT '派工名称'")
    private String taskName;

    /**
     * 厂家
     */
    @Column(name = "factory",  columnDefinition = "VARCHAR(255)   COMMENT '厂家'")
    private String factory;

    /**
     * 销售合同号
     */
    @Column(name = "salesContractNum",  columnDefinition = "VARCHAR(255)   COMMENT '销售合同号'")
    private String salesContractNum;

    /**
     * 外包合同号
     */
    @Column(name = "outsourceContractNum",  columnDefinition = "VARCHAR(255)   COMMENT '外包合同号'")
    private String outsourceContractNum;

    /**
     * 派工情况
     */
    @Column(name = "taskMoney",  columnDefinition = "VARCHAR(255)   COMMENT '派工情况'")
    private String taskCase;

    /**
     * 派工金额
     */
    @Column(name = "taskCase",  columnDefinition = "DECIMAL(10,2)   COMMENT '派工金额'")
    private Double taskMoney;

    /**
     * 实际完工状态
     */
    @Column(name = "completeStatus",  columnDefinition = "VARCHAR(255)   COMMENT '实际完工状态'")
    private String completeStatus;

    /**
     * 完工时间
     */
    @Column(name = "completeTime",  columnDefinition = "DATE   COMMENT '完工时间'")
    private LocalDate completeTime;

    /**
     * 可结算金额
     */
    @Column(name = "clearingMoney",  columnDefinition = "DECIMAL(10,2)   COMMENT '可结算金额'")
    private Double clearingMoney;

    /**
     * 已到账金额
     */
    @Column(name = "accountMoney",  columnDefinition = "DECIMAL(10,2)   COMMENT '已到账金额'")
    private Double accountMoney;

    /**
     * 制作结算资料
     */
    @Column(name = "clearingData",  columnDefinition = "DATE   COMMENT '制作结算资料'")
    private LocalDate clearingData;

    /**
     * 软调报告
     */
    @Column(name = "softCoreReport",  columnDefinition = "DATE   COMMENT '软调报告'")
    private LocalDate softCoreReport;

    /**
     * 签字
     */
    @Column(name = "signature",  columnDefinition = "DATE   COMMENT '签字'")
    private LocalDate signature;

    /**
     * 结算进度:B
     */
    @Column(name = "progressB",  columnDefinition = "VARCHAR(255)   COMMENT '结算进度:B'")
    private String progressB;

    /**
     * 资料上传ERP系统
     */
    @Column(name = "dataUploadSystem",  columnDefinition = "DATE   COMMENT '资料上传ERP系统'")
    private LocalDate dataUploadSystem;

    /**
     * 群主（项目经理）审核
     */
    @Column(name = "managerAudit",  columnDefinition = "DATE   COMMENT '群主（项目经理）审核'")
    private LocalDate managerAudit;

    /**
     * 预接收申请
     */
    @Column(name = "acceptApply",  columnDefinition = "DATE   COMMENT '预接收申请'")
    private LocalDate acceptApply;

    /**
     * 外包经理审核
     */
    @Column(name = "outsourceAudit",  columnDefinition = "DATE   COMMENT '外包经理审核'")
    private LocalDate outsourceAudit;

    /**
     * 办事处副总审批
     */
    @Column(name = "officeAudit",  columnDefinition = "DATE   COMMENT '办事处副总审批'")
    private LocalDate officeAudit;

    /**
     * 结算进度C
     */
    @Column(name = "progressC",  columnDefinition = "VARCHAR(255)   COMMENT '结算进度C'")
    private String progressC;

    /**
     * ERP系统开发票
     */
    @Column(name = "systemInvoice",  columnDefinition = "DATE   COMMENT 'ERP系统开发票'")
    private LocalDate systemInvoice;

    /**
     * 电子版发票审核
     */
    @Column(name = "electronicInvoice",  columnDefinition = "DATE   COMMENT '电子版发票审核'")
    private LocalDate electronicInvoice;

    /**
     * 实物发票邮寄
     */
    @Column(name = "physicalInvoice",  columnDefinition = "DATE   COMMENT '实物发票邮寄'")
    private LocalDate physicalInvoice;

    /**
     * 填写发票查询快递
     */
    @Column(name = "fillInvoice",  columnDefinition = "DATE   COMMENT '填写发票查询快递'")
    private LocalDate fillInvoice;

    /**
     * 实物发票审核完成
     */
    @Column(name = "physicalInvoiceComplete",  columnDefinition = "DATE   COMMENT '实物发票审核完成'")
    private LocalDate physicalInvoiceComplete;

    /**
     * 显示支付时间
     */
    @Column(name = "payTime",  columnDefinition = "DATE   COMMENT '显示支付时间'")
    private LocalDate payTime;

    /**
     * 开发票
     */
    @Column(name = "invoice",  columnDefinition = "DATE   COMMENT '开发票'")
    private LocalDate invoice;

    /**
     * 预收帐款时间
     */
    @Column(name = "advanceAccountTime",  columnDefinition = "DATE   COMMENT '预收帐款时间'")
    private LocalDate advanceAccountTime;

    /**
     * 进度
     */
    @Column(name = "progress",  columnDefinition = "DATE   COMMENT '进度'")
    private LocalDate progress;

    /**
     * 到货时间
     */
    @Column(name = "arrivalTime",  columnDefinition = "DATE   COMMENT '到货时间'")
    private LocalDate arrivalTime;

    /**
     * KPI
     */
    @Column(name = "kpi",  columnDefinition = "VARCHAR(255)   COMMENT 'KPI'")
    private String kpi;

    /**
     * 现场实际情况（KPI）
     */
    @Column(name = "actualSituation",  columnDefinition = "VARCHAR(255)   COMMENT '现场实际情况（KPI）'")
    private String actualSituation;

    /**
     * 设备型号
     */
    @Column(name = "unitType",  columnDefinition = "VARCHAR(255)   COMMENT '设备型号'")
    private String unitType;

    /**
     * 大概描述项目派工的情况备注
     */
    @Column(name = "taskCaseRemark",  columnDefinition = "VARCHAR(255)   COMMENT '大概描述项目派工的情况备注'")
    private String taskCaseRemark;

    /**
     * 总规模数
     */
    @Column(name = "scaleNum",  columnDefinition = "DECIMAL(10,2)   COMMENT '总规模数'")
    private Double scaleNum;

    /**
     * 是否可制作申请结算
     */
    @Column(name = "is_applyClearing",  columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否可制作申请结算'", insertable = false)
    private Boolean applyClearing;

    /**
     * 是否影响结算
     */
    @Column(name = "is_influenceClearing",  columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否影响结算'", insertable = false)
    private Boolean influenceClearing;

    /**
     * 结算计划
     */
    @Column(name = "clearingPlan",  columnDefinition = "VARCHAR(255)   COMMENT '结算计划'")
    private String clearingPlan;

    /**
     * 正在执行项目
     */
    @Column(name = "performProject",  columnDefinition = "VARCHAR(255)   COMMENT '正在执行项目'")
    private String performProject;

    /**
     * 归属
     */
    @Column(name = "ascription",  columnDefinition = "VARCHAR(255)   COMMENT '归属'")
    private String ascription;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getSalesContractNum() {
        return salesContractNum;
    }

    public void setSalesContractNum(String salesContractNum) {
        this.salesContractNum = salesContractNum;
    }

    public String getOutsourceContractNum() {
        return outsourceContractNum;
    }

    public void setOutsourceContractNum(String outsourceContractNum) {
        this.outsourceContractNum = outsourceContractNum;
    }

    public String getTaskCase() {
        return taskCase;
    }

    public void setTaskCase(String taskCase) {
        this.taskCase = taskCase;
    }

    public Double getTaskMoney() {
        return taskMoney;
    }

    public void setTaskMoney(Double taskMoney) {
        this.taskMoney = taskMoney;
    }

    public String getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(String completeStatus) {
        this.completeStatus = completeStatus;
    }

    public LocalDate getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(LocalDate completeTime) {
        this.completeTime = completeTime;
    }

    public Double getClearingMoney() {
        return clearingMoney;
    }

    public void setClearingMoney(Double clearingMoney) {
        this.clearingMoney = clearingMoney;
    }

    public Double getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(Double accountMoney) {
        this.accountMoney = accountMoney;
    }

    public LocalDate getClearingData() {
        return clearingData;
    }

    public void setClearingData(LocalDate clearingData) {
        this.clearingData = clearingData;
    }

    public LocalDate getSoftCoreReport() {
        return softCoreReport;
    }

    public void setSoftCoreReport(LocalDate softCoreReport) {
        this.softCoreReport = softCoreReport;
    }

    public LocalDate getSignature() {
        return signature;
    }

    public void setSignature(LocalDate signature) {
        this.signature = signature;
    }

    public String getProgressB() {
        return progressB;
    }

    public void setProgressB(String progressB) {
        this.progressB = progressB;
    }

    public LocalDate getDataUploadSystem() {
        return dataUploadSystem;
    }

    public void setDataUploadSystem(LocalDate dataUploadSystem) {
        this.dataUploadSystem = dataUploadSystem;
    }

    public LocalDate getManagerAudit() {
        return managerAudit;
    }

    public void setManagerAudit(LocalDate managerAudit) {
        this.managerAudit = managerAudit;
    }

    public LocalDate getAcceptApply() {
        return acceptApply;
    }

    public void setAcceptApply(LocalDate acceptApply) {
        this.acceptApply = acceptApply;
    }

    public LocalDate getOutsourceAudit() {
        return outsourceAudit;
    }

    public void setOutsourceAudit(LocalDate outsourceAudit) {
        this.outsourceAudit = outsourceAudit;
    }

    public LocalDate getOfficeAudit() {
        return officeAudit;
    }

    public void setOfficeAudit(LocalDate officeAudit) {
        this.officeAudit = officeAudit;
    }

    public String getProgressC() {
        return progressC;
    }

    public void setProgressC(String progressC) {
        this.progressC = progressC;
    }

    public LocalDate getSystemInvoice() {
        return systemInvoice;
    }

    public void setSystemInvoice(LocalDate systemInvoice) {
        this.systemInvoice = systemInvoice;
    }

    public LocalDate getElectronicInvoice() {
        return electronicInvoice;
    }

    public void setElectronicInvoice(LocalDate electronicInvoice) {
        this.electronicInvoice = electronicInvoice;
    }

    public LocalDate getPhysicalInvoice() {
        return physicalInvoice;
    }

    public void setPhysicalInvoice(LocalDate physicalInvoice) {
        this.physicalInvoice = physicalInvoice;
    }

    public LocalDate getFillInvoice() {
        return fillInvoice;
    }

    public void setFillInvoice(LocalDate fillInvoice) {
        this.fillInvoice = fillInvoice;
    }

    public LocalDate getPhysicalInvoiceComplete() {
        return physicalInvoiceComplete;
    }

    public void setPhysicalInvoiceComplete(LocalDate physicalInvoiceComplete) {
        this.physicalInvoiceComplete = physicalInvoiceComplete;
    }

    public LocalDate getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDate payTime) {
        this.payTime = payTime;
    }

    public LocalDate getInvoice() {
        return invoice;
    }

    public void setInvoice(LocalDate invoice) {
        this.invoice = invoice;
    }

    public LocalDate getAdvanceAccountTime() {
        return advanceAccountTime;
    }

    public void setAdvanceAccountTime(LocalDate advanceAccountTime) {
        this.advanceAccountTime = advanceAccountTime;
    }

    public LocalDate getProgress() {
        return progress;
    }

    public void setProgress(LocalDate progress) {
        this.progress = progress;
    }

    public LocalDate getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDate arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getKpi() {
        return kpi;
    }

    public void setKpi(String kpi) {
        this.kpi = kpi;
    }

    public String getActualSituation() {
        return actualSituation;
    }

    public void setActualSituation(String actualSituation) {
        this.actualSituation = actualSituation;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getTaskCaseRemark() {
        return taskCaseRemark;
    }

    public void setTaskCaseRemark(String taskCaseRemark) {
        this.taskCaseRemark = taskCaseRemark;
    }

    public Double getScaleNum() {
        return scaleNum;
    }

    public void setScaleNum(Double scaleNum) {
        this.scaleNum = scaleNum;
    }

    public Boolean getApplyClearing() {
        return applyClearing;
    }

    public void setApplyClearing(Boolean applyClearing) {
        this.applyClearing = applyClearing;
    }

    public Boolean getInfluenceClearing() {
        return influenceClearing;
    }

    public void setInfluenceClearing(Boolean influenceClearing) {
        this.influenceClearing = influenceClearing;
    }

    public String getClearingPlan() {
        return clearingPlan;
    }

    public void setClearingPlan(String clearingPlan) {
        this.clearingPlan = clearingPlan;
    }

    public String getPerformProject() {
        return performProject;
    }

    public void setPerformProject(String performProject) {
        this.performProject = performProject;
    }

    public String getAscription() {
        return ascription;
    }

    public void setAscription(String ascription) {
        this.ascription = ascription;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}