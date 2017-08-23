package com.bjike.goddess.supplier.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
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
     * 供应商基本信息
     */
    @NotNull(message = "供应商信息传输错误", groups = {ADD.class, EDIT.class})
    private String informationId;

    /**
     * 业务联系人
     */
    @NotNull(message = "业务联系人不能为空", groups = {ADD.class, EDIT.class})
    private String contacts;

    /**
     * 职务
     */
    private String duties;

    /**
     * 联系电话
     */
    @NotNull(message = "联系电话不能为空", groups = {ADD.class, EDIT.class})
    private String telephone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 传真
     */
    private String fax;

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