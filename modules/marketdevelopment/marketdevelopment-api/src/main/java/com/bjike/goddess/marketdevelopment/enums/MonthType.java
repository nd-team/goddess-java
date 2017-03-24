package com.bjike.goddess.marketdevelopment.enums;

/**
 * 月份类型
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 05:57 ]
 * @Description: [ 月份类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public enum MonthType {
    /**
     * 一月
     */
    JAN(1),
    /**
     * 二月
     */
    FEB(2),
    /**
     * 三月
     */
    MAR(3),
    /**
     * 四月
     */
    APR(4),
    /**
     * 五月
     */
    MAY(5),
    /**
     * 六月
     */
    JUN(6),
    /**
     * 七月
     */
    JUL(7),
    /**
     * 八月
     */
    AUG(8),
    /**
     * 九月
     */
    SEP(9),
    /**
     * 十月
     */
    OCT(10),
    /**
     * 十一月
     */
    NOV(11),
    /**
     * 十二月
     */
    DEC(12);

    private int code;

    private MonthType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getValueString() {
        switch (this.code) {
            case 1:
                return "一月";
            case 2:
                return "二月";
            case 3:
                return "三月";
            case 4:
                return "四月";
            case 5:
                return "五月";
            case 6:
                return "六月";
            case 7:
                return "七月";
            case 8:
                return "八月";
            case 9:
                return "九月";
            case 10:
                return "十月";
            case 11:
                return "十一月";
            case 12:
                return "十二月";
            default:
                return "一月";
        }
    }
}
