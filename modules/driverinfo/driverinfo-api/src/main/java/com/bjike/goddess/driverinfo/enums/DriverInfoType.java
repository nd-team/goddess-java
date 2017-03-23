package com.bjike.goddess.driverinfo.enums;

/**
 * 司机信息数据类型枚举：租车协议、车辆信息
 *
 * @Author: [Jason]
 * @Date: [17-3-8 下午2:16]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum DriverInfoType {

    /**
     * 租车协议数据
     */
    RENTALS(0),
    /**
     * 车辆信息
     */
    CAR(1);

    private int code;

    DriverInfoType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}