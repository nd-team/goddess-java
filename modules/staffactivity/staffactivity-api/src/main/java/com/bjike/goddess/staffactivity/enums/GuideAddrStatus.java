package com.bjike.goddess.staffactivity.enums;

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
     * 汇总
     */
    COLLECT(5),
    /**
     * 查看
     */
    SEE(6),
    /**
     * 上传附件
     */
    UPLOAD(7),
    /**
     * 下载附件
     */
    DOWNLOAD(8),
    /**
     * 查看附件
     */
    SEEFILE(9),
    /**
     * 参与活动
     */
    JOIN(10),
    /**
     * 退出活动
     */
    EXIT(11),
    /**
     *运营商务部意见
     */
    BUSINESSADVICE(12),
    /**
     * 是否持续开展
     */
    CARRY(13),
    /**
     * 运营资金评价
     */
    FUNDEVALUATE(14),
    /**
     * 监督者评价
     */
    OVERSEERVALUATE(15),
    /**
     * 总经办意见
     */
    MANAGERADVICE(16),
    /**
     * 总经办评价
     */
    MANAGERVALUATE(16);

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
