package com.bjike.goddess.secure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 增员前
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-27 10:10 ]
 * @Description: [ 增员前 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "secure_beforeadd")
public class BeforeAdd extends BaseEntity {
    /**
     * 资质名称
     */
    @Column(name = "intelligence", columnDefinition = "VARCHAR(255)   COMMENT '资质名称'")
    private String intelligence;

    /**
     * 公司名称
     */
    @Column(name = "company", columnDefinition = "VARCHAR(255)   COMMENT '公司名称'")
    private String company;

    /**
     * 社保需增地市
     */
    @Column(name = "arrival", columnDefinition = "VARCHAR(255)   COMMENT '社保需增地市'")
    private String arrival;

    /**
     * 社保地市特殊说明
     */
    @Column(name = "arrivalDescrption", columnDefinition = "VARCHAR(255)   COMMENT '社保地市特殊说明'")
    private String arrivalDescrption;

    /**
     * 需增员时间
     */
    @Column(name = "addTime", columnDefinition = "DATETIME   COMMENT '需增员时间'")
    private String addTime;

    /**
     * 需参保时长
     */
    @Column(name = "time", columnDefinition = "DECIMAL(10,2)   COMMENT '需参保时长'")
    private Double time;

    /**
     * 增员参保周期特殊要求
     */
    @Column(name = "request", columnDefinition = "VARCHAR(255)   COMMENT '增员参保周期特殊要求'")
    private String request;

    /**
     * 当前各公司参保总人数
     */
    @Column(name = "companyCount", columnDefinition = "INT(11)  COMMENT '当前各公司参保总人数'")
    private Integer companyCount;

    /**
     * 当前各地市总参保人员
     */
    @Column(name = "cityCount", columnDefinition = "INT(11)  COMMENT '当前各地市总参保人员'")
    private Integer cityCount;

    /**
     * 可参保人员姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '可参保人员姓名'")
    private String name;

    /**
     * 可参保人员新增的地市
     */
    @Column(name = "addCity", columnDefinition = "VARCHAR(255)   COMMENT '可参保人员新增的地市'")
    private String addCity;

    /**
     * 备注
     */
    @Column(name = "description", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String description;

    /**
     * 是否增员
     */
    @Column(name = "increase", columnDefinition = "TINYINT(1) COMMENT '是否增员'")
    private boolean increase;

    public String getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(String intelligence) {
        this.intelligence = intelligence;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getArrivalDescrption() {
        return arrivalDescrption;
    }

    public void setArrivalDescrption(String arrivalDescrption) {
        this.arrivalDescrption = arrivalDescrption;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Integer getCompanyCount() {
        return companyCount;
    }

    public void setCompanyCount(Integer companyCount) {
        this.companyCount = companyCount;
    }

    public Integer getCityCount() {
        return cityCount;
    }

    public void setCityCount(Integer cityCount) {
        this.cityCount = cityCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddCity() {
        return addCity;
    }

    public void setAddCity(String addCity) {
        this.addCity = addCity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIncrease() {
        return increase;
    }

    public void setIncrease(boolean increase) {
        this.increase = increase;
    }
}