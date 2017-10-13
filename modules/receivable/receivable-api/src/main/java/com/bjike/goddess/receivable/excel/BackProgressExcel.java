package com.bjike.goddess.receivable.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;

/**
 * 回款进度业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-10 02:52 ]
 * @Description: [ 回款进度业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BackProgressExcel {

    /**
     * 运营商名称
     */
    @ExcelHeader(name = "运营商名称", notNull = true)
    private String operatorName;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 外包单位
     */
    @ExcelHeader(name = "外包单位", notNull = true)
    private String contractor;

    /**
     * 类型
     */
    @ExcelHeader(name = "类型", notNull = true)
    private String type;

    /**
     * 专业
     */
    @ExcelHeader(name = "专业", notNull = true)
    private String major;

    /**
     * 派工名称
     */
    @ExcelHeader(name = "派工名称")
    private String taskName;

    /**
     * 厂家
     */
    @ExcelHeader(name = "厂家")
    private String factory;

    /**
     * 销售合同号
     */
    @ExcelHeader(name = "销售合同号")
    private String salesContractNum;

    /**
     * 外包合同号
     */
    @ExcelHeader(name = "外包合同号")
    private String outsourceContractNum;

    /**
     * 派工情况
     */
    @ExcelHeader(name = "派工情况", notNull = true)
    private String taskCase;

    /**
     * 派工金额
     */
    @ExcelHeader(name = "派工金额")
    private Double taskMoney;

    /**
     * 实际完工状态
     */
    @ExcelHeader(name = "实际完工状态", notNull = true)
    private String completeStatus;

    /**
     * 完工时间
     */
    @ExcelHeader(name = "完工时间")
    private LocalDate completeTime;

    /**
     * 可结算金额
     */
    @ExcelHeader(name = "可结算金额")
    private Double clearingMoney;

    /**
     * 已到账金额
     */
    @ExcelHeader(name = "已到账金额")
    private Double accountMoney;

    /**
     * 制作结算资料
     */
    @ExcelHeader(name = "制作结算资料")
    private LocalDate clearingData;

    /**
     * 软调报告
     */
    @ExcelHeader(name = "软调报告")
    private LocalDate softCoreReport;

    /**
     * 签字
     */
    @ExcelHeader(name = "签字")
    private LocalDate signature;

    /**
     * 结算进度:B
     */
    @ExcelHeader(name = "结算进度:B")
    private String progressB;

    /**
     * 资料上传ERP系统
     */
    @ExcelHeader(name = "资料上传ERP系统")
    private LocalDate dataUploadSystem;

    /**
     * 群主（项目经理）审核
     */
    @ExcelHeader(name = "群主（项目经理）审核")
    private LocalDate managerAudit;

    /**
     * 预接收申请
     */
    @ExcelHeader(name = "预接收申请")
    private LocalDate acceptApply;

    /**
     * 外包经理审核
     */
    @ExcelHeader(name = "外包经理审核")
    private LocalDate outsourceAudit;

    /**
     * 办事处副总审批
     */
    @ExcelHeader(name = "办事处副总审批")
    private LocalDate officeAudit;

    /**
     * 结算进度C
     */
    @ExcelHeader(name = "结算进度C")
    private String progressC;

    /**
     * ERP系统开发票
     */
    @ExcelHeader(name = "ERP系统开发票")
    private LocalDate systemInvoice;

    /**
     * 电子版发票审核
     */
    @ExcelHeader(name = "电子版发票审核")
    private LocalDate electronicInvoice;

    /**
     * 实物发票邮寄
     */
    @ExcelHeader(name = "实物发票邮寄")
    private LocalDate physicalInvoice;

    /**
     * 填写发票查询快递
     */
    @ExcelHeader(name = "填写发票查询快递")
    private LocalDate fillInvoice;

    /**
     * 实物发票审核完成
     */
    @ExcelHeader(name = "实物发票审核完成")
    private LocalDate physicalInvoiceComplete;

    /**
     * 显示支付时间
     */
    @ExcelHeader(name = "显示支付时间")
    private LocalDate payTime;

    /**
     * 开发票
     */
    @ExcelHeader(name = "开发票", notNull = true)
    private LocalDate invoice;

    /**
     * 预收帐款时间
     */
    @ExcelHeader(name = "预收帐款时间", notNull = true)
    private LocalDate advanceAccountTime;

    /**
     * 进度
     */
    @ExcelHeader(name = "进度")
    private String progress;

    /**
     * 到货时间
     */
    @ExcelHeader(name = "到货时间")
    private LocalDate arrivalTime;

    /**
     * KPI
     */
    @ExcelHeader(name = "KPI")
    private String kpi;

    /**
     * 现场实际情况（KPI）
     */
    @ExcelHeader(name = "现场实际情况（KPI）")
    private String actualSituation;

    /**
     * 设备型号
     */
    @ExcelHeader(name = "设备型号")
    private String unitType;

    /**
     * 大概描述项目派工的情况备注
     */
    @ExcelHeader(name = "大概描述项目派工的情况备注")
    private String taskCaseRemark;

    /**
     * 总规模数
     */
    @ExcelHeader(name = "总规模数")
    private Double scaleNum;

    /**
     * 是否可制作申请结算
     */
    @ExcelHeader(name = "是否可制作申请结算")
    private Boolean applyClearing;

    /**
     * 是否影响结算
     */
    @ExcelHeader(name = "是否影响结算")
    private Boolean influenceClearing;

    /**
     * 结算计划
     */
    @ExcelHeader(name = "结算计划")
    private String clearingPlan;

    /**
     * 正在执行项目
     */
    @ExcelHeader(name = "正在执行项目")
    private String performProject;

    /**
     * 归属
     */
    @ExcelHeader(name = "归属")
    private String ascription;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注")
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

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
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