package com.bjike.goddess.devicerepair.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.devicerepair.type.AuditState;
import com.bjike.goddess.devicerepair.type.MaterialState;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 设备维修
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-03 02:59 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DeviceRepairTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 项目组
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目组不能为空")
    private String projectGroup;

    /**
     * 项目名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目名称不能为空")
    private String projectName;

    /**
     * 申请人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "申请人不能为空")
    private String applicant;

    /**
     * 设备名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "设备名称不能为空")
    private String deviceName;

    /**
     * 物资编号
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "物资编号不能为空")
    private String materialCoding;

    /**
     * 经手人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "经手人不能为空")
    private String handler;

    /**
     * 设备负责人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "设备负责人不能为空")
    private String devicePrincipal;

    /**
     * 设备出现的问题
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "设备出现的问题不能为空")
    private String deviceIssue;

    /**
     * 备注
     */
    private String comment;

    /**
     * 物资状态
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "物资状态不能为空")
    private MaterialState materialState;

    /**
     * 保修期限(月)
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "保修期限(月)不能为空")
    private Double termOfService;

    /**
     * 是否在保修期
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否在保修期不能为空")
    private Boolean whetherWarranty;

    /**
     * 保修联系人
     */
    private String warrantyContact;

    /**
     * 保修联系电话
     */
    private String warrantyContactPhone;

    /**
     * 购买网址/店铺
     */
    private String buyAddress;

    /**
     * 购买途径
     */
    private String purchaseWay;

    /**
     * 维修途径
     */
    private String repairWay;

    /**
     * 检测结果
     */
    private String detectResult;

    /**
     * 是否可维修
     */
    private Boolean whetherRepair;

    /**
     * 维修后使用期限
     */
    private String repairDeadline;

    /**
     * 维修价格
     */
    private Double repairPrice;

    /**
     * 预计维修好时间
     */
    private String planOverRepairTime;

    /**
     * 预计借款时间
     */
    private String planLoanTime;

    /**
     * 福利模块负责人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "福利模块负责人不能为空")
    private String welfareModule;

    /**
     * 福利模块负责人审核状态
     */
    private AuditState welfareAuditState;

    /**
     * 项目经理
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目经理不能为空")
    private String pm;

    /**
     * 项目经理审核状态
     */
    private AuditState pmAuditState;

    /**
     * 厂家收款方式
     */
    private String venderReceiptMode;

    /**
     * 开户名称
     */
    private String accountName;

    /**
     * 开户账号
     */
    private String accountNumber;

    /**
     * 开户行
     */
    private String bankOfDeposit;

    /**
     * 是否付款
     */
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

    public String getPlanOverRepairTime() {
        return planOverRepairTime;
    }

    public void setPlanOverRepairTime(String planOverRepairTime) {
        this.planOverRepairTime = planOverRepairTime;
    }

    public String getPlanLoanTime() {
        return planLoanTime;
    }

    public void setPlanLoanTime(String planLoanTime) {
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