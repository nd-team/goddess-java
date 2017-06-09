package com.bjike.goddess.organize.enums;

/**
 * Created by ike on 17-5-15.
 */
public enum UserSetPermissionType {

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
    POSITION(2),
    /**
     * 部门
     */
    DEPART(3)
    ;
    private int code;

    UserSetPermissionType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
