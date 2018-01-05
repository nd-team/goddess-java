package com.bjike.goddess.moneyside.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 客户信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 09:58 ]
 * @Description: [ 客户信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "moneyside_customerinfo")
public class CustomerInfo extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 年龄
     */
    @Column( columnDefinition = "VARCHAR(255)   COMMENT '年龄'")
    private Integer age;

    /**
     * 性别
     */
    @Column(name = "sex", columnDefinition = "VARCHAR(255)   COMMENT '性别'")
    private String sex;

    /**
     * 籍贯
     */
    @Column(name = "nativePlace", columnDefinition = "VARCHAR(255)   COMMENT '籍贯'")
    private String nativePlace;

    /**
     * 身份证号
     */
    @Column(name = "idNumber", columnDefinition = "VARCHAR(255)   COMMENT '身份证号'")
    private String idNumber;

    /**
     * 联系方式
     */
    @Column(name = "phone",  columnDefinition = "VARCHAR(255)   COMMENT '联系方式'")
    private String phone;

    /**
     * 邮箱
     */
    @Column(name = "email", columnDefinition = "VARCHAR(255)   COMMENT '邮箱'")
    private String email;

    /**
     * 地址
     */
    @Column(name = "address",  columnDefinition = "VARCHAR(255)   COMMENT '地址'")
    private String address;

    /**
     * 其他
     */
    @Column(name = "other", columnDefinition = "VARCHAR(255)   COMMENT '其他'")
    private String other;

    /**
     * 银行开户名
     */
    @Column(name = "bankAccountName", columnDefinition = "VARCHAR(255)   COMMENT '银行开户名'")
    private String bankAccountName;

    /**
     * 银行卡号
     */
    @Column(name = "bankCardNumber", columnDefinition = "VARCHAR(255)   COMMENT '银行卡号'")
    private String bankCardNumber;

    /**
     * 银行开户行
     */
    @Column(name = "bankOfDeposit",  columnDefinition = "VARCHAR(255)   COMMENT '银行开户行'")
    private String bankOfDeposit;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    public String getBankOfDeposit() {
        return bankOfDeposit;
    }

    public void setBankOfDeposit(String bankOfDeposit) {
        this.bankOfDeposit = bankOfDeposit;
    }
}