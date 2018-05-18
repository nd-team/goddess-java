package com.bjike.goddess.attendance.enums;

/**
 * 审批类型
 * @Author: [chenjunhao]
 * @Date: [2017-11-03 16:28]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AuditType {
    /**
     * 请假
     */
    VACATE(0),
    /**
     * 加班
     */
    OVERWORK(1),
    /**
     * 出勤有误审批
     */
    FINANCE(2);
    private int code;

    AuditType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
