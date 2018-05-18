package com.bjike.goddess.attendance.enums;

/**
 * 请假开始时间
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-09 09:33]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum StartTime {
    /**
     * 08:30
     */
    A(0),
    /**
     * 09:00
     */
    B(1),
    /**
     * 09:30
     */
    C(2),
    /**
     * 10:00
     */
    D(3),
    /**
     * 10:30
     */
    E(4),
    /**
     * 11:00
     */
    F(5),
    /**
     * 11:30
     */
    G(6),
    /**
     * 13:30
     */
    H(7),
    /**
     * 14:00
     */
    I(8),
    /**
     * 14:30
     */
    J(9),
    /**
     * 15:00
     */
    K(10),
    /**
     * 15:30
     */
    L(11),
    /**
     * 16:00
     */
    M(12),
    /**
     * 16:30
     */
    N(13),
    /**
     * 17:00
     */
    O(14),
    /**
     * 17:30
     */
    P(15);
    private int code;

    StartTime(int code) {
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
                s="08:30";
                break;
            case 1:
                s="09:00";
                break;
            case 2:
                s="09:30";
                break;
            case 3:
                s="10:00";
                break;
            case 4:
                s="10:30";
                break;
            case 5:
                s="11:00";
                break;
            case 6:
                s="11:30";
                break;
            case 7:
                s="13:30";
                break;
            case 8:
                s="14:00";
                break;
            case 9:
                s="14:30";
                break;
            case 10:
                s="15:00";
                break;
            case 11:
                s="15:30";
                break;
            case 12:
                s="16:00";
                break;
            case 13:
                s="16:30";
                break;
            case 14:
                s="17:00";
                break;
            case 15:
                s="17:30";
                break;
        }
        return s;
    }
}
