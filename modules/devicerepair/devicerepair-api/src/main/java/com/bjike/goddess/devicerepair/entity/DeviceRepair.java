package com.bjike.goddess.devicerepair.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.devicerepair.type.AuditState;
import com.bjike.goddess.devicerepair.type.MaterialState;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 设备维修
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-03 02:59 ]
 * @Description: [ 设备维修 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "devicerepair_devicerepair")
public class DeviceRepair extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "projectGroup", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '项目组'")
    private String projectGroup;

    /**
     * 项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '项目名称'")
    private String projectName;

    /**
     * 申请人
     */
    @Column(name = "applicant", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '申请人'")
    private String applicant;

    /**
     * 设备名称
     */
    @Column(name = "deviceName", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '设备名称'")
    private String deviceName;

    /**
     * 物资编号
     */
    @Column(name = "materialCoding", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '物资编号'")
    private String materialCoding;

    /**
     * 经手人
     */
    @Column(name = "handler", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '经手人'")
    private String handler;

    /**
     * 设备负责人
     */
    @Column(name = "devicePrincipal", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '设备负责人'")
    private String devicePrincipal;

    /**
     * 设备出现的问题
     */
    @Column(name = "deviceIssue", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '设备出现的问题'")
    private String deviceIssue;

    /**
     * 备注
     */
    @Column(name = "comment", columnDefinition = "VARCHAR(255) COMMENT '备注'")
    private String comment;

    /**
     * 物资状态
     */
    @Column(name = "materialState", nullable = false, columnDefinition = "TINYINT(2) COMMENT '物资状态'")
    private MaterialState materialState;

    /**
     * 保修期限(月)
     */
    @Column(name = "termOfService", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '保修期限(月)'")
    private Double termOfService;

    /**
     * 是否在保修期
     */
    @Column(name = "whetherWarranty", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '是否在保修期'")
    private Boolean whetherWarranty;

    /**
     * 保修联系人
     */
    @Column(name = "warrantyContact", columnDefinition = "VARCHAR(255) COMMENT '保修联系人'")
    private String warrantyContact;

    /**
     * 保修联系电话
     */
    @Column(name = "warrantyContactPhone", columnDefinition = "VARCHAR(255) COMMENT '保修联系电话'")
    private String warrantyContactPhone;

    /**
     * 购买网址/店铺
     */
    @Column(name = "buyAddress", columnDefinition = "VARCHAR(255) COMMENT '购买网址/店铺'")
    private String buyAddress;

    /**
     * 购买途径
     */
    @Column(name = "purchaseWay", columnDefinition = "VARCHAR(255) COMMENT '购买途径'")
    private String purchaseWay;

    /**
     * 维修途径
     */
    @Column(name = "repairWay", columnDefinition = "VARCHAR(255) COMMENT '维修途径'")
    private String repairWay;

    /**
     * 检测结果
     */
    @Column(name = "detectResult", columnDefinition = "VARCHAR(255) COMMENT '检测结果'")
    private String detectResult;

    /**
     * 是否可维修
     */
    @Column(name = "whetherRepair", columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '是否可维修'")
    private Boolean whetherRepair;

    /**
     * 维修后使用期限
     */
    @Column(name = "repairDeadline", columnDefinition = "VARCHAR(255) COMMENT '维修后使用期限'")
    private String repairDeadline;

    /**
     * 维修价格
     */
    @Column(name = "repairPrice", columnDefinition = "DECIMAL(10,2) COMMENT '维修价格'")
    private Double repairPrice;

    /**
     * 预计维修好时间
     */
    @Column(name = "planOverRepairTime", columnDefinition = "DATETIME COMMENT '预计维修好时间'")
    private LocalDateTime planOverRepairTime;

    /**
     * 预计借款时间
     */
    @Column(name = "planLoanTime", columnDefinition = "DATETIME COMMENT '预计借款时间'")
    private LocalDateTime planLoanTime;

    /**
     * 福利模块负责人
     */
    @Column(name = "welfareModule", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '福利模块负责人'")
    private String welfareModule;

    /**
     * 福利模块负责人审核状态
     */
    @Column(name = "welfareAuditState", nullable = false, columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '福利模块负责人审核状态'")
    private AuditState welfareAuditState;

    /**
     * 项目经理
     */
    @Column(name = "pm", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '项目经理'")
    private String pm;

    /**
     * 项目经理审核状态
     */
    @Column(name = "pmAuditState", nullable = false, columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '项目经理审核状态'")
    private AuditState pmAuditState;

    /**
     * 厂家收款方式
     */
    @Column(name = "venderReceiptMode", columnDefinition = "VARCHAR(255) COMMENT '厂家收款方式'")
    private String venderReceiptMode;

    /**
     * 开户名称
     */
    @Column(name = "accountName", columnDefinition = "VARCHAR(255) COMMENT '开户名称'")
    private String accountName;

    /**
     * 开户账号
     */
    @Column(name = "accountNumber", columnDefinition = "VARCHAR(255) COMMENT '开户账号'")
    private String accountNumber;

    /**
     * 开户行
     */
    @Column(name = "bankOfDeposit", columnDefinition = "VARCHAR(255) COMMENT '开户行'")
    private String bankOfDeposit;

    /**
     * 是否付款
     */
    @Column(name = "whetherPayment", columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '是否付款'")
    private Boolean whetherPayment;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getMaterialCoding() {
        return materialCoding;
    }

    public void setMaterialCoding(String materialCoding) {
        this.materialCoding = materialCoding;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getDevicePrincipal() {
        return devicePrincipal;
    }

    public void setDevicePrincipal(String devicePrincipal) {
        this.devicePrincipal = devicePrincipal;
    }

    public String getDeviceIssue() {
        return deviceIssue;
    }

    public void setDeviceIssue(String deviceIssue) {
        this.deviceIssue = deviceIssue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public MaterialState getMaterialState() {
        return materialState;
    }

    public void setMaterialState(MaterialState materialState) {
        this.materialState = materialState;
    }

    public Double getTermOfService() {
        return termOfService;
    }

    public void setTermOfService(Double termOfService) {
        this.termOfService = termOfService;
    }

    public Boolean getWhetherWarranty() {
        return whetherWarranty;
    }

    public void setWhetherWarranty(Boolean whetherWarranty) {
        this.whetherWarranty = whetherWarranty;
    }

    public String getWarrantyContact() {
        return warrantyContact;
    }

    public void setWarrantyContact(String warrantyContact) {
        this.warrantyContact = warrantyContact;
    }

    public String getWarrantyContactPhone() {
        return warrantyContactPhone;
    }

    public void setWarrantyContactPhone(String warrantyContactPhone) {
        this.warrantyContactPhone = warrantyContactPhone;
    }

    public String getBuyAddress() {
        return buyAddress;
    }

    public void setBuyAddress(String buyAddress) {
        this.buyAddress = buyAddress;
    }

    public String getPurchaseWay() {
        return purchaseWay;
    }

    public void setPurchaseWay(String purchaseWay) {
        this.purchaseWay = purchaseWay;
    }

    public String getRepairWay() {
        return repairWay;
    }

    public void setRepairWay(String repairWay) {
        this.repairWay = repairWay;
    }

    public String getDetectResult() {
        return detectResult;
    }

    public void setDetectResult(String detectResult) {
        this.detectResult = detectResult;
    }

    public Boolean getWhetherRepair() {
        return whetherRepair;
    }

    public void setWhetherRepair(Boolean whetherRepair) {
        this.whetherRepair = whetherRepair;
    }

    public String getRepairDeadline() {
        return repairDeadline;
    }

    public void setRepairDeadline(String repairDeadline) {
        this.repairDeadline = repairDeadline;
    }

    public Double getRepairPrice() {
        return repairPrice;
    }

    public void setRepairPrice(Double repairPrice) {
        this.repairPrice = repairPrice;
    }

    public LocalDateTime getPlanOverRepairTime() {
        return planOverRepairTime;
    }

    public void setPlanOverRepairTime(LocalDateTime planOverRepairTime) {
        this.planOverRepairTime = planOverRepairTime;
    }

    public LocalDateTime getPlanLoanTime() {
        return planLoanTime;
    }

    public void setPlanLoanTime(LocalDateTime planLoanTime) {
        this.planLoanTime = planLoanTime;
    }

    public String getWelfareModule() {
        return welfareModule;
    }

    public void setWelfareModule(String welfareModule) {
        this.welfareModule = welfareModule;
    }

    public AuditState getWelfareAuditState() {
        return welfareAuditState;
    }

    public void setWelfareAuditState(AuditState welfareAuditState) {
        this.welfareAuditState = welfareAuditState;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public AuditState getPmAuditState() {
        return pmAuditState;
    }

    public void setPmAuditState(AuditState pmAuditState) {
        this.pmAuditState = pmAuditState;
    }

    public String getVenderReceiptMode() {
        return venderReceiptMode;
    }

    public void setVenderReceiptMode(String venderReceiptMode) {
        this.venderReceiptMode = venderReceiptMode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankOfDeposit() {
        return bankOfDeposit;
    }

    public void setBankOfDeposit(String bankOfDeposit) {
        this.bankOfDeposit = bankOfDeposit;
    }

    public Boolean getWhetherPayment() {
        return whetherPayment;
    }

    public void setWhetherPayment(Boolean whetherPayment) {
        this.whetherPayment = whetherPayment;
    }
}