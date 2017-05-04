package com.bjike.goddess.buyticket.bo;

import com.bjike.goddess.buyticket.enums.AuditorType;
import com.bjike.goddess.buyticket.enums.TripType;
import com.bjike.goddess.common.api.bo.BaseBO;


/**
 * 车票购买申请业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 04:32 ]
 * @Description: [ 车票购买申请业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BuyTicketApplyBO extends BaseBO {

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
     * 负责人
     */
    private AuditorType auditor;

    /**
     * 审核意见
     */
    private String auditOpinion;


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

    public AuditorType getAuditor() {
        return auditor;
    }

    public void setAuditor(AuditorType auditor) {
        this.auditor = auditor;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }
}