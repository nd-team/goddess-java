package com.bjike.goddess.dispatchcar.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
/**
* 出车核对修改记录
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-25 11:24 ]
* @Description:	[ 出车核对修改记录 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CheckChangeCarDTO extends BaseDTO {
    /**
     * 用车人
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