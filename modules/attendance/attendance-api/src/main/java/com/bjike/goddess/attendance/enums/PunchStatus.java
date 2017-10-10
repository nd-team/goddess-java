package com.bjike.goddess.attendance.enums;

/**
 * 打卡状态
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-22 15:33]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum PunchStatus {
    /**
     * 正常
     */
    NORMAL(0),
    /**
     * 迟到
     */
    LATE(1),
    /**
     * 外勤
     */
    OUTSIDE(2),
    /**
     * 免扣
     */
    FEE(3);
    private int code;

    PunchStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        String string = "";
        switch (code) {
            case 0:
                string = "正常";
                break;
            case 1:
                string = "迟到";
                break;
            case 2:
                string = "外勤";
                break;
            case 3:
                string = "免扣";
                break;
        }
        return string;
    }
}
