package com.bjike.goddess.attendance.enums;

/**
 * 星期数
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-23 13:50]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Week {
    /**
     * 周一
     */
    ONE(0),
    /**
     * 周二
     */
    TWO(1),
    /**
     * 周三
     */
    THREE(2),
    /**
     * 周四
     */
    FOUR(3),
    /**
     * 周五
     */
    FIVE(4),
    /**
     * 周六
     */
    SIX(5),
    /**
     * 周日
     */
    SEVEN(6);

    private int code;

    Week(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        String s="";
        switch (code){
            case 0:
                s="周一";
                break;
            case 1:
                s="周二";
                break;
            case 2:
                s="周三";
                break;
            case 3:
                s="周四";
                break;
            case 4:
                s="周五";
                break;
            case 5:
                s="周六";
                break;
            case 6:
                s="周日";
                break;
        }
        return s;
    }
}
