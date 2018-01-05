package com.bjike.goddess.accruedtax.enums;

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
     * 冻结
     */
    CONGEL(5),
    /**
     * 解冻
     */
    THAW(6),
    /**
     * 汇总
     */
    COLLECT(7),
    /**
     * 查看
     */
    SEE(8),
    /**
     * 分摊
     */
    SHARE(9);

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
