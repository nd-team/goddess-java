package com.bjike.goddess.customerplatform.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 中标单位
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-30 11:20 ]
 * @Description: [ 中标单位 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customerplatform_bidunit")
public class BidUnit extends BaseEntity {

    /**
     * 企业名称
     */
    @Column(name = "company", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '企业名称'")
    private String company;

    /**
     * 公司成立时间
     */
    @Column(name = "createCompanyTime", nullable = true, columnDefinition = "DATE   COMMENT '公司成立时间'")
    private LocalDate createCompanyTime;

    /**
     * 公司类型
     */
    @Column(name = "companyType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公司类型'")
    private String companyType;

    /**
     * 主要产品和服务
     */
    @Column(name = "products", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '主要产品和服务'")
    private String products;

    /**
     * 注册资金
     */
    @Column(name = "money", nullable = true, columnDefinition = "DECIMAL(10,2)   COMMENT '注册资金'")
    private Double money;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 企业邮箱
     */
    @Column(name = "companyEmail", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '企业邮箱'")
    private String companyEmail;

    /**
     * 企业网站
     */
    @Column(name = "website", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '企业网站'")
    private String website;

    /**
     * 企业电话
     */
    @Column(name = "companyPhone", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '企业电话'")
    private String companyPhone;

    /**
     * 联系人名字
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '联系人名字'")
    private String name;

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


//    /**
//     * 业务类型
//     */
//    @Column(name = "businessType", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '业务类型'")
//    private String businessType;

    /**
     * 需求类型
     */
    @Column(name = "projectType", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '项目类型'")
    private String projectType;

    /**
     * 需求说明
     */
    @Column(name = "description", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '项目说明'")
    private String description;

    /**
     * 备注
     */
    @Column(name = "remark", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDate getCreateCompanyTime() {
        return createCompanyTime;
    }

    public void setCreateCompanyTime(LocalDate createCompanyTime) {
        this.createCompanyTime = createCompanyTime;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
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

<<<<<<< HEAD
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

=======
>>>>>>> upstream/develop
    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
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