package com.bjike.goddess.managepromotion.enums;

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
     * 模块负责人审核
     */
    HEADAUDIT(3),
    /**
     * 预算模块审核
     */
    BUDGETAUDIT(4),
    /**
     * 项目经理审核
     */
    MANAGERAUDIT(5),
    /**
     * 规划模块审核
     */
    PLANAUDIT(6),
    /**
     * 总经办审核
     */
    GENERALAUDIT(7),
    /**
     * 删除
     */
    DELETE(8),
    /**
     * 汇总
     */
    COLLECT(9),
    ;

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
