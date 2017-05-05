package com.bjike.goddess.secure.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * 挂靠
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:12 ]
 * @Description: [ 挂靠 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AttachedTO extends BaseTO {

    /**
     * 挂靠人姓名
     */
    @NotBlank(groups = {ADD.class},message = "挂靠人姓名不能为空")
    private String attachedName;

    /**
     * 挂靠原因
     */
    @NotBlank(groups = {ADD.class},message = "挂靠原因不能为空")
    private String reason;

    /**
     * 挂靠城市
     */
    @NotBlank(groups = {ADD.class},message = "挂靠城市不能为空")
    private String city;

    /**
     * 挂靠人目前居住地
     */
    @NotBlank(groups = {ADD.class},message = "挂靠人目前居住地不能为空")
    private String attachedArrival;

    /**
     * 挂靠时长
     */
    @NotNull(groups = {ADD.class},message = "挂靠时长不能为空")
    @DecimalMin(value = "0.00",groups = {ADD.class},message = "挂靠时长必须大于0")
    private Double time;

    /**
     * 担保人姓名
     */
    @NotBlank(groups = {ADD.class},message = "担保人姓名不能为空")
    private String assureName;

    /**
     * 挂靠人与担保人关系
     */
    @NotBlank(groups = {ADD.class},message = "挂靠人与担保人关系不能为空")
    private String reverse;

    /**
     * 担保人在司岗位
     */
    @NotBlank(groups = {ADD.class},message = "担保人在司岗位不能为空")
    private String assureJob;

    /**
     * 担保人所属地区
     */
    @NotBlank(groups = {ADD.class},message = "担保人所属地区不能为空")
    private String assureArrival;

    /**
     * 担保人联系电话
     */
    @NotBlank(groups = {ADD.class},message = "担保人联系电话不能为空")
    private String assureTel;

    /**
     * 挂靠开始时间
     */
    @NotBlank(groups = {ADD.class},message = "挂靠开始时间不能为空")
    private String startTime;

    /**
     * 挂靠结束时间
     */
    @NotBlank(groups = {ADD.class},message = "挂靠结束时间不能为空")
    private String endTime;

    /**
     * 挂靠的公司
     */
    @NotBlank(groups = {ADD.class},message = "挂靠的公司不能为空")
    private String company;

    /**
     * 审批意见
     */
    @NotNull(groups = {EDIT.class},message = "审批意见不能为空")
    private boolean advice;

    /**
     * 挂靠的缴费金
     */
    @NotNull(groups = {EDIT.class},message = "挂靠的缴费金不能为空")
    @DecimalMin(value = "0.00",groups = {EDIT.class},message = "挂靠的缴费金必须大于0")
    private Double money;

    /**
     * 金额的支付类型
     */
    @NotBlank(groups = {EDIT.class},message = "金额的支付类型不能为空")
    private String paid;

    /**
     * 参保记录
     */
    private String record;

    /**
     * 备注
     */
    @NotBlank(groups = {EDIT.class},message = "备注不能为空")
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

    public boolean getAdvice() {
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