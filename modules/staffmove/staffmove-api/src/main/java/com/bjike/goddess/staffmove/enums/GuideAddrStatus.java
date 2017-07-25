package com.bjike.goddess.staffmove.enums;

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
     * 规划模块审核
     */
    BUDGETAUDIT(3),
    /**
     * 预算模块审核
     */
    PLANAUDIT(4),
    /**
     * 原决策层审核
     */
    ORANAUDIT(5),
    /**
     * 调往决策层审核
     */
    TRAAUDIT(6),
    /**
     * 总经办审核
     */
    GENAUDIT(7),
    /**
     * 删除
     */
    DELETE(8),
    /**
     * 冻结
     */
    CONGEL(9),
    /**
     * 解冻
     */
    THAW(10),
    /**
     * 汇总
     */
    COLLECT(11),
    /**
     * 上传附件
     */
    UPLOAD(12),
    /**
     * 下载附件
     */
    DOWNLOAD(13),
    /**
     * 导入
     */
    IMPORT(14),
    /**
     * 导出
     */
    EXPORT(15),
    /**
     * 查看
     */
    SEE(16),
    /**
     * 查看附件
     */
    SEEFILE(17);

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
