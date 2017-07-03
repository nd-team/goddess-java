package com.bjike.goddess.staffwelfaremanage.enums;

/**
 * 福利类型
 *
 * @Author: [Jason]
 * @Date: [17-4-5 上午11:00]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum WelfareType {

    /**
     * 线上福利
     */
    ONLINE(0),
    /**
     * 线下福利
     */
    OFFLINE(1);

    private int code;

    WelfareType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
