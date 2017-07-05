package com.bjike.goddess.checkfunds.enums;

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
     * 经办
     */
    HANDLE(1),
    /**
     * 提交
     */
    COMMIT(2),
    /**
     * 审批
     */
    AUDIT(3),
    /**
     * 余额调整
     */
    ADJUST(4),
    /**
     * 查看明细
     */
    DETAIL(5),
    /**
     * 差异
     */
    DIFFER(6),
    /**
     * 确认余额调整
     */
    CONFIRM(7),
    /**
     * 查看
     */
    SEE(8);

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
