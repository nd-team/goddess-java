package com.bjike.goddess.archive.enums;

/**
 * @Author: [yewenbo]
 * @Date: [2017-06-08 09:55]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum RotainCusPermissionType {
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

    RotainCusPermissionType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
