package com.bjike.goddess.accommodation.enums;

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
     * 商务发展部审核
     */
    BUSINESSAUDIT(3),
    /**
     * 运营财务部审核
     */
    FINANCEAUDIT(4),
    /**
     * 综合资源部审核
     */
    RESOURCEAUDIT(5),
    /**
     * 项目经理审核
     */
    MANAGERAUDIT(6),
    /**
     * 总经办审核
     */
    GENERALAUDIT(7),
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
