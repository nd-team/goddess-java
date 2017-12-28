package com.bjike.goddess.supplier.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 企业资质
 *
 * @Author: [ lijuntao ]　
 * @Date: [ 2017-12-15 03:58 ]　
 * @Description: [ 企业资质 ]　
 * @Version: [ v1.0.0 ]　
 * @Copy: [ com.bjike ]　
 */
@Entity
@Table(name = "supplier_enterprisequalifica")
public class EnterpriseQualifica extends BaseEntity {

    /**
     * 供应商信息登记id
     */
    @Column(name = "supplierInfoRegiId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '供应商信息登记id'")
    private String supplierInfoRegiId;
    /**
     * 证书名称
     */
    @Column(name = "certificateName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '证书名称'")
    private String certificateName;

    /**
     * 证书编号
     */
    @Column(name = "certificateNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '证书编号'")
    private String certificateNum;

    /**
     * 有效期限
     */
    @Column(name = "validTerm", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '有效期限'")
    private String validTerm;

    /**
     * 颁发单位
     */
    @Column(name = "issuedUnit", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '颁发单位'")
    private String issuedUnit;

    /**
     * 资质等级
     */
    @Column(name = "qualifiLevel", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '资质等级'")
    private String qualifiLevel;

    /**
     * 备注
     */
    @Column(name = "remark", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getSupplierInfoRegiId() {
        return supplierInfoRegiId;
    }

    public void setSupplierInfoRegiId(String supplierInfoRegiId) {
        this.supplierInfoRegiId = supplierInfoRegiId;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    public String getValidTerm() {
        return validTerm;
    }

    public void setValidTerm(String validTerm) {
        this.validTerm = validTerm;
    }

    public String getIssuedUnit() {
        return issuedUnit;
    }

    public void setIssuedUnit(String issuedUnit) {
        this.issuedUnit = issuedUnit;
    }

    public String getQualifiLevel() {
        return qualifiLevel;
    }

    public void setQualifiLevel(String qualifiLevel) {
        this.qualifiLevel = qualifiLevel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}