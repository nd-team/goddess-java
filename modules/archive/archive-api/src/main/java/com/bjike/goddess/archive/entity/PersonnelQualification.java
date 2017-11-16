package com.bjike.goddess.archive.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 人员资质
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 04:01 ]
 * @Description: [ 人员资质 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "archive_personnel_qualification")
public class PersonnelQualification extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "username", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String username;

    /**
     * 性别
     */
    @Column(name = "sex", columnDefinition = "VARCHAR(255)   COMMENT '性别'")
    private String sex;

    /**
     * 身份证号码
     */
    @Column(name = "identityCard", columnDefinition = "VARCHAR(255)   COMMENT '身份证号码'")
    private String identityCard;

    /**
     * 劳动关系类型
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "labor_id", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '劳动关系类型'")
    private LaborRelation labor;

    /**
     * 社保购买类型
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "social_id", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '社保购买类型'")
    private SocialSecurityType social;

    /**
     * 资质证书1
     */
    @Column(name = "certificateOne", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '资质证书1'")
    private String certificateOne;

    /**
     * 查询网站链接1
     */
    @Column(name = "websiteOne", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '查询网站链接1'")
    private String websiteOne;

    /**
     * 证件有效期限1
     */
    @Column(name = "effectiveOne", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '证件有效期限1'")
    private String effectiveOne;

    /**
     * 资质证书2
     */
    @Column(name = "certificateTwo", columnDefinition = "VARCHAR(255)   COMMENT '资质证书2'")
    private String certificateTwo;

    /**
     * 查询网站链接2
     */
    @Column(name = "websiteTwo", columnDefinition = "VARCHAR(255)   COMMENT '查询网站链接2'")
    private String websiteTwo;

    /**
     * 证件有效期限2
     */
    @Column(name = "effectiveTwo", columnDefinition = "VARCHAR(255)   COMMENT '证件有效期限2'")
    private String effectiveTwo;

    /**
     * 备注信息
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注信息'")
    private String remark;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LaborRelation getLabor() {
        return labor;
    }

    public void setLabor(LaborRelation labor) {
        this.labor = labor;
    }

    public SocialSecurityType getSocial() {
        return social;
    }

    public void setSocial(SocialSecurityType social) {
        this.social = social;
    }

    public String getCertificateOne() {
        return certificateOne;
    }

    public void setCertificateOne(String certificateOne) {
        this.certificateOne = certificateOne;
    }

    public String getWebsiteOne() {
        return websiteOne;
    }

    public void setWebsiteOne(String websiteOne) {
        this.websiteOne = websiteOne;
    }

    public String getEffectiveOne() {
        return effectiveOne;
    }

    public void setEffectiveOne(String effectiveOne) {
        this.effectiveOne = effectiveOne;
    }

    public String getCertificateTwo() {
        return certificateTwo;
    }

    public void setCertificateTwo(String certificateTwo) {
        this.certificateTwo = certificateTwo;
    }

    public String getWebsiteTwo() {
        return websiteTwo;
    }

    public void setWebsiteTwo(String websiteTwo) {
        this.websiteTwo = websiteTwo;
    }

    public String getEffectiveTwo() {
        return effectiveTwo;
    }

    public void setEffectiveTwo(String effectiveTwo) {
        this.effectiveTwo = effectiveTwo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }
}