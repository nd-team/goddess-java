package com.bjike.goddess.secure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


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
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
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
    @Column(name = "team",  columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
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
    @Column(name = "idCart",columnDefinition = "VARCHAR(255)   COMMENT '身份证号码'")
    private String idCart;

    /**
     * 身份证籍贯
     */
    @Column(name = "arrival", columnDefinition = "VARCHAR(255)   COMMENT '身份证籍贯'")
    private String arrival;

    /**
     * 联系方式
     */
    @Column(name = "tel",  columnDefinition = "VARCHAR(255)   COMMENT '联系方式'")
    private String tel;

    /**
     * 入职时间
     */
    @Column(name = "startTime",  columnDefinition = "DATETIME   COMMENT '入职时间'")
    private String startTime;

    /**
     * 转正时间
     */
    @Column(name = "officialTime", columnDefinition = "DATETIME   COMMENT '转正时间'")
    private String officialTime;

    /**
     * 参保公司
     */
    @Column(name = "company",  columnDefinition = "VARCHAR(255)   COMMENT '参保公司'")
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
    @Column(name = "type",  columnDefinition = "VARCHAR(255)   COMMENT '参保类型'")
    private String type;

    /**
     * 购买方式
     */
    @Column(name = "payType",  columnDefinition = "VARCHAR(255)   COMMENT '购买方式'")
    private String payType;

    /**
     * 参保记录
     */
    @Column(name = "record", columnDefinition = "VARCHAR(255)   COMMENT '参保记录'")
    private String record;


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

    public String getGroup1() {
        return team;
    }

    public void setGroup1(String team) {
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

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getOfficialTime() {
        return officialTime;
    }

    public void setOfficialTime(String officialTime) {
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

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}