package com.bjike.goddess.buyticket.entity;

import com.bjike.goddess.buyticket.enums.TripType;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 车票购买记录
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 04:33 ]
 * @Description: [ 车票购买记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "buyticket_buyticketrecord")
public class BuyTicketRecord extends BaseEntity {

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
     * 乘车人岗位名称
     */
    @Column(name = "passengerPostName", columnDefinition = "VARCHAR(255)   COMMENT '乘车人岗位名称'")
    private String passengerPostName;

    /**
     * 身份证号码
     */
    @Column(name = "idCardNumber", columnDefinition = "VARCHAR(255)   COMMENT '身份证号码'")
    private String idCardNumber;

    /**
     * 联系电话
     */
    @Column(name = "relationTel", columnDefinition = "VARCHAR(255)   COMMENT '联系电话'")
    private String relationTel;

    /**
     * 购票原因
     */
    @Column(name = "ticketCause", columnDefinition = "VARCHAR(255)   COMMENT '购票原因'")
    private String ticketCause;

    /**
     * 交通工具
     */
    @Column(name = "vehicle", columnDefinition = "VARCHAR(255)   COMMENT '交通工具'")
    private String vehicle;

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
     * 车票价格
     */
    @Column(name = "ticketPrice", columnDefinition = "VARCHAR(255)   COMMENT '车票价格'")
    private String ticketPrice;

    /**
     * 购买时间
     */
    @Column(name = "buyTime", columnDefinition = "DATETIME   COMMENT '购买时间'")
    private LocalDateTime buyTime;

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
     * 班次/航班
     */
    @Column(name = "shift", columnDefinition = "VARCHAR(255)   COMMENT '班次/航班'")
    private String shift;

    /**
     * 是否可选座
     */
    @Column(name = "is_optionalSeat", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否可选座'", insertable = false)
    private Boolean optionalSeat;

    /**
     * 座位信息
     */
    @Column(name = "seatInfo", columnDefinition = "VARCHAR(255)   COMMENT '座位信息'")
    private String seatInfo;

    /**
     * 出发时间
     */
    @Column(name = "departureTime", columnDefinition = "DATETIME   COMMENT '出发时间'")
    private LocalDateTime departureTime;

    /**
     * 到达时间
     */
    @Column(name = "arrivalTime", columnDefinition = "DATETIME   COMMENT '到达时间'")
    private LocalDateTime arrivalTime;

    /**
     * 订单号
     */
    @Column(name = "orderNum", columnDefinition = "VARCHAR(255)   COMMENT '订单号'")
    private String orderNum;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 乘车人是否有确认乘车信息
     */
    @Column(name = "is_confirmPassengerInfo", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '乘车人是否有确认乘车信息'", insertable = false)
    private Boolean confirmPassengerInfo;

    /**
     * 确认信息时间
     */
    @Column(name = "confirmInfoTime", columnDefinition = "DATETIME   COMMENT '确认信息时间'")
    private LocalDateTime confirmInfoTime;


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

    public String getPassengerPostName() {
        return passengerPostName;
    }

    public void setPassengerPostName(String passengerPostName) {
        this.passengerPostName = passengerPostName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getRelationTel() {
        return relationTel;
    }

    public void setRelationTel(String relationTel) {
        this.relationTel = relationTel;
    }

    public String getTicketCause() {
        return ticketCause;
    }

    public void setTicketCause(String ticketCause) {
        this.ticketCause = ticketCause;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
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

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public LocalDateTime getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(LocalDateTime buyTime) {
        this.buyTime = buyTime;
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

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Boolean getOptionalSeat() {
        return optionalSeat;
    }

    public void setOptionalSeat(Boolean optionalSeat) {
        this.optionalSeat = optionalSeat;
    }

    public String getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(String seatInfo) {
        this.seatInfo = seatInfo;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getConfirmPassengerInfo() {
        return confirmPassengerInfo;
    }

    public void setConfirmPassengerInfo(Boolean confirmPassengerInfo) {
        this.confirmPassengerInfo = confirmPassengerInfo;
    }

    public LocalDateTime getConfirmInfoTime() {
        return confirmInfoTime;
    }

    public void setConfirmInfoTime(LocalDateTime confirmInfoTime) {
        this.confirmInfoTime = confirmInfoTime;
    }
}