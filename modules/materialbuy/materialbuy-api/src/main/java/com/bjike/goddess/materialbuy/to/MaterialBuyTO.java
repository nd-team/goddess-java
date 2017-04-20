package com.bjike.goddess.materialbuy.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.materialbuy.type.AuditState;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.MessageInterpolator;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 物资购买
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 04:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MaterialBuyTO extends BaseTO {

    public interface MaterialBuyAdd{}
    public interface MaterialBuyEdit{}
    public interface PrincipalAudit{}

    /**
     * 地区
     */
    @NotBlank(groups = {MaterialBuyAdd.class, MaterialBuyEdit.class}, message = "地区不能为空")
    private String area;

    /**
     * 项目组
     */
    @NotBlank(groups = {MaterialBuyAdd.class, MaterialBuyEdit.class}, message = "项目组不能为空")
    private String projectTeam;

    /**
     * 申购人
     */
    @NotBlank(groups = {MaterialBuyAdd.class, MaterialBuyEdit.class}, message = "申购人不能为空")
    private String requisitioner;

    /**
     * 申购日期
     */
    @NotBlank(groups = {MaterialBuyAdd.class, MaterialBuyEdit.class}, message = "申购日期不能为空")
    private String subscribeDate;

    /**
     * 设备类型
     */
    @NotBlank(groups = {MaterialBuyAdd.class, MaterialBuyEdit.class}, message = "申购日期不能为空")
    private String deviceType;

    /**
     * 设备名称
     */
    @NotBlank(groups = {MaterialBuyAdd.class, MaterialBuyEdit.class}, message = "设备名称不能为空")
    private String deviceName;

    /**
     * 型号
     */
    @NotBlank(groups = {MaterialBuyAdd.class, MaterialBuyEdit.class}, message = "型号不能为空")
    private String model;

    /**
     * 数量
     */
    @Min(value = 1, groups = {MaterialBuyAdd.class, MaterialBuyEdit.class}, message = "数量必须大于0")
    private Integer quantity;

    /**
     * 单位
     */
    @NotBlank(groups = {MaterialBuyAdd.class, MaterialBuyEdit.class}, message = "单位不能为空")
    private String unit;

    /**
     * 购买原因
     */
    @NotBlank(groups = {MaterialBuyAdd.class, MaterialBuyEdit.class}, message = "购买原因不能为空")
    private String buyReason;

    /**
     * 单价
     */
    @DecimalMin(value = "0.00", inclusive = false, groups = {MaterialBuyAdd.class, MaterialBuyEdit.class}, message = "单价必须大于0")
    private Double unitPrice;

    /**
     * 总额
     */
    @DecimalMin(value = "0.00", inclusive = false, groups = {MaterialBuyAdd.class, MaterialBuyEdit.class}, message = "总额必须大于0")
    private Double totalSum;

    /**
     * 途径
     */
    @NotBlank(groups = {MaterialBuyAdd.class, MaterialBuyEdit.class}, message = "途径不能为空")
    private String approach;

    /**
     * 购买网址/店铺
     */
    private String buyAddress;

    /**
     * 收件地址
     */
    private String deliveryAddress;

    /**
     * 收件人
     */
    private String receiver;

    /**
     * 收件人联系方式
     */
    private String receiverPhone;

    /**
     * 借款人
     */
    private String borrower;

    /**
     * 借款方式
     */
    private String borrowManner;

    /**
     * 是否为代写借支
     */
    private Boolean ifReplaceBorrow;

    /**
     * 预计借款时间
     */
    private String planBorrowTime;

    /**
     * 是否付款
     */
    private Boolean ifPayment;

    /**
     * 快递单号
     */
    private String expressNumber;

    /**
     * 备注(款打到谁的卡)
     */
    private String comment;

    /**
     * 地区审核人
     */
    @NotBlank(groups = {MaterialBuyAdd.class, MaterialBuyEdit.class}, message = "地区审核人不能为空")
    private String areaAuditor;

    /**
     * 审核状态
     */
    @NotNull(groups = {PrincipalAudit.class}, message = "审核状态不能为空")
    private AuditState auditState;

    /**
     * 审核意见
     */
    @NotNull(groups = {PrincipalAudit.class}, message = "审核意见不能为空")
    private String auditOpinion;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectTeam() {
        return projectTeam;
    }

    public void setProjectTeam(String projectTeam) {
        this.projectTeam = projectTeam;
    }

    public String getRequisitioner() {
        return requisitioner;
    }

    public void setRequisitioner(String requisitioner) {
        this.requisitioner = requisitioner;
    }

    public String getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(String subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBuyReason() {
        return buyReason;
    }

    public void setBuyReason(String buyReason) {
        this.buyReason = buyReason;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public String getApproach() {
        return approach;
    }

    public void setApproach(String approach) {
        this.approach = approach;
    }

    public String getBuyAddress() {
        return buyAddress;
    }

    public void setBuyAddress(String buyAddress) {
        this.buyAddress = buyAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getBorrowManner() {
        return borrowManner;
    }

    public void setBorrowManner(String borrowManner) {
        this.borrowManner = borrowManner;
    }

    public Boolean getIfReplaceBorrow() {
        return ifReplaceBorrow;
    }

    public void setIfReplaceBorrow(Boolean ifReplaceBorrow) {
        this.ifReplaceBorrow = ifReplaceBorrow;
    }

    public String getPlanBorrowTime() {
        return planBorrowTime;
    }

    public void setPlanBorrowTime(String planBorrowTime) {
        this.planBorrowTime = planBorrowTime;
    }

    public Boolean getIfPayment() {
        return ifPayment;
    }

    public void setIfPayment(Boolean ifPayment) {
        this.ifPayment = ifPayment;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAreaAuditor() {
        return areaAuditor;
    }

    public void setAreaAuditor(String areaAuditor) {
        this.areaAuditor = areaAuditor;
    }

    public AuditState getAuditState() {
        return auditState;
    }

    public void setAuditState(AuditState auditState) {
        this.auditState = auditState;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }
}