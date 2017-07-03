package com.bjike.goddess.stockholdermeeting.enums;

/**
 * 会议组织内容状态
 *
 * @Author: [chenjunhao]
 * @Date: [2017-06-07 09:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum OrganizationStauts {
    /**
     * 冻结
     */
    FREEZE(0),
    /**
     * 正常
     */
    NORMAL(1);

    private int code;

    OrganizationStauts(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
