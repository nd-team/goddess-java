package com.bjike.goddess.dispatchcar.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 出车记录数据传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-12 05:26 ]
 * @Description: [ 出车记录数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DispatchCarInfoDTO extends BaseDTO {
    /**
     * 出车人
     */
    private String carUser;


    /**
     * 出车单号
     */
    private String number;

    public String getCarUser() {
        return carUser;
    }

    public void setCarUser(String carUser) {
        this.carUser = carUser;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}