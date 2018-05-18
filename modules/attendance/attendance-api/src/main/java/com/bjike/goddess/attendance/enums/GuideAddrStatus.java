package com.bjike.goddess.attendance.enums;

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
     * 审核
     */
    AUDIT(3),
    /**
     * 删除
     */
    DELETE(4),
    /**
     * 搜索
     */
    SEE(5),
    /**
     * 补录
     */
    RECORD(6),

    /**
     * 查看
     */
    SEARCH(7),

    /**
     * 打卡
     */
    PUNCH(8),

    /**
     * 请假
     */
    LEAVE(9),

    /**
     * 加班
     */
    OVERTIME(10),

    /**
     * 录入
     */
    JOIN(11),

    /**
     * 审核加班
     */
    AUDITOVER(12),

    /**
     * 审核请假
     */
    AUDITLEAVE(13),

    /**
     * 申请加班
     */
    APPLY(14),

    ;

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
