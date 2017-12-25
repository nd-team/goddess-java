package com.bjike.goddess.businessinteraction.enums;

/**
 * 发送汇总邮件的发送间隔单位
 * @Author: [tanghaixiang]
 * @Date: [2017-03-16 19:10]
 * @Description: [发送汇总邮件的发送间隔单位]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CustomerSendUnit {

    /**
     * 分钟
     */
    MINUTE(0),
    /**
     * 小时
     */
    HOURS(1),
    /**
     * 天
     */
    DAY(2),
    /**
     * 周
     */
    WEEK(3),
    /**
     * 月
     */
    MONTH(4),
    /**
     * 季度
     */
    QUARTER(5),
    /**
     * 年
     */
    YEAR(6),
    ;

    private int code;

    CustomerSendUnit(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
