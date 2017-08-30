package com.bjike.goddess.buyticket.vo;

import com.bjike.goddess.buyticket.enums.AuditType;
import com.bjike.goddess.buyticket.enums.TripType;


/**
 * 车票购买申请表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 04:32 ]
 * @Description: [ 车票购买申请表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BuyTicketApplyVO {

    /**
     * id
     */
    private String id;
    /**
     * 申请人
     */
    private String applicant;

    /**
     * 地区
     */
    private String area;

    /**
     * 部门/项目组
     */
    private String department;

    /**
     * 乘车人
     */
    private String passenger;

    /**
     * 乘车人数
     */
    private String passengerNum;

    /**
     * 购票原因
     */
    private String ticketCause;

    /**
     * 车票类型
     */
    private String ticketType;

    /**
     * 购买方式
     */
    private String buyPattern;

    /**
     * 行程类型
     */
    private TripType tripType;

    /**
     * 出发地
     */
    private String origin;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 计划出发时间
     */
    private String planDepartureTime;

    /**
     * 计划到达时间
     */
    private String planArrivalTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 规划模块负责人
     */
    private String planAuditor;

    /**
     * 规划模块审核意见
     */
    private AuditType planAuditOpinion;

    /**
     * 福利模块负责人
     */
    private String welfAuditor;

    /**
     * 福利模块审核意见
     */
    private AuditType welfAuditOpinion;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
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