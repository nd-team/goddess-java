package com.bjike.goddess.archive.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 员工档案
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 10:32 ]
 * @Description: [ 员工档案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "archive_staff_records")
public class StaffRecords extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "username", unique = true, nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String username;

    /**
     * 员工编号
     */
    @Column(name = "serialNumber", unique = true, nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String serialNumber;

    /**
     * 项目组
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String project;

    /**
     * 职位
     */
    @Column(name = "position", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '职位'")
    private String position;

    /**
     * 学历
     */
    @Column(name = "education", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '学历'")
    private String education;

    /**
     * 专业
     */
    @Column(name = "major", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '专业'")
    private String major;

    /**
     * 毕业学校
     */
    @Column(name = "school", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '毕业学校'")
    private String school;

    /**
     * 毕业时间
     */
    @Column(name = "graduate", nullable = false, columnDefinition = "DATE   COMMENT '毕业时间'")
    private LocalDate graduate;

    /**
     * 入职时间
     */
    @Column(name = "entryTime", nullable = false, columnDefinition = "DATE   COMMENT '入职时间'")
    private LocalDate entryTime;

    /**
     * 在职时间(月)
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '在职时间(月)'")
    private Integer seniority;

    /**
     * 电话号码
     */
    @Column(name = "telephone", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '电话号码'")
    private String telephone;

    /**
     * 出生日期
     */
    @Column(name = "birth", nullable = false, columnDefinition = "DATE   COMMENT '出生日期'")
    private LocalDate birth;

    /**
     * 户籍地址
     */
    @Column(name = "address", columnDefinition = "VARCHAR(255)   COMMENT '户籍地址'")
    private String address;

    /**
     * 身份证号码
     */
    @Column(name = "identityCard", columnDefinition = "VARCHAR(255)   COMMENT '身份证号码'")
    private String identityCard;

    /**
     * 银行卡号码
     */
    @Column(name = "bankCard", columnDefinition = "VARCHAR(255)   COMMENT '银行卡号码'")
    private String bankCard;

    /**
     * 开户行
     */
    @Column(name = "bank", columnDefinition = "VARCHAR(255)   COMMENT '开户行'")
    private String bank;

    /**
     * 邮箱
     */
    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '邮箱'")
    private String email;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '状态'", insertable = false)
    private Status status;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public LocalDate getGraduate() {
        return graduate;
    }

    public void setGraduate(LocalDate graduate) {
        this.graduate = graduate;
    }

    public LocalDate getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDate entryTime) {
        this.entryTime = entryTime;
    }

    public Integer getSeniority() {
        return seniority;
    }

    public void setSeniority(Integer seniority) {
        this.seniority = seniority;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}