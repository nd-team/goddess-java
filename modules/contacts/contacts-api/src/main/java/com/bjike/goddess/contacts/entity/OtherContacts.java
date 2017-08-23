package com.bjike.goddess.contacts.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 其他通讯录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:44 ]
 * @Description: [ 其他通讯录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "contacts_other_contacts")
public class OtherContacts extends BaseEntity {

    /**
     * 服务类别
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '服务类别'")
    private String type;

    /**
     * 公司名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公司名称'")
    private String name;

    /**
     * 联系电话
     */
    @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '联系电话'")
    private String phone;

    /**
     * 公司地址
     */
    @Column(name = "address", columnDefinition = "VARCHAR(255)   COMMENT '公司地址'")
    private String address;

    /**
     * 使用人
     */
    @Column(name = "user", columnDefinition = "VARCHAR(255)   COMMENT '使用人'")
    private String user;

    /**
     * 使用日期
     */
    @Column(name = "useDate", columnDefinition = "DATE   COMMENT '使用日期'")
    private LocalDate useDate;

    /**
     * 评价
     */
    @Column(name = "evaluate", columnDefinition = "VARCHAR(255)   COMMENT '评价'")
    private String evaluate;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDate getUseDate() {
        return useDate;
    }

    public void setUseDate(LocalDate useDate) {
        this.useDate = useDate;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}