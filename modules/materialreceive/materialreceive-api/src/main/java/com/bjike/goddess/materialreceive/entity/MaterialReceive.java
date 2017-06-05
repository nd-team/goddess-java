package com.bjike.goddess.materialreceive.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.materialinstock.type.MaterialState;
import com.bjike.goddess.materialreceive.type.AuditState;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 物资领用
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-24 05:41 ]
 * @Description: [ 物资领用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "materialreceive_materialreceive")
public class MaterialReceive extends BaseEntity {

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
     * 物品名称
     */
    @Column(name = "materialName", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '物品名称'")
    private String materialName;

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
     * 领用人
     */
    @Column(name = "recipient", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '领用人'")
    private String recipient;

    /**
     * 领用日期
     */
    @Column(name = "receiveDate", nullable = false, columnDefinition = "DATE COMMENT '领用日期'")
    private LocalDate receiveDate;

    /**
     * 用途
     */
    @Column(name = "purpose", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '用途'")
    private String purpose;

    /**
     * 审核人
     */
    @Column(name = "auditor", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '审核人'")
    private String auditor;

    /**
     * 审核状态
     */
    @Column(name = "auditState", nullable = false, columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '审核状态'")
    private AuditState auditState;

    /**
     * 审核意见
     */
    @Column(name = "auditOpinion", columnDefinition = "VARCHAR(255) COMMENT '审核意见'")
    private String auditOpinion;

    /**
     * 备注
     */
    @Column(name = "comment", columnDefinition = "VARCHAR(255) COMMENT '备注'")
    private String comment;

    /**
     * 物资编号
     */
    @Column(name = "materialNo", columnDefinition = "VARCHAR(255) COMMENT '物资编号'")
    private String materialNo;

    /**
     * 型号
     */
    @Column(name = "model", columnDefinition = "VARCHAR(255) COMMENT '型号'")
    private String model;

    /**
     * 原存储地区
     */
    @Column(name = "oldStorageArea", columnDefinition = "VARCHAR(255) COMMENT '原存储地区'")
    private String oldStorageArea;

    /**
     * 物资原负责人
     */
    @Column(name = "oldPrincipal", columnDefinition = "VARCHAR(255) COMMENT '物资原负责人'")
    private String oldPrincipal;

    /**
     * 经手人
     */
    @Column(name = "handler", columnDefinition = "VARCHAR(255) COMMENT '经手人'")
    private String handler;

    /**
     * 是否归还
     */
    @Column(name = "ifReturn", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否归还'", insertable = false)
    private Boolean ifReturn;

    /**
     * 审核情况
     */
    @Column(name = "auditCase", columnDefinition = "TINYINT(2) COMMENT '审核情况'")
    private AuditState auditCase;

    /**
     * 归还时间
     */
    @Column(name = "returnTime", columnDefinition = "DATETIME COMMENT '归还时间'")
    private LocalDateTime returnTime;

    /**
     * 物资状态
     */
    @Column(name = "materialState", columnDefinition = "TINYINT(2) COMMENT '物资状态'")
    private MaterialState materialState;


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

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
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

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOldStorageArea() {
        return oldStorageArea;
    }

    public void setOldStorageArea(String oldStorageArea) {
        this.oldStorageArea = oldStorageArea;
    }

    public String getOldPrincipal() {
        return oldPrincipal;
    }

    public void setOldPrincipal(String oldPrincipal) {
        this.oldPrincipal = oldPrincipal;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public Boolean getIfReturn() {
        return ifReturn;
    }

    public void setIfReturn(Boolean ifReturn) {
        this.ifReturn = ifReturn;
    }

    public AuditState getAuditCase() {
        return auditCase;
    }

    public void setAuditCase(AuditState auditCase) {
        this.auditCase = auditCase;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }

    public MaterialState getMaterialState() {
        return materialState;
    }

    public void setMaterialState(MaterialState materialState) {
        this.materialState = materialState;
    }
}