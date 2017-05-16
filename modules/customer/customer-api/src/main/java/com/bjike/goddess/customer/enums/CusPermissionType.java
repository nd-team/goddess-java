package com.bjike.goddess.customer.enums;

/**
 * Created by ike on 17-5-15.
 */
public enum CusPermissionType {

    /**
     * 层级
     */
    LEVEL(0),
    /**
     * 模块
     */
    MODULE(1),
    /**
     * 岗位
     */
    POSITION(2)
    ;
    private int code;

    CusPermissionType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
