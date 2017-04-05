package com.bjike.goddess.contacts.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.customer.entity.CustomerLevel;
import com.bjike.goddess.customer.enums.CustomerSex;
import com.bjike.goddess.customer.enums.CustomerStatus;
import com.bjike.goddess.customer.enums.CustomerType;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;


/**
 * 商务通讯录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 06:04 ]
 * @Description: [ 商务通讯录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "contacts_commerce_contacts")
public class CommerceContacts extends BaseEntity {

    /**
     * 客户信息编号
     */
    @Column(name = "customerNum", nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '客户信息编号'")
    private String customerNum;

    /**
     * 客户姓名
     */
    @Column(name = "customerName", nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '客户姓名'")
    private String customerName;

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 性别
     */
    @Column(name = "customerSex", nullable = false, columnDefinition = "INT(2)   COMMENT '性别'")
    private CustomerSex customerSex;

    /**
     * 客户类别
     */
    @Column(name = "customerType", columnDefinition = "INT(2)   COMMENT '客户类别'")
    private CustomerType customerType;

    /**
     * 客户状态
     */
    @Column(name = "customerStatus", columnDefinition = "INT(2)   COMMENT '客户状态'")
    private CustomerStatus customerStatus;

    /**
     * 关系程度
     */
    @Column(name = "relation", columnDefinition = "DECIMAL(10,2)   COMMENT '关系程度'")
    private Double relation;

    /**
     * 客户级别
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "customerLevel_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '客户级别'")
    private CustomerLevel customerLevel;

    /**
     * 客户来源
     */
    @Column(name = "origin", columnDefinition = "VARCHAR(255)   COMMENT '客户来源'")
    private String origin;

    /**
     * 介绍人
     */
    @Column(name = "introducer", columnDefinition = "VARCHAR(255)   COMMENT '介绍人'")
    private String introducer;

    /**
     * 邮箱
     */
    @Email
    @Column(name = "cusEmail", columnDefinition = "VARCHAR(255)   COMMENT '邮箱'")
    private String cusEmail;

    /**
     * 手机号
     */
    @Column(name = "tel", columnDefinition = "VARCHAR(255)   COMMENT '手机号'")
    private String tel;

    /**
     * 座机
     */
    @Column(name = "phone", columnDefinition = "VARCHAR(255)   COMMENT '座机'")
    private String phone;

    /**
     * 微信
     */
    @Column(name = "weChart", columnDefinition = "VARCHAR(255)   COMMENT '微信'")
    private String weChart;

    /**
     * QQ号
     */
    @Column(name = "qq", columnDefinition = "VARCHAR(255)   COMMENT 'QQ号'")
    private String qq;

    /**
     * 岗位
     */
    @Column(name = "workPosition", columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String workPosition;

    /**
     * 职级
     */
    @Column(name = "workLevel", columnDefinition = "VARCHAR(255)   COMMENT '职级'")
    private String workLevel;

    /**
     * 职权
     */
    @Column(name = "workRight", columnDefinition = "VARCHAR(255)   COMMENT '职权'")
    private String workRight;

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public CustomerSex getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(CustomerSex customerSex) {
        this.customerSex = customerSex;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public CustomerStatus getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(CustomerStatus customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Double getRelation() {
        return relation;
    }

    public void setRelation(Double relation) {
        this.relation = relation;
    }

    public CustomerLevel getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(CustomerLevel customerLevel) {
        this.customerLevel = customerLevel;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeChart() {
        return weChart;
    }

    public void setWeChart(String weChart) {
        this.weChart = weChart;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    public String getWorkLevel() {
        return workLevel;
    }

    public void setWorkLevel(String workLevel) {
        this.workLevel = workLevel;
    }

    public String getWorkRight() {
        return workRight;
    }

    public void setWorkRight(String workRight) {
        this.workRight = workRight;
    }
}