package com.bjike.goddess.allmeeting.enums;

/**
 * 会议形式
 * <p>
 * Created by ike on 17-5-31.
 */
public enum PermissionType {

    /**
     * 允许
     */
    ALLOW(0),

    /**
     * 禁止
     */
    FORBID(1);


    private int code;

    PermissionType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
