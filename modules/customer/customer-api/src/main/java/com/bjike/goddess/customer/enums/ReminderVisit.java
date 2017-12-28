package com.bjike.goddess.customer.enums;


/**
 * 提醒拜访时间枚举
 *
 * @Author: [lijuntao]
 * @Date: [2017-03-15 16:48]
 * @Description: [提醒拜访时间枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ReminderVisit {

    /**
     * 提前一个小时
     */
    ADVANCEHOUR(0),
    /**
     * 提前一天
     */
    ADVANCEDAY(1),
    /**
     * 提前一周
     */
    ADVANCEWEEK(2),
    /**
     * 提前一个月
     */
    ADVANCEMONTH(3);

    private int code;

    ReminderVisit(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
