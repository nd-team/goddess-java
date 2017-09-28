package com.bjike.goddess.lendreimbursement.entity;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-09-07 15:59]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Status {

    THAW(0)//解冻
    , CONGEAL(1)//冻结
    , DELETE(2)//删除
    ;

    private int value;

    private Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
