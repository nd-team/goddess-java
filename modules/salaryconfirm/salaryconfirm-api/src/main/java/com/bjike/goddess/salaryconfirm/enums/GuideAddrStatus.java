package com.bjike.goddess.salaryconfirm.enums;

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
    DELETE(4),
    /**
     * 部门汇总
     */
    DEPARTCOLLECT(7),
    /**
     * 个人汇总
     */
    PERSONALCOLLECT(7),
    /**
     * 地区汇总
     */
    AREACOLLECT(7),
    /**
     * 部门分析
     */
    DEPARTANALYZE(7),
    /**
     * 个人分析
     */
    PERSONALANALYZE(7),
    /**
     * 地区分析
     */
    AREAANALYZE(7),
    /**
     * 上传附件
     */
    UPLOAD(8),
    /**
     * 下载附件
     */
    DOWNLOAD(9),
    /**
     * 导入
     */
    IMPORT(10),
    /**
     * 导出
     */
    EXPORT(11),
    /**
     * 查看
     */
    SEE(12),
    /**
     * 查看附件
     */
    SEEFILE(13);

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
