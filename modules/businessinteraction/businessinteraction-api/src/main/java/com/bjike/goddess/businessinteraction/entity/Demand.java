package com.bjike.goddess.businessinteraction.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 互动平台需求描述
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:21 ]
 * @Description: [ 互动平台需求描述 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessinteraction_demand")
public class Demand extends BaseEntity {

    /**
     * 需求者来源
     */
    @Column(name = "origin", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '需求者来源'")
    private String origin;

    /**
     * 需求者姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '需求者姓名'")
    private String name;

    /**
     * 需求者职能
     */
    @Column(name = "work",  columnDefinition = "VARCHAR(255)   COMMENT '需求者职能'")
    private String work;

    /**
     * 需求者所在公司名称
     */
    @Column(name = "companyName",  columnDefinition = "VARCHAR(255)   COMMENT '需求者所在公司名称'")
    private String companyName;

    /**
     * 需求者所在公司类型
     */
    @Column(name = "companyType",  columnDefinition = "VARCHAR(255)   COMMENT '需求者所在公司类型'")
    private String companyType;

    /**
     * 联系方式
     */
    @Column(name = "contactWay",  columnDefinition = "VARCHAR(255)   COMMENT '联系方式'")
    private String contactWay;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 业务方向
     */
    @Column(name = "businessTarget",  columnDefinition = "VARCHAR(255)   COMMENT '业务方向'")
    private String businessTarget;

    /**
     * 规模
     */
    @Column(name = "size", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '规模'")
    private String size;

    /**
     * 人工
     */
    @Column(name = "artificial",  columnDefinition = "VARCHAR(255)   COMMENT '人工'")
    private String artificial;

    /**
     * 设备
     */
    @Column(name = "device",  columnDefinition = "VARCHAR(255)   COMMENT '设备'")
    private String device;

    /**
     * 工时
     */
    @Column(name = "workTime",  columnDefinition = "VARCHAR(255)   COMMENT '工时'")
    private String workTime;

    /**
     * 价格
     */
    @Column(name = "price",  columnDefinition = "VARCHAR(255)   COMMENT '价格'")
    private String price;

    /**
     * 专业
     */
    @Column(name = "profession", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '专业'")
    private String profession;


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBusinessTarget() {
        return businessTarget;
    }

    public void setBusinessTarget(String businessTarget) {
        this.businessTarget = businessTarget;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getArtificial() {
        return artificial;
    }

    public void setArtificial(String artificial) {
        this.artificial = artificial;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}