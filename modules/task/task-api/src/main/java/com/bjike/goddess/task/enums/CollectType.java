package com.bjike.goddess.task.enums;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-22 14:56]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum  CollectType {

    /**
     * 数量
     */
    COUNT(1),
    /**
     * 明细
     */
    DETAIL(2);



    CollectType(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
