package com.bjike.goddess.secure.enums;

/**
 * 社保卡管理分类
 *
 * @Author: [chenjunhao]
 * @Date: [2017-06-05 19:57]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CartStatus {
    /**
     * 已领社保卡帐户
     */
    ALREADYRECEIVED(0),
    /**
     * 未领社保卡帐户
     */
    UNRECEIVED(1),
    /**
     * 预计可领取社保卡帐户
     */
    CANRECEIVED(2);

    private int value;

    public int getValue() {
        return value;
    }

    CartStatus(int value) {
        this.value = value;
    }
}
