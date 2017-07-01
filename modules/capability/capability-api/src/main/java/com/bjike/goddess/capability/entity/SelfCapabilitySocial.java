package com.bjike.goddess.capability.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 个人社交能力展示
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-16 11:05 ]
 * @Description: [ 个人社交能力展示 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "capability_selfcapabilitysocial")
public class SelfCapabilitySocial extends BaseEntity {

    /**
     * 个人社交资源姓名
     */
    @Column(name = "contactName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '个人社交资源姓名'")
    private String contactName;

    /**
     * 性别
     */
    @Column(name = "sex", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '性别'")
    private String sex;

    /**
     * 联系方式
     */
    @Column(name = "contactWay", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '联系方式'")
    private String contactWay;

    /**
     * 邮箱
     */
    @Column(name = "emailName", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '邮箱'")
    private String emailName;

    /**
     * QQ/微信
     */
    @Column(name = "qqOrWechat", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT 'QQ/微信'")
    private String qqOrWechat;

    /**
     * 籍贯
     */
    @Column(name = "natives", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '籍贯'")
    private String natives;

    /**
     * 爱好
     */
    @Column(name = "hobby", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '爱好'")
    private String hobby;

    /**
     * 性格评价
     */
    @Column(name = "charact", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '性格评价'")
    private String charact;

    /**
     * 家庭成员
     */
    @Column(name = "family", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '家庭成员'")
    private String family;

    /**
     * 家庭成员与本人关系
     */
    @Column(name = "familyRelation", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '家庭成员与本人关系'")
    private String familyRelation;

    /**
     * 求学和培训经历
     */
    @Column(name = "studyExperience", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '求学和培训经历'")
    private String studyExperience;

    /**
     * 接触经历
     */
    @Column(name = "connectExperience", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '接触经历'")
    private String connectExperience;

    /**
     * 以往工作地区
     */
    @Column(name = "oldWorkPlace", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '以往工作地区'")
    private String oldWorkPlace;

    /**
     * 生活地区
     */
    @Column(name = "livePlace", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '生活地区'")
    private String livePlace;

    /**
     * 成长地区
     */
    @Column(name = "growthPlace", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '成长地区'")
    private String growthPlace;

    /**
     * 个人能力id
     */
    @Column(name = "selfCapabilityId", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '个人能力id'")
    private String selfCapabilityId;

    /**
     * 组织机构
     */
    @Column(name = "organization", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '组织机构'")
    private String organization;

    /**
     * 现在工作地区
     */
    @Column(name = "nowWorkPlace", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '现在工作地区'")
    private String nowWorkPlace;

    /**
     * 所在公司
     */
    @Column(name = "nowCompany", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '所在公司'")
    private String nowCompany;

    /**
     * 岗位
     */
    @Column(name = "station", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String station;

    /**
     * 职权
     */
    @Column(name = "duty", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '职权'")
    private String duty;


    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getEmailName() {
        return emailName;
    }

    public void setEmailName(String emailName) {
        this.emailName = emailName;
    }

    public String getQqOrWechat() {
        return qqOrWechat;
    }

    public void setQqOrWechat(String qqOrWechat) {
        this.qqOrWechat = qqOrWechat;
    }

    public String getNatives() {
        return natives;
    }

    public void setNatives(String natives) {
        this.natives = natives;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getCharact() {
        return charact;
    }

    public void setCharact(String charact) {
        this.charact = charact;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getFamilyRelation() {
        return familyRelation;
    }

    public void setFamilyRelation(String familyRelation) {
        this.familyRelation = familyRelation;
    }

    public String getStudyExperience() {
        return studyExperience;
    }

    public void setStudyExperience(String studyExperience) {
        this.studyExperience = studyExperience;
    }

    public String getConnectExperience() {
        return connectExperience;
    }

    public void setConnectExperience(String connectExperience) {
        this.connectExperience = connectExperience;
    }

    public String getOldWorkPlace() {
        return oldWorkPlace;
    }

    public void setOldWorkPlace(String oldWorkPlace) {
        this.oldWorkPlace = oldWorkPlace;
    }

    public String getLivePlace() {
        return livePlace;
    }

    public void setLivePlace(String livePlace) {
        this.livePlace = livePlace;
    }

    public String getGrowthPlace() {
        return growthPlace;
    }

    public void setGrowthPlace(String growthPlace) {
        this.growthPlace = growthPlace;
    }

    public String getSelfCapabilityId() {
        return selfCapabilityId;
    }

    public void setSelfCapabilityId(String selfCapabilityId) {
        this.selfCapabilityId = selfCapabilityId;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getNowWorkPlace() {
        return nowWorkPlace;
    }

    public void setNowWorkPlace(String nowWorkPlace) {
        this.nowWorkPlace = nowWorkPlace;
    }

    public String getNowCompany() {
        return nowCompany;
    }

    public void setNowCompany(String nowCompany) {
        this.nowCompany = nowCompany;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }
}