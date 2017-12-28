package com.bjike.goddess.supplier.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 联络情况
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 04:03 ]
 * @Description: [ 联络情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "supplier_contactsituation")
public class ContactSituation extends BaseEntity {

    /**
     * 供应商信息登记id
     */
    @Column(name = "supplierInfoRegiId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '供应商信息登记id'")
    private String supplierInfoRegiId;
    /**
     * 业务联络人
     */
    @Column(name = "bussLiaison", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务联络人'")
    private String bussLiaison;

    /**
     * 职务
     */
    @Column(name = "duty", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '职务'")
    private String duty;

    /**
     * 联系电话
     */
    @Column(name = "contactNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '联系电话'")
    private String contactNum;

    /**
     * 邮箱
     */
    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '邮箱'")
    private String email;

    /**
     * 传真
     */
    @Column(name = "facsimile", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '传真'")
    private String facsimile;

    public String getSupplierInfoRegiId() {
        return supplierInfoRegiId;
    }

    public void setSupplierInfoRegiId(String supplierInfoRegiId) {
        this.supplierInfoRegiId = supplierInfoRegiId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacsimile() {
        return facsimile;
    }

    public void setFacsimile(String facsimile) {
        this.facsimile = facsimile;
    }
}