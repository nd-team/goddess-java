package com.bjike.goddess.projectprocing.enums;

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
     * 汇总
     */
    COLLECT(6),
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
    SEEFILE(12),
    /**
     * 回款确认
     */
    RECEICONFIRMA(13),
    /**
     * 项目经理意见
     */
    PROJECTMANAGEOPINION(14),
    /**
     * 增值税发票通报
     */
    NOTICEINVOICE(15),
    /**
     * 付款
     */
    PAYMONEY(16),
    /**
     * 分配责任人
     */
    ASSIGNEDPERSON(17),
    /**
     * (核对分析)资金模块意见
     */
    CAPITALOPINION(18),
    /**
     * (确认)总经理审批
     */
    GENERALAPPROVAL(19),
    /**
     * 进度确认
     */
    SCHEDULECONFIRM(20),
    ;

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
