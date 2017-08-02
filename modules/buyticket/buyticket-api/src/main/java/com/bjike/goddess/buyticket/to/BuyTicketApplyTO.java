package com.bjike.goddess.buyticket.to;

import com.bjike.goddess.buyticket.enums.AuditType;
import com.bjike.goddess.buyticket.enums.TripType;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


/**
 * 车票购买申请
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 04:32 ]
 * @Description: [ 车票购买申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BuyTicketApplyTO extends BaseTO {

    /**
     * 申请人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "申请人不能为空")
    private String applicant;

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 部门/项目组
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "部门/项目组不能为空")
    private String department;

    /**
     * 乘车人
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "乘车人不能为空")
    private String[] passenger;

    /**
     * 乘车人数
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "乘车人数不能为空")
    private String passengerNum;

    /**
     * 购票原因
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "购票原因不能为空")
    private String ticketCause;

    /**
     * 车票类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "车票类型不能为空")
    private String ticketType;

    /**
     * 购买方式
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "购买方式不能为空")
    private String buyPattern;

    /**
     * 行程类型
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "行程类型不能为空")
    private TripType tripType;

    /**
     * 出发地
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "出发地不能为空")
    private String origin;

    /**
     * 目的地
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "目的地不能为空")
    private String destination;

    /**
     * 计划出发时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "计划出发时间不能为空")
    private String planDepartureTime;

    /**
     * 计划到达时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "计划到达时间不能为空")
    private String planArrivalTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 规划模块负责人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "规划模块负责人不能为空")
    private String planAuditor;

    /**
     * 规划模块审核意见
     */
    private AuditType planAuditOpinion;

    /**
     * 福利模块负责人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "福利模块负责人不能为空")
    private String welfAuditor;

    /**
     * 福利模块审核意见
     */
    private AuditType welfAuditOpinion;

    /**
     * 是否发送邮件
     */
    @NotNull(groups = {ADD.class}, message = "是否发送邮件不能为空")
    private Boolean sendEmail;

    /**
     * 发送对象
     */
    private String[] sendObject;


    public Boolean getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(Boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public String[] getSendObject() {
        return sendObject;
    }

    public void setSendObject(String[] sendObject) {
        this.sendObject = sendObject;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String[] getPassenger() {
        return passenger;
    }

    public void setPassenger(String[] passenger) {
        this.passenger = passenger;
    }

    public String getPassengerNum() {
        return passengerNum;
    }

    public void setPassengerNum(String passengerNum) {
        this.passengerNum = passengerNum;
    }

    public String getTicketCause() {
        return ticketCause;
    }

    public void setTicketCause(String ticketCause) {
        this.ticketCause = ticketCause;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getBuyPattern() {
        return buyPattern;
    }

    public void setBuyPattern(String buyPattern) {
        this.buyPattern = buyPattern;
    }

    public TripType getTripType() {
        return tripType;
    }

    public void setTripType(TripType tripType) {
        this.tripType = tripType;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPlanDepartureTime() {
        return planDepartureTime;
    }

    public void setPlanDepartureTime(String planDepartureTime) {
        this.planDepartureTime = planDepartureTime;
    }

    public String getPlanArrivalTime() {
        return planArrivalTime;
    }

    public void setPlanArrivalTime(String planArrivalTime) {
        this.planArrivalTime = planArrivalTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPlanAuditor() {
        return planAuditor;
    }

    public void setPlanAuditor(String planAuditor) {
        this.planAuditor = planAuditor;
    }

    public AuditType getPlanAuditOpinion() {
        return planAuditOpinion;
    }

    public void setPlanAuditOpinion(AuditType planAuditOpinion) {
        this.planAuditOpinion = planAuditOpinion;
    }

    public String getWelfAuditor() {
        return welfAuditor;
    }

    public void setWelfAuditor(String welfAuditor) {
        this.welfAuditor = welfAuditor;
    }

    public AuditType getWelfAuditOpinion() {
        return welfAuditOpinion;
    }

    public void setWelfAuditOpinion(AuditType welfAuditOpinion) {
        this.welfAuditOpinion = welfAuditOpinion;
    }
}