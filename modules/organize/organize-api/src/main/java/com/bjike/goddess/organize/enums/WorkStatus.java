package com.bjike.goddess.organize.enums;

/**
 * 担任状态
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-06 15:34]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum WorkStatus {
    /**
     * 主职
     */
    MAIN(0),
    /**
     * 兼任
     */
    PARTJOB(1);
    private int code;

    WorkStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        String s = null;
        switch (code) {
            case 0:
                s = "主职";
                break;
            case 1:
                s = "兼任";
                break;
        }
        return s;
    }
}
