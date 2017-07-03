package com.bjike.goddess.lendreimbursement.enums;

/**
 * 报销状态
 * @Author: [tanghaixiang]
 * @Date: [2017-04-11 18:29]
 * @Description: [报销状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ReimStatus {

    /**
     * 未处理
     */
    NONE(0),
    /**
     * 负责人审核：审核通过
     */
    CHARGEPASS(1),
    /**
     * 负责人审核：审核不通过
     */
    CHARGENOTPASS(2),
    /**
     * 分析：审核通过
     */
    PASS(3),
    /**
     * 分析：审核不通过
     */
    NOTPASS(4),
    /**
     * 分析：申请冻结
     */
    CONGEL(5),
    /**
     * 负责人确认冻结
     */
    CHARGECONGEL(6)
    ;

    private int code;

    ReimStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static String exportStrConvert(ReimStatus reimStatus) {
        String name = "";
        if (reimStatus.equals(ReimStatus.NONE)) {
            name = "未处理";
        }
        if (reimStatus.equals(ReimStatus.CHARGEPASS)) {
            name = "负责人审核通过";
        }
        if (reimStatus.equals(ReimStatus.CHARGENOTPASS)) {
            name = "负责人审核不通过";
        }
        if (reimStatus.equals(ReimStatus.PASS)) {
            name = "分析审核通过";
        }
        if (reimStatus.equals(ReimStatus.NOTPASS)) {
            name = "分析审核不通过";
        }
        if (reimStatus.equals(ReimStatus.CONGEL)) {
            name = "分析申请冻结";
        }
        if (reimStatus.equals(ReimStatus.CHARGECONGEL)) {
            name = "负责人确认冻结";
        }
        return name;
    }
}
