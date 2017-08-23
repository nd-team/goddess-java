package com.bjike.goddess.announcement.enums;

/**
 * 公告状态
 *
 * @Author: [chenjunhao]
 * @Date: [2017-07-07 14:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Status {
    /**
     * 冻结
     */
    FREEZE(0),
    /**
     * 正常
     */
    NORMAL(1);

    private int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
