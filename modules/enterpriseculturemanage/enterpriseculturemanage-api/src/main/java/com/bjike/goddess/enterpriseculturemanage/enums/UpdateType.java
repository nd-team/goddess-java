package com.bjike.goddess.enterpriseculturemanage.enums;

/**
 * 保留或覆盖
 *
 * @Author: [Jason]
 * @Date: [17-4-1 上午9:45]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum UpdateType {

    /**
     * 覆盖
     */
    COVER(0),

    /**
     * 保留
     */
    RESERVE(1);

    private int code;

    UpdateType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
