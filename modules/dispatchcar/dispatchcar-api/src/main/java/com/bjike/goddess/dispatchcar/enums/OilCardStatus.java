package com.bjike.goddess.dispatchcar.enums;

/**
 * 油卡状态
 */
public enum OilCardStatus {

    /**
     * 闲置
     */
    IDLE(0),
    /**
     * 正常使用
     */
    USE(1),
    /**
     * 冻结使用
     */
    FREEZE(2);

    private int code;

    OilCardStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
