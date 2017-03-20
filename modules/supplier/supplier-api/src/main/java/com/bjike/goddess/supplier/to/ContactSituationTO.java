package com.bjike.goddess.supplier.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 联系情况
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T11:03:21.700 ]
 * @Description: [ 联系情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContactSituationTO extends BaseTO {

    /**
     * 联系情况ID数组
     */
    private String[] contact_ids;

    /**
     * 业务联系人数组
     */
    private String[] contacts;

    /**
     * 职务数组
     */
    private String[] duties;

    /**
     * 联系电话数组
     */
    private String[] telephone;

    /**
     * 邮箱数组
     */
    private String[] email;

    /**
     * 传真数组
     */
    private String[] fax;

    public String[] getContact_ids() {
        return contact_ids;
    }

    public void setContact_ids(String[] contact_ids) {
        this.contact_ids = contact_ids;
    }

    public String[] getContacts() {
        return contacts;
    }

    public void setContacts(String[] contacts) {
        this.contacts = contacts;
    }

    public String[] getDuties() {
        return duties;
    }

    public void setDuties(String[] duties) {
        this.duties = duties;
    }

    public String[] getTelephone() {
        return telephone;
    }

    public void setTelephone(String[] telephone) {
        this.telephone = telephone;
    }

    public String[] getEmail() {
        return email;
    }

    public void setEmail(String[] email) {
        this.email = email;
    }

    public String[] getFax() {
        return fax;
    }

    public void setFax(String[] fax) {
        this.fax = fax;
    }
}