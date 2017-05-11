package com.bjike.goddess.outcarfare.vo;

/**
 * @Author: [yewenbo]
 * @Date: [2017-05-06 13:42]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PayVO {
    /**
     * 司机名称
     */
    private String driverName;

    /**
     * 出车日期
     */
    private String carDate;

    /**
     * 单号
     */
    private String number;

    /**
     * 地区
     */
    private String arrival;

    /**
     * 用车人
     */
    private String carUser;

    /**
     * 科目
     */
    private String project;

    /**
     * 租车单价
     */
    private Double carPrice;

    /**
     * 加班时长
     */
    private Double overtimeHour;

    /**
     * 加班单价
     */
    private Double overtimePrice;

    /**
     * 加班费
     */
    private Double overtimeFee;

    /**
     * 餐费补贴
     */
    private Double allowance;

    /**
     * 停车/过路费
     */
    private Double parkFee;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 填单人
     */
    private String single;

    /**
     * 是否付款
     */
    private boolean havePay;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCarDate() {
        return carDate;
    }

    public void setCarDate(String carDate) {
        this.carDate = carDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getCarUser() {
        return carUser;
    }

    public void setCarUser(String carUser) {
        this.carUser = carUser;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Double carPrice) {
        this.carPrice = carPrice;
    }

    public Double getOvertimeHour() {
        return overtimeHour;
    }

    public void setOvertimeHour(Double overtimeHour) {
        this.overtimeHour = overtimeHour;
    }

    public Double getOvertimePrice() {
        return overtimePrice;
    }

    public void setOvertimePrice(Double overtimePrice) {
        this.overtimePrice = overtimePrice;
    }

    public Double getOvertimeFee() {
        return overtimeFee;
    }

    public void setOvertimeFee(Double overtimeFee) {
        this.overtimeFee = overtimeFee;
    }

    public Double getAllowance() {
        return allowance;
    }

    public void setAllowance(Double allowance) {
        this.allowance = allowance;
    }

    public Double getParkFee() {
        return parkFee;
    }

    public void setParkFee(Double parkFee) {
        this.parkFee = parkFee;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public boolean isHavePay() {
        return havePay;
    }

    public void setHavePay(boolean havePay) {
        this.havePay = havePay;
    }
}
