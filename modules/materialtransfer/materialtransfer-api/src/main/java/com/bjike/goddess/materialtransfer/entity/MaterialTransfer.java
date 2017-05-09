package com.bjike.goddess.materialtransfer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.materialtransfer.type.AuditState;
import com.bjike.goddess.materialtransfer.type.MaterialSource;
import com.bjike.goddess.materialtransfer.type.MaterialState;
import com.bjike.goddess.materialtransfer.type.TransferWay;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 物资调动
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-28 10:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "materialtransfer_materialtransfer")
public class MaterialTransfer extends BaseEntity {

    /**
     * 申请人
     */
    @Column(name = "proposer", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '申请人'")
    private String proposer;

    /**
     * 设备类型
     */
    @Column(name = "materialType", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '设备类型'")
    private String materialType;

    /**
     * 物资名称
     */
    @Column(name = "materialName", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '物资名称'")
    private String materialName;

    /**
     * 型号
     */
    @Column(name = "model", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '型号'")
    private String model;

    /**
     * imei号
     */
    @Column(name = "imeiCode", columnDefinition = "VARCHAR(255) COMMENT 'imei号'")
    private String imeiCode;

    /**
     * 入库编码
     */
    @Column(name = "instockCode", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '入库编码'")
    private String instockCode;

    /**
     * 数量
     */
    @Column(name = "quantity", nullable = false, columnDefinition = "INT(11) COMMENT '数量'")
    private Integer quantity;

    /**
     * 单位
     */
    @Column(name = "unit", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '单位'")
    private String unit;

    /**
     * 调入地区
     */
    @Column(name = "transferredArea", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '调入地区'")
    private String transferredArea;

    /**
     * 申请日期,默认为当前日期
     */
    @Column(name = "applyDate", nullable = false, columnDefinition = "DATE COMMENT '申请日期'")
    private LocalDate applyDate;

    /**
     * 用途
     */
    @Column(name = "purpose", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '用途'")
    private String purpose;

    /**
     * 经手人
     */
    @Column(name = "handler", columnDefinition = "VARCHAR(255) COMMENT '经手人'")
    private String handler;

    /**
     * 备注
     */
    @Column(name = "comment", columnDefinition = "VARCHAR(255) COMMENT '备注'")
    private String comment;

    /**
     * 原存储地区项目经理
     */
    @Column(name = "originalPM", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '原存储地区项目经理'")
    private String originalPM;

    /**
     * 项目经理审核状态
     */
    @Column(name = "pmAuditState", nullable = false, columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '项目经理审核状态'")
    private AuditState pmAuditState;

    /**
     * 福利模块负责人
     */
    @Column(name = "welfareModule", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '福利模块负责人'")
    private String welfareModule;

    /**
     * 福利模块负责人审核状态
     */
    @Column(name = "welfareState", nullable = false, columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '福利模块负责人审核状态'")
    private AuditState welfareState;

    /**
     * 原存储地区
     */
    @Column(name = "archSaveArea", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '原存储地区'")
    private String archSaveArea;

    /**
     * 调动途径
     */
    @Column(name = "transferWay", nullable = false, columnDefinition = "TINYINT(2) COMMENT '调动途径'")
    private TransferWay transferWay;

    /**
     * 快递费用
     */
    @Column(name = "expressCharge", columnDefinition = "DECIMAL(10,2) DEFAULT 0 COMMENT '快递费用'")
    private Double expressCharge;

    /**
     * 物资来源
     */
    @Column(name = "materialSource", nullable = false, columnDefinition = "TINYINT(2) COMMENT '物资来源'")
    private MaterialSource materialSource;

    /**
     * 物资状态
     */
    @Column(name = "materialState", nullable = false, columnDefinition = "TINYINT(2) COMMENT '物资状态'")
    private MaterialState materialState;

    /**
     * 调动时间
     */
    @Column(name = "transferTime", columnDefinition = "DATETIME COMMENT '调动时间'")
    private LocalDateTime transferTime;

    /**
     * 物资负责人
     */
    @Column(name = "materialPrincipal", columnDefinition = "VARCHAR(255) COMMENT '物资负责人'")
    private String materialPrincipal;

    /**
     * 领用人
     */
    @Column(name = "recipient", columnDefinition = "VARCHAR(255) COMMENT '领用人'")
    private String recipient;

    /**
     * 福利模块负责人确认调配成功
     */
    @Column(name = "confirmDeploy", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '福利模块负责人确认调配成功'")
    private Boolean confirmDeploy;

    /**
     * 调配完成时间
     */
    @Column(name = "finishDeployTime", columnDefinition = "DATETIME COMMENT '调配完成时间'")
    private LocalDateTime finishDeployTime;


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

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(LocalDate applyDate) {
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

    public LocalDateTime getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(LocalDateTime transferTime) {
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

    public LocalDateTime getFinishDeployTime() {
        return finishDeployTime;
    }

    public void setFinishDeployTime(LocalDateTime finishDeployTime) {
        this.finishDeployTime = finishDeployTime;
    }
}