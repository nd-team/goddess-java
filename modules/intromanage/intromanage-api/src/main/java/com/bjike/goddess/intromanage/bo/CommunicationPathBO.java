package com.bjike.goddess.intromanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 通讯途径业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 06:02 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CommunicationPathBO extends BaseBO {

    /**
     * 公司记录id
     */
    private String firmId;

    /**
     * 总公司地址
     */
    private String headOfficeAddress;

    /**
     * 总公司联系方式
     */
    private String headOfficeContact;

    /**
     * 分公司地址
     */
    private String branchAddress;

    /**
     * 分公司联系方式
     */
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