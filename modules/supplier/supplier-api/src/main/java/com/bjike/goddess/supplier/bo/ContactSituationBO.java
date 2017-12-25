package com.bjike.goddess.supplier.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 联络情况业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 04:03 ]
 * @Description: [ 联络情况业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContactSituationBO extends BaseBO {

    /**
     * 供应商信息登记id
     */
    private String supplierInfoRegiId;
    /**
     * 业务联络人
     */
    private String bussLiaison;

    /**
     * 职务
     */
    private String duty;

    /**
     * 联系电话
     */
    private String contactNum;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 传真
     */
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