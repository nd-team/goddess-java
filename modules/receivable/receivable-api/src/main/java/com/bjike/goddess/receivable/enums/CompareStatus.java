package com.bjike.goddess.receivable.enums;

/**
 * Created by ike on 17-6-23.
 */
public enum CompareStatus {
    /**
     * 地区
     */
    AREA(0),
    /**
     * 项目
     */
    PROJECT(1),
    /**
     * 总包单位
     */
    UNIT(2),
    ;
    private int code;

    private CompareStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }


}
