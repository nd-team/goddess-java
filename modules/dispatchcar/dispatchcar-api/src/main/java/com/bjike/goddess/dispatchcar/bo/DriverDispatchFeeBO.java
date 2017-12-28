package com.bjike.goddess.dispatchcar.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 出车记录业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-12 05:26 ]
 * @Description: [ 出车记录业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DriverDispatchFeeBO extends BaseBO {

    /**
     * 司机
     */
    private String driver;

    /**
     * 出车费
     */
    private Double fee;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }
}