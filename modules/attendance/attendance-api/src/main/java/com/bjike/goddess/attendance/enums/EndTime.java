package com.bjike.goddess.attendance.enums;

/**
 * 请假结束时间
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-09 09:33]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum EndTime {
    /**
     * 09:00
     */
    A(0),
    /**
     * 09:30
     */
    B(1),
    /**
     * 10:00
     */
    C(2),
    /**
     * 10:30
     */
    D(3),
    /**
     * 11:00
     */
    E(4),
    /**
     * 11:30
     */
    F(5),
    /**
     * 12:00
     */
    G(6),
    /**
     * 14:00
     */
    H(7),
    /**
     * 14:30
     */
    I(8),
    /**
     * 15:00
     */
    J(9),
    /**
     * 15:30
     */
    K(10),
    /**
     * 16:00
     */
    L(11),
    /**
     * 16:30
     */
    M(12),
    /**
     * 17:00
     */
    N(13),
    /**
     * 17:30
     */
    O(14),
    /**
     * 18:00
     */
    P(15);
    private int code;

    EndTime(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        String s = "";
        switch (code) {
            case 0:
                s = "09:00";
                break;
            case 1:
                s = "09:30";
                break;
            case 2:
                s = "10:00";
                break;
            case 3:
                s = "10:30";
                break;
            case 4:
                s = "11:00";
                break;
            case 5:
                s = "11:30";
                break;
            case 6:
                s = "12:00";
                break;
            case 7:
                s = "14:00";
                break;
            case 8:
                s = "14:30";
                break;
            case 9:
                s = "15:00";
                break;
            case 10:
                s = "15:30";
                break;
            case 11:
                s = "16:00";
                break;
            case 12:
                s = "16:30";
                break;
            case 13:
                s = "17:00";
                break;
            case 14:
                s = "17:30";
                break;
            case 15:
                s = "18:00";
                break;
        }
        return s;
    }
}
