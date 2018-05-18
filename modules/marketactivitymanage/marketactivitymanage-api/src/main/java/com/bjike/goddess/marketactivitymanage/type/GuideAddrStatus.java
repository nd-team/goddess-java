package com.bjike.goddess.marketactivitymanage.type;

/**
 * 导航权限
 * @Author: [lijuntao]
 * @Date: [2017-06-20 12:53]
 * @Description: [ ]
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
     * 运营资金审核
     */
    MONEYAUDIT(3),
    /**
     * 删除
     */
    DELETE(4),
    /**
     * 冻结
     */
    CONGEL(5),
    /**
     * 解冻
     */
    THAW(6),
    /**
     * 汇总
     */
    COLLECT(7),
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
    SEEFILE(13),
    /**
     * 决策层审核
     */
    DECISIONAUDIT(14);

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
