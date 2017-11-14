package com.bjike.goddess.attendance.enums;

/**
 * 假期类型
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-20 11:40]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum HolidayType {
    /**
     * 法定节假日
     */
    FESTIVAL(0),
    /**
     * 周末
     */
    WEEKEND(1),
    /**
     * 其他
     */
    OTHER(2);
    private int code;

    HolidayType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
