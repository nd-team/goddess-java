package com.bjike.goddess.organize.enums;

/**
 * 人员状态
 * @Author: [chenjunhao]
 * @Date: [2017-09-06 15:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum  StaffStatus {
    /**
     * 在职
     */
    WORKING(0),
    /**
     * 已离职
     */
    HAVELEAVE(1),
    /**
     * 待离职
     */
    LEAVING(2),
    /**
     * 请假
     */
    REST(3)
    ;
    private int code;

    StaffStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
