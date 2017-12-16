package com.bjike.goddess.supplier.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.supplier.enums.InfoSource;
import com.bjike.goddess.supplier.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 供应商信息管理
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 10:33 ]
 * @Description: [ 供应商信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "supplier_supplierinfo")
public class SupplierInfo extends BaseEntity {

    /**
     * 供应商信息收集时间
     */
    @Column(name = "infoCollectDate", nullable = false, columnDefinition = "DATE   COMMENT '供应商信息收集时间'")
    private LocalDate infoCollectDate;

    /**
     * 供应商信息来源
     */
    @Column(name = "infoSource", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '供应商信息来源'")
    private InfoSource infoSource;

    /**
     * 市场编号
     */
    @Column(name = "marketNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '市场编号'")
    private String marketNum;

    /**
     * 业务类型
     */
    @Column(name = "bussType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务类型'")
    private String bussType;

    /**
     * 供应商编号
     */
    @Column(name = "supplierNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '供应商编号'")
    private String supplierNum;

    /**
     * 供应商地区
     */
    @Column(name = "supplierArea", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '供应商地区'")
    private String supplierArea;

    /**
     * 供应商名称
     */
    @Column(name = "supplierName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '供应商名称'")
    private String supplierName;

    /**
     * 供应商类型
     */
    @Column(name = "supplierType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '供应商类型'")
    private String supplierType;

    /**
     * 经营范围
     */
    @Column(name = "bussScope",  columnDefinition = "VARCHAR(255)   COMMENT '经营范围'")
    private String bussScope;

    /**
     * 主要产品和服务
     */
    @Column(name = "majorProducts",  columnDefinition = "VARCHAR(255)   COMMENT '主要产品和服务'")
    private String majorProducts;

    /**
     * 业务联络人
     */
    @Column(name = "bussLiaison", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务联络人'")
    private String bussLiaison;

    /**
     * 职务
     */
    @Column(name = "duty",  columnDefinition = "VARCHAR(255)   COMMENT '职务'")
    private String duty;

    /**
     * 联系电话
     */
    @Column(name = "contactNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '联系电话'")
    private String contactNum;

    /**
     * 邮箱
     */
    @Column(name = "mailBox",  columnDefinition = "VARCHAR(255)   COMMENT '邮箱'")
    private String mailBox;

    /**
     * 传真
     */
    @Column(name = "fax",  columnDefinition = "VARCHAR(255)   COMMENT '传真'")
    private String fax;

    /**
     * 信息完成度
     */
    @Column(name = "infoCompletion",  columnDefinition = "VARCHAR(255)   COMMENT '信息完成度'")
    private String infoCompletion;

    /**
     * 是否上传营业执照附件
     */
    @Column(name = "is_uploadBusinLicense",  columnDefinition = "TINYINT(1)    COMMENT '是否上传营业执照附件'")
    private Boolean uploadBusinLicense;

    /**
     * 是否上传资质附件
     */
    @Column(name = "is_uploadQualifition",  columnDefinition = "TINYINT(1)    COMMENT '是否上传资质附件'")
    private Boolean uploadQualifition;

    /**
     * 资质等级
     */
    @Column(name = "levelQualifition",  columnDefinition = "VARCHAR(255)   COMMENT '资质等级'")
    private String levelQualifition;

    /**
     * 是否确定合作
     */
    @Column(name = "is_deterCooper",  columnDefinition = "TINYINT(1)   COMMENT '是否确定合作'")
    private Boolean deterCooper;

    /**
     * 最新外包合同签订时间
     */
    @Column(name = "newSigningDate",columnDefinition = "DATE   COMMENT '最新外包合同签订时间'")
    private LocalDate newSigningDate;

    /**
     * 最新外包合同截止时间
     */
    @Column(name = "newCutoffDate",  columnDefinition = "DATE   COMMENT '最新外包合同截止时间'")
    private LocalDate newCutoffDate;

    /**
     * 是否付款完成
     */
    @Column(name = "is_payComplete",  columnDefinition = "TINYINT(1)   COMMENT '是否付款完成'")
    private Boolean payComplete;

    /**
     * 结算完成时间
     */
    @Column(name = "settlementCompleteDate",  columnDefinition = "DATE   COMMENT '结算完成时间'")
    private LocalDate settlementCompleteDate;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '状态'")
    private Status status;

    /**
     * 已合作次数
     */
    @Column(name = "cooperationNum",  columnDefinition = "INT(11)   COMMENT '已合作次数'")
    private Integer cooperationNum;

    /**
     * 供应商信息是否完善
     */
    @Column(name = "is_infoPerfecting",nullable = false,columnDefinition = "TINYINT(1)   COMMENT '供应商信息是否完善'")
    private Boolean infoPerfecting;

    public LocalDate getInfoCollectDate() {
        return infoCollectDate;
    }

    public void setInfoCollectDate(LocalDate infoCollectDate) {
        this.infoCollectDate = infoCollectDate;
    }

    public InfoSource getInfoSource() {
        return infoSource;
    }

    public void setInfoSource(InfoSource infoSource) {
        this.infoSource = infoSource;
    }

    public String getMarketNum() {
        return marketNum;
    }

    public void setMarketNum(String marketNum) {
        this.marketNum = marketNum;
    }

    public String getBussType() {
        return bussType;
    }

    public void setBussType(String bussType) {
        this.bussType = bussType;
    }

    public String getSupplierNum() {
        return supplierNum;
    }

    public void setSupplierNum(String supplierNum) {
        this.supplierNum = supplierNum;
    }

    public String getSupplierArea() {
        return supplierArea;
    }

    public void setSupplierArea(String supplierArea) {
        this.supplierArea = supplierArea;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getBussScope() {
        return bussScope;
    }

    public void setBussScope(String bussScope) {
        this.bussScope = bussScope;
    }

    public String getMajorProducts() {
        return majorProducts;
    }

    public void setMajorProducts(String majorProducts) {
        this.majorProducts = majorProducts;
    }

    public String getBussLiaison() {
        return bussLiaison;
    }

    public void setBussLiaison(String bussLiaison) {
        this.bussLiaison = bussLiaison;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getMailBox() {
        return mailBox;
    }

    public void setMailBox(String mailBox) {
        this.mailBox = mailBox;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getInfoCompletion() {
        return infoCompletion;
    }

    public void setInfoCompletion(String infoCompletion) {
        this.infoCompletion = infoCompletion;
    }

    public Boolean getUploadBusinLicense() {
        return uploadBusinLicense;
    }

    public void setUploadBusinLicense(Boolean uploadBusinLicense) {
        this.uploadBusinLicense = uploadBusinLicense;
    }

    public Boolean getUploadQualifition() {
        return uploadQualifition;
    }

    public void setUploadQualifition(Boolean uploadQualifition) {
        this.uploadQualifition = uploadQualifition;
    }

    public String getLevelQualifition() {
        return levelQualifition;
    }

    public void setLevelQualifition(String levelQualifition) {
        this.levelQualifition = levelQualifition;
    }

    public Boolean getDeterCooper() {
        return deterCooper;
    }

    public void setDeterCooper(Boolean deterCooper) {
        this.deterCooper = deterCooper;
    }

    public LocalDate getNewSigningDate() {
        return newSigningDate;
    }

    public void setNewSigningDate(LocalDate newSigningDate) {
        this.newSigningDate = newSigningDate;
    }

    public LocalDate getNewCutoffDate() {
        return newCutoffDate;
    }

    public void setNewCutoffDate(LocalDate newCutoffDate) {
        this.newCutoffDate = newCutoffDate;
    }

    public Boolean getPayComplete() {
        return payComplete;
    }

    public void setPayComplete(Boolean payComplete) {
        this.payComplete = payComplete;
    }

    public LocalDate getSettlementCompleteDate() {
        return settlementCompleteDate;
    }

    public void setSettlementCompleteDate(LocalDate settlementCompleteDate) {
        this.settlementCompleteDate = settlementCompleteDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getCooperationNum() {
        return cooperationNum;
    }

    public void setCooperationNum(Integer cooperationNum) {
        this.cooperationNum = cooperationNum;
    }

    public Boolean getInfoPerfecting() {
        return infoPerfecting;
    }

    public void setInfoPerfecting(Boolean infoPerfecting) {
        this.infoPerfecting = infoPerfecting;
    }
}