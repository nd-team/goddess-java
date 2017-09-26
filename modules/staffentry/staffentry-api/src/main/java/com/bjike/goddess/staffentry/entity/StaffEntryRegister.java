package com.bjike.goddess.staffentry.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 员工入职注册
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-15 07:03 ]
 * @Description: [ 员工入职注册 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffentry_staffentryregister")
public class StaffEntryRegister extends BaseEntity {

    /**
     * 所属部门/项目组
     */
    @Column(name = "department", columnDefinition = "VARCHAR(255)   COMMENT '所属部门'")
    private String department;
    /**
     * 联系电话
     */
    @Column(name = "contact_num", columnDefinition = "VARCHAR(255)   COMMENT '联系电话'")
    private String contactNum;

    /**
     * 入职日期
     */
    @Column(name = "entryDate", columnDefinition = "DATE   COMMENT '入职日期'")
    private LocalDate entryDate;
    /**
     * 是否住宿
     */
    @Column(name = "lodge", columnDefinition = "TINYINT(2)   COMMENT '是否住宿'")
    private Boolean lodge;
    /**
     * 是否使用公司电脑
     */
    @Column(name = "useCompanyComputer", columnDefinition = "TINYINT(2)   COMMENT '是否使用公司电脑'")
    private Boolean useCompanyComputer;
    /**
     * 入职地址
     */
    @Column(name = "entryAddress", columnDefinition = "VARCHAR(255)   COMMENT '入职地址'")
    private String entryAddress;
    /**
     * 是否入职
     */
    @Column(name = "entry", columnDefinition = "TINYINT(2)   COMMENT '是否入职'")
    private Boolean entry;
    /**
     * 未入职原因
     */
    @Column(name = "noEntryCause", columnDefinition = "TEXT   COMMENT '未入职原因'")
    private String noEntryCause;
    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;
    /**
     * 是否通告
     */
    @Column(name = "notice", columnDefinition = "TINYINT(2)   COMMENT '是否通告'")
    private Boolean notice;
    /**
     * 注册邮箱使用手机号
     */
    @Column(name = "registerUseNum", columnDefinition = "VARCHAR(255)   COMMENT '注册邮箱使用手机号'")
    private String registerUseNum;

    /**
     * 职位
     */
    @Column(name = "position", columnDefinition = "VARCHAR(255)   COMMENT '职位'")
    private String position;

    /**
     * 工作邮箱
     */
    @Column(name = "workEmail", columnDefinition = "VARCHAR(255)   COMMENT '工作邮箱'")
    private String workEmail;
    /**
     * 工作邮箱密码
     */
    @Column(name = "workEmailPassword", columnDefinition = "VARCHAR(255)   COMMENT '工作邮箱密码'")
    private String workEmailPassword;
    /**
     * 更改后的邮箱密码
     */
    @Column(name = "contactNum", columnDefinition = "VARCHAR(255)   COMMENT '入职日期'")
    private String changeEmailPassword;
    /**
     * 帐号密码是否已告知(是/否)
     */
    @Column(name = "tellStatus", columnDefinition = "VARCHAR(255)   COMMENT '帐号密码是否已告知(是/否)'")
    private String tellStatus;


    /**
     * 用户id
     */
    @Column(name = "userId", nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '用户id'")
    private String userId;


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public Boolean getLodge() {
        return lodge;
    }

    public void setLodge(Boolean lodge) {
        this.lodge = lodge;
    }

    public Boolean getUseCompanyComputer() {
        return useCompanyComputer;
    }

    public void setUseCompanyComputer(Boolean useCompanyComputer) {
        this.useCompanyComputer = useCompanyComputer;
    }

    public String getEntryAddress() {
        return entryAddress;
    }

    public void setEntryAddress(String entryAddress) {
        this.entryAddress = entryAddress;
    }

    public Boolean getEntry() {
        return entry;
    }

    public void setEntry(Boolean entry) {
        this.entry = entry;
    }

    public String getNoEntryCause() {
        return noEntryCause;
    }

    public void setNoEntryCause(String noEntryCause) {
        this.noEntryCause = noEntryCause;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Boolean getNotice() {
        return notice;
    }

    public void setNotice(Boolean notice) {
        this.notice = notice;
    }

    public String getRegisterUseNum() {
        return registerUseNum;
    }

    public void setRegisterUseNum(String registerUseNum) {
        this.registerUseNum = registerUseNum;
    }

    public String getChangeEmailPassword() {
        return changeEmailPassword;
    }

    public void setChangeEmailPassword(String changeEmailPassword) {
        this.changeEmailPassword = changeEmailPassword;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getWorkEmailPassword() {
        return workEmailPassword;
    }

    public void setWorkEmailPassword(String workEmailPassword) {
        this.workEmailPassword = workEmailPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTellStatus() {
        return tellStatus;
    }

    public void setTellStatus(String tellStatus) {
        this.tellStatus = tellStatus;
    }

}