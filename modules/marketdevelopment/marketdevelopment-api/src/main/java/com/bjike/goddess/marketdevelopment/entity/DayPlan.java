package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 天计划
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:08 ]
 * @Description: [ 天计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_day_plan")
public class DayPlan extends BaseEntity {

    /**
     * 序号
     */
    @Column(name = "serialNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '序号'")
    private String serialNumber;

    /**
     * 时间
     */
    @Column(name = "time", nullable = false, columnDefinition = "DATE  COMMENT '时间'")
    private LocalDate time;

    /**
     * 业务状态(立项前/立项后)
     */
    @Column(name = "is_business", nullable = false, columnDefinition = "TINYINT(2)    COMMENT '业务状态(立项前/立项后)'")
    private Boolean business;

    /**
     * 业务类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务类型'")
    private String type;

    /**
     * 业务方向科目
     */
    @Column(name = "source", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向科目'")
    private String source;

    /**
     * 公司名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公司名称'")
    private String name;

    /**
     * 计划工作内容
     */
    @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '计划工作内容'")
    private String content;

    /**
     * 所属阶段
     */
    @Column(name = "phase", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所属阶段'")
    private String phase;

    /**
     * 任务量
     */
    @Column(name = "quota", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '任务量'")
    private Double quota;

    /**
     * 任务人
     */
    @Column(name = "own", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '任务人'")
    private String own;

    /**
     * 洽谈方式
     */
    @Column(name = "negotiation", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '洽谈方式'")
    private String negotiation;

    /**
     * 商务预算费
     */
    @Column(name = "budget", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '商务预算费'")
    private Double budget;

    /**
     * 客户姓名
     */
    @Column(name = "customer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '客户姓名'")
    private String customer;

    /**
     * 客户电话
     */
    @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '客户电话'")
    private String phone;

    /**
     * 商务内容
     */
    @Column(name = "businessContent", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '商务内容'")
    private String businessContent;

    /**
     * 配合人
     */
    @Column(name = "coordinate", columnDefinition = "VARCHAR(255)   COMMENT '配合人'")
    private String coordinate;


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public Boolean getBusiness() {
        return business;
    }

    public void setBusiness(Boolean business) {
        this.business = business;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }

    public String getOwn() {
        return own;
    }

    public void setOwn(String own) {
        this.own = own;
    }

    public String getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(String negotiation) {
        this.negotiation = negotiation;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBusinessContent() {
        return businessContent;
    }

    public void setBusinessContent(String businessContent) {
        this.businessContent = businessContent;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }
}