package com.bjike.goddess.materialtransfer.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.materialtransfer.entity.MaterialTransfer;
import com.bjike.goddess.materialtransfer.type.AuditState;
import com.bjike.goddess.materialtransfer.type.MaterialSource;
import com.bjike.goddess.materialtransfer.type.MaterialState;
import com.bjike.goddess.materialtransfer.type.TransferWay;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 物资调动
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-28 10:47 ]
 * @Description: [ 物资调动 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MaterialTransferTO extends BaseTO {

    /**
     * 申请人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "申请人不能为空")
    private String proposer;

    /**
     * 设备类型
     */
    @NotBlank(groups = {EDIT.class}, message = "设备类型不能为空")
    private String materialType;

    /**
     * 物资名称
     */
    @NotBlank(groups = {EDIT.class}, message = "设备名称不能为空")
    private String materialName;

    /**
     * 型号
     */
    @NotBlank(groups = {EDIT.class}, message = "型号不能为空")
    private String model;

    /**
     * imei号
     */
    private String imeiCode;

    /**
     * 入库编码
     */
    @NotBlank(groups = {ADD.class}, message = "入库编码不能为空")
    private String instockCode;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 单位
     */
    @NotBlank(groups = {EDIT.class}, message = "单位不能为空")
    private String unit;

    /**
     * 调入地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "调入地区不能为空")
    private String transferredArea;

    /**
     * 申请日期
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "申请日期不能为空")
    private String applyDate;

    /**
     * 用途
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "用途不能为空")
    private String purpose;

    /**
     * 经手人
     */
    private String handler;

    /**
     * 备注
     */
    private String comment;

    /**
     * 原存储地区项目经理
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "原存储地区项目经理不能为空")
    private String originalPM;

    /**
     * 项目经理审核状态
     */
    private AuditState pmAuditState;

    /**
     * 福利模块负责人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "福利模块负责人不能为空")
    private String welfareModule;

    /**
     * 福利模块负责人审核状态
     */
    private AuditState welfareState;

    /**
     * 原存储地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "原存储地区不能为空")
    private String archSaveArea;

    /**
     * 调动途径
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "调动途径不能为空")
    private TransferWay transferWay;

    /**
     * 快递费用
     */
    private Double expressCharge;

    /**
     * 物资来源
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "物资来源不能为空")
    private MaterialSource materialSource;

    /**
     * 物资状态
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "物资状态不能为空")
    private MaterialState materialState;

    /**
     * 调动时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "调动时间不能为空")
    private String transferTime;

    /**
     * 物资负责人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "物资负责人为空")
    private String materialPrincipal;

    /**
     * 领用人
     */
    private String recipient;

    /**
     * 福利模块负责人确认调配成功
     */
    private Boolean confirmDeploy;

    /**
     * 调配完成时间
     */
    private String finishDeployTime;


    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImeiCode() {
        return imeiCode;
    }

    public void setImeiCode(String imeiCode) {
        this.imeiCode = imeiCode;
    }

    public String getInstockCode() {
        return instockCode;
    }

    public void setInstockCode(String instockCode) {
        this.instockCode = instockCode;
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

    public String getTransferredArea() {
        return transferredArea;
    }

    public void setTransferredArea(String transferredArea) {
        this.transferredArea = transferredArea;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOriginalPM() {
        return originalPM;
    }

    public void setOriginalPM(String originalPM) {
        this.originalPM = originalPM;
    }

    public AuditState getPmAuditState() {
        return pmAuditState;
    }

    public void setPmAuditState(AuditState pmAuditState) {
        this.pmAuditState = pmAuditState;
    }

    public String getWelfareModule() {
        return welfareModule;
    }

    public void setWelfareModule(String welfareModule) {
        this.welfareModule = welfareModule;
    }

    public AuditState getWelfareState() {
        return welfareState;
    }

    public void setWelfareState(AuditState welfareState) {
        this.welfareState = welfareState;
    }

    public String getArchSaveArea() {
        return archSaveArea;
    }

    public void setArchSaveArea(String archSaveArea) {
        this.archSaveArea = archSaveArea;
    }

    public TransferWay getTransferWay() {
        return transferWay;
    }

    public void setTransferWay(TransferWay transferWay) {
        this.transferWay = transferWay;
    }

    public Double getExpressCharge() {
        return expressCharge;
    }

    public void setExpressCharge(Double expressCharge) {
        this.expressCharge = expressCharge;
    }

    public MaterialSource getMaterialSource() {
        return materialSource;
    }

    public void setMaterialSource(MaterialSource materialSource) {
        this.materialSource = materialSource;
    }

    public MaterialState getMaterialState() {
        return materialState;
    }

    public void setMaterialState(MaterialState materialState) {
        this.materialState = materialState;
    }

    public String getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(String transferTime) {
        this.transferTime = transferTime;
    }

    public String getMaterialPrincipal() {
        return materialPrincipal;
    }

    public void setMaterialPrincipal(String materialPrincipal) {
        this.materialPrincipal = materialPrincipal;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Boolean getConfirmDeploy() {
        return confirmDeploy;
    }

    public void setConfirmDeploy(Boolean confirmDeploy) {
        this.confirmDeploy = confirmDeploy;
    }

    public String getFinishDeployTime() {
        return finishDeployTime;
    }

    public void setFinishDeployTime(String finishDeployTime) {
        this.finishDeployTime = finishDeployTime;
    }
}