package com.bjike.goddess.supplier.vo;

/**
 * 联系情况表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T11:03:21.701 ]
 * @Description: [ 联系情况表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContactSituationVO {

    /**
     * id
     */
    private String id;
    /**
     * 供应商基本信息
     */
    private String informationId;

    /**
     * 业务联系人
     */
    private String contacts;

    /**
     * 职务
     */
    private String duties;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 传真
     */
    private String fax;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInformationId() {
        return informationId;
    }

    public void setInformationId(String informationId) {
        this.informationId = informationId;
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