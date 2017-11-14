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
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

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
     * 资质/认证证书
     */
    @Column(name = "litterae",  columnDefinition = "VARCHAR(255)   COMMENT '资质/认证证书'")
    private String litterae;

    /**
     * 是否购买社保
     */
    @Column(name = "buySecurity",  columnDefinition = "TINYINT(2)   COMMENT '是否购买社保'")
    private Boolean buySecurity;

    /**
     * 社保购买类型
     */
    @Column(name = "securityType",  columnDefinition = "VARCHAR(255)   COMMENT '社保购买类型'")
    private String securityType;

    /**
     * 购买社保所属公司
     */
    @Column(name = "company",  columnDefinition = "VARCHAR(255)   COMMENT '购买社保所属公司'")
    private String company;


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
     * qq号
     */
    @Column(name = "QQ", columnDefinition = "VARCHAR(255)   COMMENT 'qq号'")
    private String QQ;

    /**
     * 个人邮箱账号
     */
    @Column(name = "selfEmail", columnDefinition = "VARCHAR(255)   COMMENT '个人邮箱账号'")
    private String selfEmail;

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
     * 目前住宿地址
     */
    @Column(name = "nowAddress", columnDefinition = "VARCHAR(255)   COMMENT '目前住宿地址'")
    private String nowAddress;

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
     * 离职时间
     */
    @Column(name = "dimissionTime",  columnDefinition = "DATE   COMMENT '离职时间'")
    private LocalDate dimissionTime;

    /**
     * 邮箱
     */
    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '邮箱'")
    private String email;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)  COMMENT '状态'")
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getLitterae() {
        return litterae;
    }

    public void setLitterae(String litterae) {
        this.litterae = litterae;
    }

    public Boolean getBuySecurity() {
        return buySecurity;
    }

    public void setBuySecurity(Boolean buySecurity) {
        this.buySecurity = buySecurity;
    }

    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getSelfEmail() {
        return selfEmail;
    }

    public void setSelfEmail(String selfEmail) {
        this.selfEmail = selfEmail;
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

    public String getNowAddress() {
        return nowAddress;
    }

    public void setNowAddress(String nowAddress) {
        this.nowAddress = nowAddress;
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

    public LocalDate getDimissionTime() {
        return dimissionTime;
    }

    public void setDimissionTime(LocalDate dimissionTime) {
        this.dimissionTime = dimissionTime;
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