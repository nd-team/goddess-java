package com.bjike.goddess.lendreimbursement.enums;

/**
 * 报销图形显示状态
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-04-06 10:47]
 * @Description: [报销图形显示状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ReimburseShapeStatus {

    /**
     * 年
     */
    YEAR(0),
    /**
     * 月
     */
    MONTH(1),
    /**
     * 周
     */
    WEEK(2),
    /**
     * 日
     */
    DAY(3),
    /**
     * 季度
     */
    QUART(4);

    private int code;

    ReimburseShapeStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
