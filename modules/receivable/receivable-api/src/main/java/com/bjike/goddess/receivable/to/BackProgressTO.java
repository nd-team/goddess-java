package com.bjike.goddess.receivable.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.receivable.entity.BackProgress;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 回款进度
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-10 02:52 ]
 * @Description: [ 回款进度 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BackProgressTO extends BaseTO {
    public interface TestEdit{}

    /**
     * 运营商名称
     */
    @NotBlank(message = "运营商名称不能为空",groups = {BackProgressTO.TestEdit.class})
    private String operatorName;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {BackProgressTO.TestEdit.class})
    private String area;

    /**
     * 外包单位
     */
    @NotBlank(message = "外包单位不能为空",groups = {BackProgressTO.TestEdit.class})
    private String contractor;

    /**
     * 类型
     */
    @NotBlank(message = "类型不能为空",groups = {BackProgressTO.TestEdit.class})
    private String type;

    /**
     * 专业
     */
    @NotBlank(message = "专业不能为空",groups = {BackProgressTO.TestEdit.class})
    private String major;

    /**
     * 派工名称
     */
    private String taskName;

    /**
     * 厂家
     */
    private String factory;

    /**
     * 销售合同号
     */
    private String salesContractNum;

    /**
     * 外包合同号
     */
    private String outsourceContractNum;

    /**
     * 派工情况
     */
    @NotBlank(message = "派工情况不能为空",groups = {BackProgressTO.TestEdit.class})
    private String taskCase;

    /**
     * 派工金额
     */
    private Double taskMoney;

    /**
     * 实际完工状态
     */
    @NotBlank(message = "实际完工状态不能为空",groups = {BackProgressTO.TestEdit.class})
    private String completeStatus;

    /**
     * 完工时间
     */
    private String completeTime;

    /**
     * 可结算金额
     */
    private Double clearingMoney;

    /**
     * 已到账金额
     */
    private Double accountMoney;

    /**
     * 制作结算资料
     */
    private String clearingData;

    /**
     * 软调报告
     */
    private String softCoreReport;

    /**
     * 签字
     */
    private String signature;

    /**
     * 结算进度:B
     */
    private String progressB;

    /**
     * 资料上传ERP系统
     */
    private String dataUploadSystem;

    /**
     * 群主（项目经理）审核
     */
    private String managerAudit;

    /**
     * 预接收申请
     */
    private String acceptApply;

    /**
     * 外包经理审核
     */
    private String outsourceAudit;

    /**
     * 办事处副总审批
     */
    private String officeAudit;

    /**
     * 结算进度C
     */
    private String progressC;

    /**
     * ERP系统开发票
     */
    private String systemInvoice;

    /**
     * 电子版发票审核
     */
    private String electronicInvoice;

    /**
     * 实物发票邮寄
     */
    private String physicalInvoice;

    /**
     * 填写发票查询快递
     */
    private String fillInvoice;

    /**
     * 实物发票审核完成
     */
    private String physicalInvoiceComplete;

    /**
     * 显示支付时间
     */
    private String payTime;

    /**
     * 开发票
     */
    @NotBlank(message = "开发票不能为空",groups = {BackProgressTO.TestEdit.class})
    private String invoice;

    /**
     * 预收帐款时间
     */
    @NotBlank(message = "预收帐款时间不能为空",groups = {BackProgressTO.TestEdit.class})
    private String advanceAccountTime;

    /**
     * 进度
     */
    private String progress;

    /**
     * 到货时间
     */
    private String arrivalTime;

    /**
     * KPI
     */
    private String kpi;

    /**
     * 现场实际情况（KPI）
     */
    private String actualSituation;

    /**
     * 设备型号
     */
    private String unitType;

    /**
     * 大概描述项目派工的情况备注
     */
    private String taskCaseRemark;

    /**
     * 总规模数
     */
    private Double scaleNum;

    /**
     * 是否可制作申请结算
     */
    private Boolean applyClearing;

    /**
     * 是否影响结算
     */
    private Boolean influenceClearing;

    /**
     * 结算计划
     */
    private String clearingPlan;

    /**
     * 正在执行项目
     */
    private String performProject;

    /**
     * 归属
     */
    private String ascription;

    /**
     * 备注
     */
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

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
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

    public String getClearingData() {
        return clearingData;
    }

    public void setClearingData(String clearingData) {
        this.clearingData = clearingData;
    }

    public String getSoftCoreReport() {
        return softCoreReport;
    }

    public void setSoftCoreReport(String softCoreReport) {
        this.softCoreReport = softCoreReport;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getProgressB() {
        return progressB;
    }

    public void setProgressB(String progressB) {
        this.progressB = progressB;
    }

    public String getDataUploadSystem() {
        return dataUploadSystem;
    }

    public void setDataUploadSystem(String dataUploadSystem) {
        this.dataUploadSystem = dataUploadSystem;
    }

    public String getManagerAudit() {
        return managerAudit;
    }

    public void setManagerAudit(String managerAudit) {
        this.managerAudit = managerAudit;
    }

    public String getAcceptApply() {
        return acceptApply;
    }

    public void setAcceptApply(String acceptApply) {
        this.acceptApply = acceptApply;
    }

    public String getOutsourceAudit() {
        return outsourceAudit;
    }

    public void setOutsourceAudit(String outsourceAudit) {
        this.outsourceAudit = outsourceAudit;
    }

    public String getOfficeAudit() {
        return officeAudit;
    }

    public void setOfficeAudit(String officeAudit) {
        this.officeAudit = officeAudit;
    }

    public String getProgressC() {
        return progressC;
    }

    public void setProgressC(String progressC) {
        this.progressC = progressC;
    }

    public String getSystemInvoice() {
        return systemInvoice;
    }

    public void setSystemInvoice(String systemInvoice) {
        this.systemInvoice = systemInvoice;
    }

    public String getElectronicInvoice() {
        return electronicInvoice;
    }

    public void setElectronicInvoice(String electronicInvoice) {
        this.electronicInvoice = electronicInvoice;
    }

    public String getPhysicalInvoice() {
        return physicalInvoice;
    }

    public void setPhysicalInvoice(String physicalInvoice) {
        this.physicalInvoice = physicalInvoice;
    }

    public String getFillInvoice() {
        return fillInvoice;
    }

    public void setFillInvoice(String fillInvoice) {
        this.fillInvoice = fillInvoice;
    }

    public String getPhysicalInvoiceComplete() {
        return physicalInvoiceComplete;
    }

    public void setPhysicalInvoiceComplete(String physicalInvoiceComplete) {
        this.physicalInvoiceComplete = physicalInvoiceComplete;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getAdvanceAccountTime() {
        return advanceAccountTime;
    }

    public void setAdvanceAccountTime(String advanceAccountTime) {
        this.advanceAccountTime = advanceAccountTime;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
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