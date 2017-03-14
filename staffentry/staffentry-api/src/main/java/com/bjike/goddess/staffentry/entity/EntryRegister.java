package com.bjike.goddess.staffentry.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 员工入职登记
 * @Author: [tanghaixiang]
 * @Date: [2017-03-08 10:23]
 * @Description: [员工入职登记]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "staffentry_entryregister")
public class EntryRegister extends BaseEntity {

    /**
     * 员工编号
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '员工工号'" , unique = true)
    private String empNumber;
    /**
     * 姓名
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '姓名'")
    private String username;

    /**
     *性别0男1女
     */
    @Column(nullable = false,columnDefinition = "INT(11) COMMENT '性别0男1女'")
    private Integer gender;

    /**
     *出生年月日
     */
    @Column(nullable = false,columnDefinition = "DATE COMMENT '出生年月日'")
    private LocalDate birthday;

    /**
     *民族
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '民族'")
    private String nation;

    /**
     *婚姻状况
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '婚姻状况'")
    private String marriage;

    /**
     *政治面貌
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '政治面貌'")
    private String politicsStatus;

    /**
     *籍贯
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '籍贯'")
    private String nativePlace;

    /**
     *身高
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '身高'")
    private String stature;

    /**
     *专业
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '专业'")
    private String profession;

    /**
     *学历
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '学历'")
    private String education;

    /**
     *毕业学校
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '毕业学校'")
    private String schoolTag;

    /**
     *毕业时间
     */
    @Column(nullable = false,columnDefinition = "DATE COMMENT '毕业时间'")
    private LocalDate graduationDate;

    /**
     *健康状况
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '健康状况'")
    private String healthStatus;

    /**
     *QQ号
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'QQ号'")
    private String qq;

    /**
     *手机号
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '手机号'")
    private String phone;

    /**
     *个人邮箱账号
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '个人邮箱账号'")
    private String email;

    /**
     *紧急情况联系人
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '紧急情况联系人'")
    private String emergencyContact;

    /**
     *联系电话
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '联系电话'")
    private String phoneNumber;

    /**
     *身份证号码
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '身份证号码'")
    private String idCard;

    /**
     *户口地址
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '户口地址'")
    private String registeredAddress;

    /**
     *目前住宿地址
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '目前住宿地址'")
    private String location;

    /**
     *兴趣爱好
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '兴趣爱好'")
    private String hobbies;


    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getStature() {
        return stature;
    }

    public void setStature(String stature) {
        this.stature = stature;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSchoolTag() {
        return schoolTag;
    }

    public void setSchoolTag(String schoolTag) {
        this.schoolTag = schoolTag;
    }

    public LocalDate getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(LocalDate graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
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

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }
}
