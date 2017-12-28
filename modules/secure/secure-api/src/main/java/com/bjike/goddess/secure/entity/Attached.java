package com.bjike.goddess.secure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.secure.enums.Pay;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


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
    @Column(name = "attachedArrival", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '挂靠人目前居住地'")
    private String attachedArrival;

    /**
     * 挂靠时长
     */
    @Column(name = "time", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '挂靠时长'")
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
    @Column(name = "assureJob", columnDefinition = "VARCHAR(255)   COMMENT '担保人在司岗位'")
    private String assureJob;

    /**
     * 担保人所属地区
     */
    @Column(name = "assureArrival", columnDefinition = "VARCHAR(255)   COMMENT '担保人所属地区'")
    private String assureArrival;

    /**
     * 担保人联系电话
     */
    @Column(name = "assureTel", columnDefinition = "VARCHAR(255)   COMMENT '担保人联系电话'")
    private String assureTel;

    /**
     * 挂靠开始时间
     */
    @Column(name = "startTime", columnDefinition = "DATE  COMMENT '挂靠开始时间'")
    private LocalDate startTime;

    /**
     * 挂靠结束时间
     */
    @Column(name = "endTime", columnDefinition = "DATE  COMMENT '挂靠结束时间'")
    private LocalDate endTime;

    /**
     * 挂靠的公司
     */
    @Column(name = "company", columnDefinition = "VARCHAR(255)   COMMENT '挂靠的公司'")
    private String company;

    /**
     * 审批意见
     */
    @Column(name = "advice", columnDefinition = "VARCHAR(255)  COMMENT '审批意见'")
    private String advice;

    /**
     * 挂靠的缴费金
     */
    @Column(name = "money", columnDefinition = "DECIMAL(10,2)   COMMENT '挂靠的缴费金'")
    private Double money;

    /**
     * 金额的支付类型
     */
    @Column(name = "pay", nullable = false, columnDefinition = "TINYINT(2)  COMMENT '金额的支付类型'")
    private Pay pay;

    /**
     * 前参保地市
     */
    @Column(name = "beforeCity", columnDefinition = "VARCHAR(255)   COMMENT '前参保地市'")
    private String beforeCity;

    /**
     * 前参保时间
     */
    @Column(name = "beforeTime", columnDefinition = "DATE   COMMENT '前参保时间'")
    private LocalDate beforeTime;

    /**
     * 已参保年限
     */
    @Column(name = "insuredYear", columnDefinition = "VARCHAR(255)   COMMENT '已参保年限'")
    private String insuredYear;
    /**
     * 是否挂靠到期
     */
    @Column(name = "is_affiliated", columnDefinition = "VARCHAR(255)   COMMENT '是否挂靠到期'")
    private Boolean affiliated;

    /**
     * 备注
     */
    @Column(name = "decription", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String decription;

    public Boolean getAffiliated() {
        return affiliated;
    }

    public void setAffiliated(Boolean affiliated) {
        this.affiliated = affiliated;
    }

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

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Pay getPay() {
        return pay;
    }

    public void setPay(Pay pay) {
        this.pay = pay;
    }

    public String getBeforeCity() {
        return beforeCity;
    }

    public void setBeforeCity(String beforeCity) {
        this.beforeCity = beforeCity;
    }

    public LocalDate getBeforeTime() {
        return beforeTime;
    }

    public void setBeforeTime(LocalDate beforeTime) {
        this.beforeTime = beforeTime;
    }

    public String getInsuredYear() {
        return insuredYear;
    }

    public void setInsuredYear(String insuredYear) {
        this.insuredYear = insuredYear;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}