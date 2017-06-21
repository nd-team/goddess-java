package com.bjike.goddess.progressmanage.enums;

/**
 * 权限类型
 *
 * Created by ike on 17-6-9.
 */
public enum PermissionType {

    /**
     * 个人
     */
    PERSON(0),
    /**
     * 部门或项目组
     */
    DEPART(1),
    /**
     * 全部
     */
    ALL(2);

    PermissionType(int code) {
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
