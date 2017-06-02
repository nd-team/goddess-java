package com.bjike.goddess.salaryconfirm.enums;

/**
 * 薪资核算确认类型
 * <p>
 * Created by ike on 17-5-20.
 */
public enum FindType {

    /**
     * 等待确认薪资
     */
    WAIT(0),
    /**
     * 已确认薪资
     */
    CONFIRM(1),
    /**
     * 已付薪资
     */
    PAYED(2),
    /**
     * 已确认收款薪资
     */
    RECEIVED(3);

    private int code;

    FindType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
