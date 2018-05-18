package com.bjike.goddess.secure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 社保增员信息名单
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 03:02 ]
 * @Description: [ 社保增员信息名单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "secure_addemployee")
public class AddEmployee extends BaseEntity {

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
     * 岗位
     */
    @Column(name = "job", columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String job;

    /**
     * 岗位层级
     */
    @Column(name = "jobLevel", columnDefinition = "VARCHAR(255)   COMMENT '岗位层级'")
    private String jobLevel;

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
     * 参保公司
     */
    @Column(name = "company", columnDefinition = "VARCHAR(255)   COMMENT '参保公司'")
    private String company;

    /**
     * 参保地市
     */
    @Column(name = "secureCity", columnDefinition = "VARCHAR(255)   COMMENT '参保地市'")
    private String secureCity;

    /**
     * 参保户口
     */
    @Column(name = "bornLocal", columnDefinition = "VARCHAR(255)   COMMENT '参保户口'")
    private String bornLocal;

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
     * 前参保时间
     */
    @Column(name = "beforeTime", columnDefinition = "DATE   COMMENT '前参保时间'")
    private LocalDate beforeTime;

    /**
     * 前参保地市
     */
    @Column(name = "beforeCity", columnDefinition = "VARCHAR(255)   COMMENT '前参保地市'")
    private String beforeCity;

    /**
     * 已参保年限
     */
    @Column(name = "insuredYear", columnDefinition = "VARCHAR(255)   COMMENT '已参保年限'")
    private String insuredYear;

    /**
     * 增员时间
     */
    @Column(name = "secureTime", columnDefinition = "DATE   COMMENT '参保时间'")
    private LocalDate secureTime;

    /**
     * 运营商务部意见
     */
    @Column(name = "businessAdvice", columnDefinition = "VARCHAR(255)   COMMENT '运营商务部意见'")
    private String businessAdvice;
    /**
     * 前参保公司
     */
    @Column(name = "beforeCompany", columnDefinition = "VARCHAR(255)   COMMENT '前参保公司'")
    private String beforeCompany;

    /**
     * 增员月份
     */
    @Column(name = "month", nullable = false, columnDefinition = "INT(11)   COMMENT '增员月份'")
    private Integer month;

    /**
     * 增员年份
     */
    @Column(name = "year", nullable = false, columnDefinition = "INT(11)   COMMENT '增员年份'")
    private Integer year;

    public String getBeforeCompany() {
        return beforeCompany;
    }

    public void setBeforeCompany(String beforeCompany) {
        this.beforeCompany = beforeCompany;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSecureCity() {
        return secureCity;
    }

    public void setSecureCity(String secureCity) {
        this.secureCity = secureCity;
    }

    public String getBornLocal() {
        return bornLocal;
    }

    public void setBornLocal(String bornLocal) {
        this.bornLocal = bornLocal;
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

    public LocalDate getBeforeTime() {
        return beforeTime;
    }

    public void setBeforeTime(LocalDate beforeTime) {
        this.beforeTime = beforeTime;
    }

    public String getBeforeCity() {
        return beforeCity;
    }

    public void setBeforeCity(String beforeCity) {
        this.beforeCity = beforeCity;
    }

    public String getInsuredYear() {
        return insuredYear;
    }

    public void setInsuredYear(String insuredYear) {
        this.insuredYear = insuredYear;
    }

    public LocalDate getSecureTime() {
        return secureTime;
    }

    public void setSecureTime(LocalDate secureTime) {
        this.secureTime = secureTime;
    }

    public String getBusinessAdvice() {
        return businessAdvice;
    }

    public void setBusinessAdvice(String businessAdvice) {
        this.businessAdvice = businessAdvice;
    }
}