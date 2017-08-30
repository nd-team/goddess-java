package com.bjike.goddess.staffpay.enums;

/**
 * 工资确认类型
 * <p>
 * Created by ike on 17-5-20.
 */
public enum FindType {

    /**
     * 等待付款
     */
    WAIT(0),
    /**
     * 第一次付款
     */
    FIRST(1),
    /**
     * 已付款
     */
    CONFIRM(2);

    private int code;

    FindType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
