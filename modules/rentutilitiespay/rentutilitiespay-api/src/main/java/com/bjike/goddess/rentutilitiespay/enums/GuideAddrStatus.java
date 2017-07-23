package com.bjike.goddess.rentutilitiespay.enums;

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
     * 运营财务部审核
     */
    FINANCEAUDIT(3),

    /**
     * 综合资源部审核
     */
    RESOURCEAUDIT(4),

    /**
     * 删除
     */
    DELETE(5),

    /**
     * 冻结
     */
    CONGEL(6),

    /**
     * 解冻
     */
    THAW(7),

    /**
     * 汇总
     */
    COLLECT(8),

    /**
     * 上传附件
     */
    UPLOAD(9),

    /**
     * 下载附件
     */
    DOWNLOAD(10),

    /**
     * 导入
     */
    IMPORT(11),

    /**
     * 导出
     */
    EXPORT(12),

    /**
     * 查看
     */
    SEE(13),

    /**
     * 查看附件
     */
    SEEFILE(14);

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    }
