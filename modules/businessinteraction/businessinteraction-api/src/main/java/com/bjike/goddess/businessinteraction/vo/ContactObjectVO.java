package com.bjike.goddess.businessinteraction.vo;

/**
 * 获取合作对象联系方式
 * @Author: [tanghaixiang]
 * @Date: [2017-03-29 10:13]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ContactObjectVO {

    /**
     * 公司名
     */
    private String companyName;
    /**
     * 联系人
     */
    private String contactName;
    /**
     * 联系电话
     */
    private String contactTel;
    /**
     * 移动电话
     */
    private String mobilePhone;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
