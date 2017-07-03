package com.bjike.goddess.outcarfare.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 司机汇总
 * @Author: [chenjunhao]
 * @Date: [2017-05-05 17:30]
 * @Description: [司机汇总 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DriverCountBO extends BaseBO {
    /**
     * 司机名称
     */
    private String driverName;
    /**
     * 租车单价
     */
    private Double carPrice;
    /**
     * 加班总费
     */
    private Double overtimeFeeSum;
    /**
     * 餐费总补贴
     */
    private Double allowanceSum;
    /**
     * 停车/过路总费
     */
    private Double parkFeeSum;
    /**
     * 总金额
     */
    private Double amountSum;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Double carPrice) {
        this.carPrice = carPrice;
    }

    public Double getOvertimeFeeSum() {
        return overtimeFeeSum;
    }

    public void setOvertimeFeeSum(Double overtimeFeeSum) {
        this.overtimeFeeSum = overtimeFeeSum;
    }

    public Double getAllowanceSum() {
        return allowanceSum;
    }

    public void setAllowanceSum(Double allowanceSum) {
        this.allowanceSum = allowanceSum;
    }

    public Double getParkFeeSum() {
        return parkFeeSum;
    }

    public void setParkFeeSum(Double parkFeeSum) {
        this.parkFeeSum = parkFeeSum;
    }

    public Double getAmountSum() {
        return amountSum;
    }

    public void setAmountSum(Double amountSum) {
        this.amountSum = amountSum;
    }
}
