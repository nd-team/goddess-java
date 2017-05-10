package com.bjike.goddess.secure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 挂靠
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:12 ]
 * @Description: [ 挂靠 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "secure_attached")
public class Attached extends BaseEntity {

    /**
     * 挂靠人姓名
     */
    @Column(name = "attachedName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '挂靠人姓名'")
    private String attachedName;

    /**
     * 挂靠原因
     */
    @Column(name = "reason", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '挂靠原因'")
    private String reason;

    /**
     * 挂靠城市
     */
    @Column(name = "city", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '挂靠城市'")
    private String city;

    /**
     * 挂靠人目前居住地
     */
    @Column(name = "attachedArrival", columnDefinition = "VARCHAR(255)   COMMENT '挂靠人目前居住地'")
    private String attachedArrival;

    /**
     * 挂靠时长
     */
    @Column(name = "time", columnDefinition = "DECIMAL(10,2)   COMMENT '挂靠时长'")
    private Double time;

    /**
     * 担保人姓名
     */
    @Column(name = "assureName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '担保人姓名'")
    private String assureName;

    /**
     * 挂靠人与担保人关系
     */
    @Column(name = "reverse", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '挂靠人与担保人关系'")
    private String reverse;

    /**
     * 担保人在司岗位
     */
    @Column(name = "assureJob", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '担保人在司岗位'")
    private String assureJob;

    /**
     * 担保人所属地区
     */
    @Column(name = "assureArrival", columnDefinition = "VARCHAR(255)   COMMENT '担保人所属地区'")
    private String assureArrival;

    /**
     * 担保人联系电话
     */
    @Column(name = "assureTel", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '担保人联系电话'")
    private String assureTel;

    /**
     * 挂靠开始时间
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "DATETIME   COMMENT '挂靠开始时间'")
    private String startTime;

    /**
     * 挂靠结束时间
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "DATETIME   COMMENT '挂靠结束时间'")
    private String endTime;

    /**
     * 挂靠的公司
     */
    @Column(name = "company", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '挂靠的公司'")
    private String company;

    /**
     * 审批意见
     */
    @Column(name = "advice", columnDefinition = "TINYINT(1)  COMMENT '审批意见'")
    private boolean advice;

    /**
     * 挂靠的缴费金
     */
    @Column(name = "money", columnDefinition = "DECIMAL(10,2)   COMMENT '挂靠的缴费金'")
    private Double money;

    /**
     * 金额的支付类型
     */
    @Column(name = "paid", columnDefinition = "VARCHAR(255)   COMMENT '金额的支付类型'")
    private String paid;

    /**
     * 参保记录
     */
    @Column(name = "record", columnDefinition = "VARCHAR(255)   COMMENT '参保记录'")
    private String record;

    /**
     * 备注
     */
    @Column(name = "decription", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String decription;

    public String getAttachedName() {
        return attachedName;
    }

    public void setAttachedName(String attachedName) {
        this.attachedName = attachedName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAttachedArrival() {
        return attachedArrival;
    }

    public void setAttachedArrival(String attachedArrival) {
        this.attachedArrival = attachedArrival;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getAssureName() {
        return assureName;
    }

    public void setAssureName(String assureName) {
        this.assureName = assureName;
    }

    public String getReverse() {
        return reverse;
    }

    public void setReverse(String reverse) {
        this.reverse = reverse;
    }

    public String getAssureJob() {
        return assureJob;
    }

    public void setAssureJob(String assureJob) {
        this.assureJob = assureJob;
    }

    public String getAssureArrival() {
        return assureArrival;
    }

    public void setAssureArrival(String assureArrival) {
        this.assureArrival = assureArrival;
    }

    public String getAssureTel() {
        return assureTel;
    }

    public void setAssureTel(String assureTel) {
        this.assureTel = assureTel;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean isAdvice() {
        return advice;
    }

    public void setAdvice(boolean advice) {
        this.advice = advice;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}