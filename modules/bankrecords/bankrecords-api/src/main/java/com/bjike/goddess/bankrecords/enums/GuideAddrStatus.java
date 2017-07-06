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
     * 删除
     */
    DELETE(1),
    /**
     * 汇总
     */
    COLLECT(2),
    /**
     * 分析
     */
    ANALYZE(3),
    /**
     * 对比
     */
    COMPARE(4),
    /**
     * 上传附件
     */
    UPLOAD(5),
    /**
     * 下载附件
     */
    DOWNLOAD(6),
    /**
     * 导入
     */
    IMPORT(7),
    /**
     * 导出
     */
    EXPORT(8),
    /**
     * 查看
     */
    SEE(9),
    /**
     * 查看附件
     */
    SEEFILE(10);

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
