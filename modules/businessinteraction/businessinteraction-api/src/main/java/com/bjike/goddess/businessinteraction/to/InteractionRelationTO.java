package com.bjike.goddess.businessinteraction.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 商业能力互动联系
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:06 ]
 * @Description: [ 商业能力互动联系 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InteractionRelationTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空")
    private String area;

    /**
     * 公司名称
     */
    @NotBlank(message = "公司名称不能为空")
    private String companyName;

    /**
     * 公司电话
     */
    private String companyTel;

    /**
     * 公司邮箱
     */
    private String companyEmail;

    /**
     * 公司主页
     */
    private String companyMajorPage;

    /**
     * 公司微信号
     */
    private String companyWebchat;

    /**
     * 公司QQ号
     */
    private String companyQQ;

    /**
     * 公司论坛
     */
    private String companyTalk;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    private String contactTel;

    /**
     * 移动电话
     */
    private String phone;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyMajorPage() {
        return companyMajorPage;
    }

    public void setCompanyMajorPage(String companyMajorPage) {
        this.companyMajorPage = companyMajorPage;
    }

    public String getCompanyWebchat() {
        return companyWebchat;
    }

    public void setCompanyWebchat(String companyWebchat) {
        this.companyWebchat = companyWebchat;
    }

    public String getCompanyQQ() {
        return companyQQ;
    }

    public void setCompanyQQ(String companyQQ) {
        this.companyQQ = companyQQ;
    }

    public String getCompanyTalk() {
        return companyTalk;
    }

    public void setCompanyTalk(String companyTalk) {
        this.companyTalk = companyTalk;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}