package com.bjike.goddess.supplier.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 联系情况
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T11:03:21.694 ]
 * @Description: [ 联系情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "supplier_contact_situation")
public class ContactSituation extends BaseEntity {

    /**
     * 供应商基本信息
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "information_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '供应商基本信息'")
    private SupplierInformation information;

    /**
     * 业务联系人
     */
    @Column(name = "contacts", nullable = false, columnDefinition = "VARCHAR(50)   COMMENT '业务联系人'")
    private String contacts;

    /**
     * 职务
     */
    @Column(name = "duties", columnDefinition = "VARCHAR(50)   COMMENT '职务'")
    private String duties;

    /**
     * 联系电话
     */
    @Column(name = "telephone", nullable = false, columnDefinition = "VARCHAR(20)   COMMENT '联系电话'")
    private String telephone;

    /**
     * 邮箱
     */
    @Column(name = "email", columnDefinition = "VARCHAR(100)   COMMENT '邮箱'")
    private String email;

    /**
     * 传真
     */
    @Column(name = "fax", columnDefinition = "VARCHAR(100)   COMMENT '传真'")
    private String fax;


    public SupplierInformation getInformation() {
        return information;
    }

    public void setInformation(SupplierInformation information) {
        this.information = information;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}