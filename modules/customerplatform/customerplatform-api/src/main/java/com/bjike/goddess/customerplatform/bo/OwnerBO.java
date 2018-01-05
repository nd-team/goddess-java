package com.bjike.goddess.customerplatform.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 业主业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-30 11:27 ]
 * @Description: [ 业主业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OwnerBO extends BaseBO {

    /**
     * 业主姓名
     */
    private String ownerName;

    /**
     * 业主地址
     */
    private String owneraddress;

    /**
     * 业主联系方式（电话）
     */
    private String ownerPhone;

    /**
     * 联系人姓名
     */
    private String name;

    /**
     * 联系人性别
     */
    private String sex;

    /**
     * 联系人职位
     */
    private String position;

    /**
     * 联系人电话
     */
    private String phone;

    /**
     * 联系人微信
     */
    private String wechat;

    /**
     * 联系人邮箱
     */
    private String email;

    /**
     * 需求类型
     */
    private String demandType;

    /**
     * 需求说明
     */
    private String description;

    /**
     * 备注
     */
    private String remark;


    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwneraddress() {
        return owneraddress;
    }

    public void setOwneraddress(String owneraddress) {
        this.owneraddress = owneraddress;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDemandType() {
        return demandType;
    }

    public void setDemandType(String demandType) {
        this.demandType = demandType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}