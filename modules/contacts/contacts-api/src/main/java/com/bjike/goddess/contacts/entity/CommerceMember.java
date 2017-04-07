package com.bjike.goddess.contacts.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 商务会员卡
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:38 ]
 * @Description: [ 商务会员卡 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "contacts_commer_cemember")
public class CommerceMember extends BaseEntity {

    /**
     * 开卡名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '开卡名称'")
    private String name;

    /**
     * 卡号
     */
    @Column(name = "number", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '卡号'", unique = true)
    private String number;

    /**
     * 开卡人联系方式
     */
    @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '开卡人联系方式'")
    private String phone;

    /**
     * 用途
     */
    @Column(name = "purpose", columnDefinition = "VARCHAR(255)   COMMENT '用途'")
    private String purpose;

    /**
     * 类别
     */
    @Column(name = "type", columnDefinition = "VARCHAR(255)   COMMENT '类别'")
    private String type;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}