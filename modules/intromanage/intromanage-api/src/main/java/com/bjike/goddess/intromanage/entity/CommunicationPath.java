package com.bjike.goddess.intromanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 通讯途径
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 06:02 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "intromanage_communicationpath")
public class CommunicationPath extends BaseEntity {

    /**
     * 公司记录id
     */
    @Column(name = "firmId", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '公司记录id'")
    private String firmId;

    /**
     * 总公司地址
     */
    @Column(name = "headOfficeAddress", columnDefinition = "VARCHAR(255) COMMENT '总公司地址'")
    private String headOfficeAddress;

    /**
     * 总公司联系方式
     */
    @Column(name = "headOfficeContact", columnDefinition = "VARCHAR(255) COMMENT '总公司联系方式'")
    private String headOfficeContact;

    /**
     * 分公司地址
     */
    @Column(name = "branchAddress", columnDefinition = "VARCHAR(255) COMMENT '分公司地址'")
    private String branchAddress;

    /**
     * 分公司联系方式
     */
    @Column(name = "branchPhone", columnDefinition = "VARCHAR(255) COMMENT '分公司联系方式'")
    private String branchPhone;


    public String getFirmId() {
        return firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public String getHeadOfficeAddress() {
        return headOfficeAddress;
    }

    public void setHeadOfficeAddress(String headOfficeAddress) {
        this.headOfficeAddress = headOfficeAddress;
    }

    public String getHeadOfficeContact() {
        return headOfficeContact;
    }

    public void setHeadOfficeContact(String headOfficeContact) {
        this.headOfficeContact = headOfficeContact;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getBranchPhone() {
        return branchPhone;
    }

    public void setBranchPhone(String branchPhone) {
        this.branchPhone = branchPhone;
    }
}