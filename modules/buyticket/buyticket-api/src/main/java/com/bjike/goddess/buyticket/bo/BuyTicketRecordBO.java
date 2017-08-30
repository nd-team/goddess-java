package com.bjike.goddess.buyticket.bo;

import com.bjike.goddess.buyticket.enums.TripType;
import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 车票购买记录业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 04:33 ]
 * @Description: [ 车票购买记录业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BuyTicketRecordBO extends BaseBO {

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
     * 乘车人岗位名称
     */
    private String passengerPostName;

    /**
     * 身份证号码
     */
    private String idCardNumber;

    /**
     * 联系电话
     */
    private String relationTel;

    /**
     * 购票原因
     */
    private String ticketCause;

    /**
     * 交通工具
     */
    private String vehicle;

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
     * 计划出发时间
     */
    private String planDepartureTime;

    /**
     * 计划到达时间
     */
    private String planArrivalTime;

    /**
     * 车票价格
     */
    private String ticketPrice;

    /**
     * 购买时间
     */
    private String buyTime;

    /**
     * 出发地
     */
    private String origin;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 班次/航班
     */
    private String shift;

    /**
     * 是否可选座
     */
    private Boolean optionalSeat;

    /**
     * 座位信息
     */
    private String seatInfo;

    /**
     * 出发时间
     */
    private String departureTime;

    /**
     * 到达时间
     */
    private String arrivalTime;

    /**
     * 订单号
     */
    private String orderNum;

    /**
     * 备注
     */
    private String remark;

    /**
     * 乘车人是否有确认乘车信息
     */
    private Boolean confirmPassengerInfo;

    /**
     * 确认信息时间
     */
    private String confirmInfoTime;


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

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
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

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
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

    public String getConfirmInfoTime() {
        return confirmInfoTime;
    }

    public void setConfirmInfoTime(String confirmInfoTime) {
        this.confirmInfoTime = confirmInfoTime;
    }
}