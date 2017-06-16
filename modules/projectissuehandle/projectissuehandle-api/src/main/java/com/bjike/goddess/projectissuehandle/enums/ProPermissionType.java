package com.bjike.goddess.projectissuehandle.enums;

/**
 * Created by ike on 17-5-15.
 */
public enum ProPermissionType {

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

    ProPermissionType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
