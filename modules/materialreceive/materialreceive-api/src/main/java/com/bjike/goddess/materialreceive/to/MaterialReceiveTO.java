package com.bjike.goddess.materialreceive.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.materialreceive.type.AuditState;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 物资领用
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-24 05:41 ]
 * @Description: [ 物资领用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MaterialReceiveTO extends BaseTO {

    public interface AUDIT {
    }//审核

    public interface RECEIVEOVER {
    }//领用完成

    public interface MATERIALRETURN {
    }//物资归还

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
     * 物品名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "物品名称不能为空")
    private String materialName;

    /**
     * 单位
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "单位不能为空")
    private String unit;

    /**
     * 领用人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "领用人不能为空")
    private String recipient;

    /**
     * 领用日期
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "领用日期不能为空")
    private String receiveDate;

    /**
     * 用途
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "用途不能为空")
    private String purpose;

    /**
     * 审核人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "审核人不能为空")
    private String auditor;

    /**
     * 审核状态
     */
    @NotNull(groups = {MaterialReceiveTO.AUDIT.class}, message = "审核状态不能为空")
    private AuditState auditState;

    /**
     * 审核意见
     */
    @NotBlank(groups = {MaterialReceiveTO.AUDIT.class}, message = "审核意见不能为空")
    private String auditOpinion;

    /**
     * 备注
     */
    private String comment;

    /**
     * 物资编号
     */
    private String materialNo;

    /**
     * 型号
     */
    @NotBlank(groups = {MaterialReceiveTO.RECEIVEOVER.class}, message = "型号不能为空")
    private String model;

    /**
     * 原存储地区
     */
    @NotBlank(groups = {MaterialReceiveTO.RECEIVEOVER.class}, message = "原存储地区不能为空")
    private String oldStorageArea;

    /**
     * 物资原负责人
     */
    @NotBlank(groups = {MaterialReceiveTO.RECEIVEOVER.class}, message = "物资原负责人不能为空")
    private String oldPrincipal;

    /**
     * 经手人
     */
    @NotBlank(groups = {MaterialReceiveTO.RECEIVEOVER.class}, message = "经手人不能为空")
    private String handler;

    /**
     * 是否归还
     */
    @NotNull(groups = {MaterialReceiveTO.MATERIALRETURN.class}, message = "是否归还不能为空")
    private Boolean ifReturn;

    /**
     * 审核情况
     */
    @NotBlank(groups = {MaterialReceiveTO.MATERIALRETURN.class}, message = "审核情况不能为空")
    private String auditCase;

    /**
     * 归还时间
     */
    @NotBlank(groups = {MaterialReceiveTO.MATERIALRETURN.class}, message = "归还时间不能为空")
    private String returnTime;

    /**
     * 物资状态
     */
    @NotBlank(groups = {MaterialReceiveTO.MATERIALRETURN.class}, message = "物资状态不能为空")
    private String materialState;

    /**
     * 领用编号
     */
    @Size(groups = {ADD.class, EDIT.class, MaterialReceiveTO.MATERIALRETURN.class}, message = "领用数量必修是大于0的整数")
    private String[] materialNum;

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

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
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

    public String getAuditCase() {
        return auditCase;
    }

    public void setAuditCase(String auditCase) {
        this.auditCase = auditCase;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getMaterialState() {
        return materialState;
    }

    public void setMaterialState(String materialState) {
        this.materialState = materialState;
    }

    public String[] getMaterialNum() {
        return materialNum;
    }

    public void setMaterialNum(String[] materialNum) {
        this.materialNum = materialNum;
    }
}