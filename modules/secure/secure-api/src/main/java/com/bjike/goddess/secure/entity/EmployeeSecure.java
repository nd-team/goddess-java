package com.bjike.goddess.secure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 员工社保基本信息
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 10:19 ]
 * @Description: [ 员工社保基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "secure_employeesecure")
public class EmployeeSecure extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 员工编号
     */
    @Column(name = "employeeNum", columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String employeeNum;

    /**
     * 地区
     */
    @Column(name = "city", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String city;

    /**
     * 项目组
     */
    @Column(name = "team", columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String team;

    /**
     * 参保单位
     */
    @Column(name = "company", columnDefinition = "VARCHAR(255)   COMMENT '参保单位'")
    private String company;

    /**
     * 身份证号码
     */
    @Column(name = "idCart", columnDefinition = "VARCHAR(255)   COMMENT '身份证号码'")
    private String idCart;

    /**
     * 身份证籍贯
     */
    @Column(name = "born", columnDefinition = "VARCHAR(255)   COMMENT '身份证籍贯'")
    private String born;

    /**
     * 联系方式
     */
    @Column(name = "tel", columnDefinition = "VARCHAR(255)   COMMENT '联系方式'")
    private String tel;

    /**
     * 入职时间
     */
    @Column(name = "startTime", columnDefinition = "DATE   COMMENT '入职时间'")
    private LocalDate startTime;

    /**
     * 转正时间
     */
    @Column(name = "officialTime", columnDefinition = "DATE   COMMENT '转正时间'")
    private LocalDate officialTime;

    /**
     * 前购买社保地市
     */
    @Column(name = "beforeCity", columnDefinition = "VARCHAR(255)   COMMENT '前购买社保地市'")
    private String beforeCity;

    /**
     * 前地市参保时间
     */
    @Column(name = "beforeTime", columnDefinition = "DATE   COMMENT '前地市参保时间'")
    private LocalDate beforeTime;

    /**
     * 参保户口
     */
    @Column(name = "bornLocal", columnDefinition = "VARCHAR(255)   COMMENT '参保户口'")
    private String bornLocal;

    /**
     * 参保时间
     */
    @Column(name = "secureTime", columnDefinition = "DATE   COMMENT '参保时间'")
    private LocalDate secureTime;

    /**
     * 参保类型
     */
    @Column(name = "type", columnDefinition = "VARCHAR(255)   COMMENT '参保类型'")
    private String type;

    /**
     * 购买方式
     */
    @Column(name = "payType", columnDefinition = "VARCHAR(255)   COMMENT '购买方式'")
    private String payType;

    /**
     * 已参保年限
     */
    @Column(name = "insuredYear", columnDefinition = "VARCHAR(255)   COMMENT '已参保年限'")
    private String insuredYear;

    /**
     * 运营商务部意见
     */
    @Column(name = "businessAdvice", columnDefinition = "VARCHAR(255)   COMMENT '运营商务部意见'")
    private String businessAdvice;

    /**
     * 总经办意见
     */
    @Column(name = "managerAdvice", columnDefinition = "VARCHAR(255)   COMMENT '总经办意见'")
    private String managerAdvice;

    /**
     * 社保状态
     */
    @Column(name = "status", columnDefinition = "VARCHAR(255)   COMMENT '社保状态'")
    private String status;

    /**
     * 备注
     */
    @Column(name = "description", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIdCart() {
        return idCart;
    }

    public void setIdCart(String idCart) {
        this.idCart = idCart;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getOfficialTime() {
        return officialTime;
    }

    public void setOfficialTime(LocalDate officialTime) {
        this.officialTime = officialTime;
    }

    public String getBeforeCity() {
        return beforeCity;
    }

    public void setBeforeCity(String beforeCity) {
        this.beforeCity = beforeCity;
    }

    public LocalDate getBeforeTime() {
        return beforeTime;
    }

    public void setBeforeTime(LocalDate beforeTime) {
        this.beforeTime = beforeTime;
    }

    public String getBornLocal() {
        return bornLocal;
    }

    public void setBornLocal(String bornLocal) {
        this.bornLocal = bornLocal;
    }

    public LocalDate getSecureTime() {
        return secureTime;
    }

    public void setSecureTime(LocalDate secureTime) {
        this.secureTime = secureTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getInsuredYear() {
        return insuredYear;
    }

    public void setInsuredYear(String insuredYear) {
        this.insuredYear = insuredYear;
    }

    public String getBusinessAdvice() {
        return businessAdvice;
    }

    public void setBusinessAdvice(String businessAdvice) {
        this.businessAdvice = businessAdvice;
    }

    public String getManagerAdvice() {
        return managerAdvice;
    }

    public void setManagerAdvice(String managerAdvice) {
        this.managerAdvice = managerAdvice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}