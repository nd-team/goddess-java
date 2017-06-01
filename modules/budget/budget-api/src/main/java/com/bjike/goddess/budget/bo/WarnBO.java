package com.bjike.goddess.budget.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 预警业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-17 10:35 ]
 * @Description: [ 预警业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WarnBO extends BaseBO {

    /**
     * 预警的值
     */
    private Double warnValue;


    public Double getWarnValue() {
        return warnValue;
    }

    public void setWarnValue(Double warnValue) {
        this.warnValue = warnValue;
    }
}