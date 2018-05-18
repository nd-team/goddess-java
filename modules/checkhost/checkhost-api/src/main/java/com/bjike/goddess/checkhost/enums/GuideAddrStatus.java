package com.bjike.goddess.checkhost.enums;

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
     * 部门审核
     */
    DEPARTADUIT(2),
    /**
     * 模块负责人审核
     */
    HEADADUIT(3),
    /**
     * 编辑
     */
    EDIT(4),
    /**
     * 删除
     */
    DELETE(5),
    /**
     * 查看
     */
    SEE(6);

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
