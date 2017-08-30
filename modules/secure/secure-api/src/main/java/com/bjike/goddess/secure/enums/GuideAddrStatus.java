package com.bjike.goddess.secure.enums;

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
     * 审批
     */
    AUDIT(3),
    /**
     * 删除
     */
    DELETE(4),
    /**
     * 运营商务部审核
     */
    BUINESS(5),
    /**
     * 总经办审核
     */
    BOSS(6),
    /**
     * 社保管理负责人操作
     */
    CHARGE(7),
    /**
     * 查看
     */
    SEE(8),
    /**
     * 搜索
     */
    SEARCH(9);

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
