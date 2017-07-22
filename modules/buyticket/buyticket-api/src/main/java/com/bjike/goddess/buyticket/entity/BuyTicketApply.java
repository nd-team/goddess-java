package com.bjike.goddess.buyticket.entity;

import com.bjike.goddess.buyticket.enums.AuditType;
import com.bjike.goddess.buyticket.enums.TripType;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 车票购买申请
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 04:32 ]
 * @Description: [ 车票购买申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "buyticket_buyticketapply")
public class BuyTicketApply extends BaseEntity {

    /**
     * 申请人
     */
    @Column(name = "applicant", columnDefinition = "VARCHAR(255)   COMMENT '申请人'")
    private String applicant;

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 部门/项目组
     */
    @Column(name = "department", columnDefinition = "VARCHAR(255)   COMMENT '部门/项目组'")
    private String department;

    /**
     * 乘车人
     */
    @Column(name = "passenger", columnDefinition = "VARCHAR(255)   COMMENT '乘车人'")
    private String passenger;

    /**
     * 乘车人数
     */
    @Column(name = "passengerNum", columnDefinition = "VARCHAR(255)   COMMENT '乘车人数'")
    private String passengerNum;

    /**
     * 购票原因
     */
    @Column(name = "ticketCause", columnDefinition = "VARCHAR(255)   COMMENT '购票原因'")
    private String ticketCause;

    /**
     * 车票类型
     */
    @Column(name = "ticketType", columnDefinition = "VARCHAR(255)   COMMENT '车票类型'")
    private String ticketType;

    /**
     * 购买方式
     */
    @Column(name = "buyPattern", columnDefinition = "VARCHAR(255)   COMMENT '购买方式'")
    private String buyPattern;

    /**
     * 行程类型
     */
    @Column(name = "tripType",  columnDefinition = "INT(2)   COMMENT '行程类型'")
    private TripType tripType;

    /**
     * 出发地
     */
    @Column(name = "origin", columnDefinition = "VARCHAR(255)   COMMENT '出发地'")
    private String origin;

    /**
     * 目的地
     */
    @Column(name = "destination", columnDefinition = "VARCHAR(255)   COMMENT '目的地'")
    private String destination;

    /**
     * 计划出发时间
     */
    @Column(name = "planDepartureTime", columnDefinition = "DATETIME   COMMENT '计划出发时间'")
    private LocalDateTime planDepartureTime;

    /**
     * 计划到达时间
     */
    @Column(name = "planArrivalTime", columnDefinition = "DATETIME   COMMENT '计划到达时间'")
    private LocalDateTime planArrivalTime;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 规划模块负责人
     */
    @Column(name = "planAuditor",  columnDefinition = "VARCHAR(255)  COMMENT '规划模块负责人'")
    private String planAuditor;

    /**
     * 规划模块审核意见
     */
    @Column(name = "planAuditOpinion", columnDefinition = "TINYINT(2)   COMMENT '规划模块审核意见'")
    private AuditType planAuditOpinion;

    /**
     * 福利模块负责人
     */
    @Column(name = "welfAuditor",  columnDefinition = "VARCHAR(255)   COMMENT '福利模块负责人'")
    private String welfAuditor;

    /**
     * 福利模块审核意见
     */
    @Column(name = "welfAuditOpinion", columnDefinition = "TINYINT(2)  COMMENT '福利模块审核意见'")
    private AuditType welfAuditOpinion;


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

    public LocalDateTime getPlanDepartureTime() {
        return planDepartureTime;
    }

    public void setPlanDepartureTime(LocalDateTime planDepartureTime) {
        this.planDepartureTime = planDepartureTime;
    }

    public LocalDateTime getPlanArrivalTime() {
        return planArrivalTime;
    }

    public void setPlanArrivalTime(LocalDateTime planArrivalTime) {
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