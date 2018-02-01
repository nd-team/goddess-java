package com.bjike.goddess.customerplatform.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 站点
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-30 11:29 ]
 * @Description: [ 站点 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customerplatform_site")
public class Site extends BaseEntity {
    /**
     * 站点经度
     */
    @Column(name = "siteLatitude", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '站点经度'")
    private String siteLatitude;
    /**
     * 站点纬度
     */
    @Column(name = "siteLongitude", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '站点纬度'")
    private String siteLongitude;
    /**
     * 归属运营商
     */
    @Column(name = "ownershipOperator", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '归属运营商'")
    private String ownershipOperator;

    /**
     * 站点名称
     */
    @Column(name = "siteName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '站点名称'")
    private String siteName;

    /**
     * 站点类型
     */
    @Column(name = "siteType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '站点类型'")
    private String siteType;

    /**
     * 联系人姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '联系人姓名'")
    private String name;
    /**
     * 省份
     */
    @Column(name = "provinces", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '省份'")
    private String provinces;
    /**
     * 市
     */
    @Column(name = "city", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '市'")
    private String city;
    /**
     * 区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '区'")
    private String area;

    /**
     * 联系人性别
     */
    @Column(name = "sex", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '联系人性别'")
    private String sex;

    /**
     * 联系人职位
     */
    @Column(name = "position", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '联系人职位'")
    private String position;

    /**
     * 联系人电话
     */
    @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '联系人电话'")
    private String phone;

    /**
     * 联系人微信
     */
    @Column(name = "wechat", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '联系人微信'")
    private String wechat;

    /**
     * 联系人邮箱
     */
    @Column(name = "email", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '联系人邮箱'")
    private String email;

    /**
     * 需求类型
     */
    @Column(name = "demandType", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '需求类型'")
    private String demandType;

    /**
     * 需求说明
     */
    @Column(name = "description", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '需求说明'")
    private String description;

    /**
     * 备注
     */
    @Column(name = "remark", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    public String getSiteLatitude() {
        return siteLatitude;
    }

    public void setSiteLatitude(String siteLatitude) {
        this.siteLatitude = siteLatitude;
    }

    public String getSiteLongitude() {
        return siteLongitude;
    }

    public void setSiteLongitude(String siteLongitude) {
        this.siteLongitude = siteLongitude;
    }

    public String getOwnershipOperator() {
        return ownershipOperator;
    }

    public void setOwnershipOperator(String ownershipOperator) {
        this.ownershipOperator = ownershipOperator;
    }

    public String getProvinces() {
        return provinces;
    }

    public void setProvinces(String provinces) {
        this.provinces = provinces;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDemandType() {
        return demandType;
    }

    public void setDemandType(String demandType) {
        this.demandType = demandType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}