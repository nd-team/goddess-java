package com.bjike.goddess.managementpromotion.enums;

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
     * 规划模块填写审核
     */
    GUIHUA(5),
    /**
     * 素养模块填写
     */
    SUYANG(6),
    /**
     * 项目经理审核
     */
    MANAGER(7),
    /**
     * 运营商务部预算模块审核
     */
    BUINESS(8),
    /**
     * 模块负责人审核
     */
    MODULE(9),
    /**
     * 总经理审核
     */
    BOSS(10),
    /**
     * 查看
     */
    SEE(11);

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
