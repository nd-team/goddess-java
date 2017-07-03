package com.bjike.goddess.secure.vo;

import com.bjike.goddess.secure.enums.Pay;



/**
 * 挂靠到期表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 10:04 ]
 * @Description: [ 挂靠到期表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AttachedEndVO {

    /**
     * id
     */
    private String id;
    /**
     * 挂靠原因
     */
    private String reason;

    /**
     * 挂靠人姓名
     */
    private String attachedName;

    /**
     * 目前居住地
     */
    private String arrival;

    /**
     * 挂靠时长
     */
    private Double attachedTime;

    /**
     * 担保人姓名
     */
    private String assureName;

    /**
     * 挂靠人与担保人关系
     */
    private String reverse;

    /**
     * 担保人在司岗位
     */
    private String assureJob;

    /**
     * 担保人所属地区
     */
    private String assureArrival;

    /**
     * 担保人联系电话
     */
    private String assureTel;

    /**
     * 挂靠开始时间
     */
    private String startTime;

    /**
     * 挂靠结束时间
     */
    private String endTime;

    /**
     * 挂靠的公司
     */
    private String company;

    /**
     * 挂靠的缴费金
     */
    private Double money;

    /**
     * 金额的支付类型
     */
    private Pay pay;

    /**
     * 前参保地市
     */
    private String beforeCity;

    /**
     * 前参保时间
     */
    private String beforeTime;

    /**
     * 已参保年限
     */
    private String insuredYear;

    /**
     * 备注
     */
    private String decription;

    /**
     * 是否继续挂靠
     */
    private Boolean isAttachedAgin;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAttachedName() {
        return attachedName;
    }

    public void setAttachedName(String attachedName) {
        this.attachedName = attachedName;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Double getAttachedTime() {
        return attachedTime;
    }

    public void setAttachedTime(Double attachedTime) {
        this.attachedTime = attachedTime;
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

    public String getBeforeTime() {
        return beforeTime;
    }

    public void setBeforeTime(String beforeTime) {
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

    public Boolean getIsAttachedAgin() {
        return isAttachedAgin;
    }

    public void setIs_attached_agin(Boolean isAttachedAgin) {
        this.isAttachedAgin = isAttachedAgin;
    }
}