package com.bjike.goddess.businsurance.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 意外险记录
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 05:43 ]
 * @Description: [ 意外险记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businsurance_insurerecord")
public class InsureRecord extends BaseEntity {

    /**
     * 商业险类型
     */
    @Column(name = "busType",  columnDefinition = "VARCHAR(255)   COMMENT '商业险类型'")
    private String busType;

    /**
     * 商业险名称
     */
    @Column(name = "typeName",  columnDefinition = "VARCHAR(255)   COMMENT '商业险名称'")
    private String typeName;

    /**
     * 保单生效日
     */
    @Column(name = "startDate",  columnDefinition = "DATE   COMMENT '保单生效日'")
    private LocalDate startDate;

    /**
     * 保单期满日
     */
    @Column(name = "endDate",  columnDefinition = "DATE   COMMENT '保单期满日'")
    private LocalDate endDate;

    /**
     * 投保人名字
     */
    @Column(name = "insureName",  columnDefinition = "VARCHAR(255)   COMMENT '投保人名字'")
    private String insureName;

    /**
     * 被保险人名字
     */
    @Column(name = "insureByName",  columnDefinition = "VARCHAR(255)   COMMENT '被保险人名字'")
    private String insureByName;

    /**
     * 险种名称
     */
    @Column(name = "insureTypeName",  columnDefinition = "VARCHAR(255)   COMMENT '险种名称'")
    private String insureTypeName;

    /**
     * 组合名称
     */
    @Column(name = "combiName",  columnDefinition = "VARCHAR(255)   COMMENT '组合名称'")
    private String combiName;

    /**
     * 销售机构名称
     */
    @Column(name = "saleGroupName",  columnDefinition = "VARCHAR(255)   COMMENT '销售机构名称'")
    private String saleGroupName;

    /**
     * 销售员名称
     */
    @Column(name = "salerName",  columnDefinition = "VARCHAR(255)   COMMENT '销售员名称'")
    private String salerName;

    /**
     * 机构电话
     */
    @Column(name = "organTel",  columnDefinition = "VARCHAR(255)   COMMENT '机构电话'")
    private String organTel;

    /**
     * 合同存储编号
     */
    @Column(name = "storageNumber",  columnDefinition = "VARCHAR(255)   COMMENT '合同存储编号'")
    private String storageNumber;

    /**
     * 合同存储路径
     */
    @Column(name = "storagePath",  columnDefinition = "VARCHAR(255)   COMMENT '合同存储路径'")
    private String storagePath;

    /**
     * 合同附件
     */
    @Column(name = "storageFile",  columnDefinition = "VARCHAR(255)   COMMENT '合同附件'")
    private String storageFile;

    /**
     * 是否已续保
     */
    @Column(name = "renewal",  columnDefinition = "VARCHAR(255)   COMMENT '是否已续保'")
    private String renewal;

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getInsureName() {
        return insureName;
    }

    public void setInsureName(String insureName) {
        this.insureName = insureName;
    }

    public String getInsureByName() {
        return insureByName;
    }

    public void setInsureByName(String insureByName) {
        this.insureByName = insureByName;
    }

    public String getInsureTypeName() {
        return insureTypeName;
    }

    public void setInsureTypeName(String insureTypeName) {
        this.insureTypeName = insureTypeName;
    }

    public String getCombiName() {
        return combiName;
    }

    public void setCombiName(String combiName) {
        this.combiName = combiName;
    }

    public String getSaleGroupName() {
        return saleGroupName;
    }

    public void setSaleGroupName(String saleGroupName) {
        this.saleGroupName = saleGroupName;
    }

    public String getSalerName() {
        return salerName;
    }

    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }

    public String getOrganTel() {
        return organTel;
    }

    public void setOrganTel(String organTel) {
        this.organTel = organTel;
    }

    public String getStorageNumber() {
        return storageNumber;
    }

    public void setStorageNumber(String storageNumber) {
        this.storageNumber = storageNumber;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public String getStorageFile() {
        return storageFile;
    }

    public void setStorageFile(String storageFile) {
        this.storageFile = storageFile;
    }

    public String getRenewal() {
        return renewal;
    }

    public void setRenewal(String renewal) {
        this.renewal = renewal;
    }
}