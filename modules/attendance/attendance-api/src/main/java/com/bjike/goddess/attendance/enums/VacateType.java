package com.bjike.goddess.attendance.enums;

/**
 * 请假类型
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-07 17:16]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum VacateType {
    /**
     * 年假
     */
    ANNUAL(0),
    /**
     * 事假
     */
    MATTER(1),
    /**
     * 病假
     */
    SICK(2),
    /**
     * 调休
     */
    ADJUST(3),
    /**
     * 婚假
     */
    MARRY(4),
    /**
     * 产假
     */
    MATERNITY(5),
    /**
     * 陪产假
     */
    PATERNITY(6),
    /**
     * 产检假
     */
    CHECK(7),
    /**
     * 丧假
     */
    FUNERAL(8),
    /**
     * 其他
     */
    OTHER(9);
    private int code;

    VacateType(int code) {
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
                s="年假";
                break;
            case 1:
                s="事假";
                break;
            case 2:
                s="病假";
                break;
            case 3:
                s="调休";
                break;
            case 4:
                s="婚假";
                break;
            case 5:
                s="产假";
                break;
            case 6:
                s="陪产假";
                break;
            case 7:
                s="产检假";
                break;
            case 8:
                s="丧假";
                break;
            case 9:
                s="其他";
                break;
        }
        return s;
    }
}
