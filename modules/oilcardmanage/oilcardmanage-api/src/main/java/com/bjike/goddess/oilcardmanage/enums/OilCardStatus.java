package com.bjike.goddess.oilcardmanage.enums;

/**
 * 油卡状态
 */
public enum OilCardStatus {

    /**
     * 闲置
     */
    IDLE(0),
    /**
     * 正常适用
     */
    USE(1),
    /**
     * 冻结适用
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
