package com.bjike.goddess.lendreimbursement.enums;

/**
 * 报销公司详细图筛选状态
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-04-06 10:47]
 * @Description: [报销公司详细图筛选状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ReimShapeDetailTypeStatus {

    /**
     * 项目组
     */
    GROUP(0),
    /**
     * 地区
     */
    AREA(1),
    /**
     * 项目名称
     */
    PROJECT(2),
    /**
     * 借款人或报销人
     */
    REIMER(2);

    private int code;

    ReimShapeDetailTypeStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
