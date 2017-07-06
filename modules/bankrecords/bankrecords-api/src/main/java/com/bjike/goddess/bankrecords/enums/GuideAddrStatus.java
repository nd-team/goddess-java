package com.bjike.goddess.bankrecords.enums;

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
     * 列表
     */
    ADD(1),
    /**
     * 列表
     */
    EDIT(2),
    /**
     * 删除
     */
    DELETE(3),
    /**
     * 汇总
     */
    COLLECT(4),
    /**
     * 分析
     */
    ANALYZE(5),
    /**
     * 对比
     */
    COMPARE(6),
    /**
     * 上传附件
     */
    UPLOAD(7),
    /**
     * 下载附件
     */
    DOWNLOAD(8),
    /**
     * 导入
     */
    IMPORT(9),
    /**
     * 导出
     */
    EXPORT(10),
    /**
     * 查看
     */
    SEE(11),
    /**
     * 查看附件
     */
    SEEFILE(12);

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
