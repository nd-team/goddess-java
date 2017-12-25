package com.bjike.goddess.market.enums;

/**
 * 导航栏类型
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-20 19:57]
 * @Description: [导航栏类型]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum GuideAddrStatus {

    /**
     * 列表
     */
    LIST(0),
    /**
     * 添加
     */
    ADD(1),
    /**
     * 编辑
     */
    EDIT(2),

    /**
     * 删除
     */
    DELETE(3),
    /**
     * 预算模块分析
     */
    BUDGETAUDIT(4),
    /**
     * 规划模块分析
     */
    PLANAUDIT(5),
    /**
     * 汇总
     */
    COLLECT(6);

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
