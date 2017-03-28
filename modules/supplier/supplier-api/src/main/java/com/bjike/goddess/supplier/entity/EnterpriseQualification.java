package com.bjike.goddess.supplier.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 企业资质
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:47:02.254 ]
 * @Description: [ 企业资质 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "supplier_enterprise_qualification")
public class EnterpriseQualification extends BaseEntity {

    /**
     * 供应商基本信息
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "information_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '供应商基本信息'")
    private SupplierInformation information;

    /**
     * 序号
     */
    @Column(name = "serialNumber", nullable = false, columnDefinition = "VARCHAR(50)   COMMENT '序号'")
    private String serialNumber;

    /**
     * 证书名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(50)   COMMENT '证书名称'")
    private String name;

    /**
     * 证书编号
     */
    @Column(name = "certificateNumber", nullable = false, columnDefinition = "VARCHAR(100)   COMMENT '证书编号'")
    private String certificateNumber;

    /**
     * 有效期限
     */
    @Column(name = "validityPeriod", nullable = false, columnDefinition = "VARCHAR(50)   COMMENT '有效期限'")
    private String validityPeriod;

    /**
     * 颁发单位
     */
    @Column(name = "promulgate", nullable = false, columnDefinition = "VARCHAR(50)   COMMENT '颁发单位'")
    private String promulgate;

    /**
     * 资质等级
     */
    @Column(name = "aptitude", nullable = false, columnDefinition = "VARCHAR(20)   COMMENT '资质等级'")
    private String aptitude;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public SupplierInformation getInformation() {
        return information;
    }

    public void setInformation(SupplierInformation information) {
        this.information = information;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(String validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public String getPromulgate() {
        return promulgate;
    }

    public void setPromulgate(String promulgate) {
        this.promulgate = promulgate;
    }

    public String getAptitude() {
        return aptitude;
    }

    public void setAptitude(String aptitude) {
        this.aptitude = aptitude;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}