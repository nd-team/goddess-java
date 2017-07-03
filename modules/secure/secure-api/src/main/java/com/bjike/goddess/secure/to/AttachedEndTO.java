package com.bjike.goddess.secure.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.secure.enums.Pay;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;


/**
 * 挂靠到期
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 10:04 ]
 * @Description: [ 挂靠到期 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AttachedEndTO extends BaseTO {

    /**
     * 挂靠原因
     */
//    @NotBlank(groups = {EDIT.class},message = "挂靠原因不能为空")
    private String reason;

    /**
     * 挂靠人姓名
     */
//    @NotBlank(groups = {EDIT.class},message = "挂靠人姓名不能为空")
    private String attachedName;

    /**
     * 目前居住地
     */
//    @NotBlank(groups = {EDIT.class},message = "目前居住地不能为空")
    private String arrival;

    /**
     * 挂靠时长
     */
//    @NotNull(groups = {EDIT.class},message = "挂靠时长不能为空")
//    @DecimalMin(value = "0.00",groups = {EDIT.class},message = "挂靠时长必须大于0")
    private Double attachedTime;

    /**
     * 担保人姓名
     */
//    @NotBlank(groups = {EDIT.class},message = "担保人姓名不能为空")
    private String assureName;

    /**
     * 挂靠人与担保人关系
     */
//    @NotBlank(groups = {EDIT.class},message = "挂靠人与担保人关系不能为空")
    private String reverse;

    /**
     * 担保人在司岗位
     */
//    @NotBlank(groups = {EDIT.class},message = "担保人在司岗位不能为空")
    private String assureJob;

    /**
     * 担保人所属地区
     */
//    @NotBlank(groups = {EDIT.class},message = "担保人所属地区不能为空")
    private String assureArrival;

    /**
     * 担保人联系电话
     */
//    @NotBlank(groups = {EDIT.class},message = "担保人联系电话不能为空")
    private String assureTel;

    /**
     * 挂靠开始时间
     */
//    @NotBlank(groups = {EDIT.class},message = "挂靠开始时间不能为空")
    private String startTime;

    /**
     * 挂靠结束时间
     */
//    @NotBlank(groups = {EDIT.class},message = "挂靠结束时间不能为空")
    private String endTime;

    /**
     * 挂靠的公司
     */
//    @NotBlank(groups = {EDIT.class},message = "挂靠的公司不能为空")
    private String company;

    /**
     * 挂靠的缴费金
     */
//    @NotNull(groups = {EDIT.class},message = "挂靠的缴费金不能为空")
//    @DecimalMin(value = "0.00",groups = {EDIT.class},message = "挂靠的缴费金必须大于0")
    private Double money;

    /**
     * 金额的支付类型
     */
//    @NotNull(groups = {EDIT.class},message = "金额的支付类型不能为空")
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
//    @NotBlank(groups = {EDIT.class},message = "备注不能为空")
    private String decription;

    /**
     * 是否继续挂靠
     */
    @NotNull(groups = {EDIT.class},message = "是否继续挂靠不能为空")
    private Boolean isAttachedAgin;


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

    public void setIsAttachedAgin(Boolean isAttachedAgin) {
        this.isAttachedAgin = isAttachedAgin;
    }
}