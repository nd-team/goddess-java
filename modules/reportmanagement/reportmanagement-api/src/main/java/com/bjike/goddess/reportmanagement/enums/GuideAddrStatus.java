package com.bjike.goddess.reportmanagement.enums;

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
     * 删除
     */
    DELETE(3),
    /**
     * 冻结
     */
    CONGEL(4),
    /**
     * 解冻
     */
    THAW(5),
    /**
     * 上传附件
     */
    UPLOAD(6),
    /**
     * 下载附件
     */
    DOWNLOAD(7),
    /**
     * 查看
     */
    SEE(8),
    /**
     * 查看附件
     */
    SEEFILE(9);

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
