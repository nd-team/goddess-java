package com.bjike.goddess.lendreimbursement.enums;

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
     * 查看/审核详情
     */
    SEE(11),
    /**
     * 查看附件
     */
    SEEFILE(12),
    /**
     * 负责人审核、确认冻结、取消冻结
     */
    CHARGEAUDIT(13),
    /**
     * 总经办审批
     */
    MANAGEAUDIT(14),
    /**
     * 财务运营部审核
     */
    FINACEAUDIT(15),
    /**
     * 运营部申请冻结
     */
    FINACECONGEL(16),
    /**
     * 负责人确认冻结
     */
    CHARGECONGEL(17),

    /**
     * 负责人取消冻结
     */
    CHARGETHAW(18),

    /**
     * 付款
     */
    PAY(19),

    /**
     * 收款确认
     */
    RECIVEMONEYSURE(20),
    /**
     * 收款确认
     */
    RECIVEMONEYSURE2(21),

    /**
     * 还款、寄件
     */
    RETURN(22),

    /**
     * 生成记账凭证
     */
    VOUCHER(23),

    /**
     * 还款核对
     */
    RETURNCHECK(24),

    /**
     * 收到单据
     */
    RECIVETICKET(25)
    ;

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
