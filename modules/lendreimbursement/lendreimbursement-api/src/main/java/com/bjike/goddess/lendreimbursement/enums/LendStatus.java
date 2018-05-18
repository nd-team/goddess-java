package com.bjike.goddess.lendreimbursement.enums;

/**
 * 借款流程状态
 * @Author: [tanghaixiang]
 * @Date: [2017-04-06 10:47]
 * @Description: [借款流程状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum LendStatus {

    /**
     * 未处理
     */
    NONE(0),
    /**
     * 负责人审核通过
     */
    CHARGEPASS(1),
    /**
     * 负责人审核不通过
     */
    CHARGENOTPASS(2),
    /**
     * 财务运营审核通过
     */
    FINACEPASS(3),
    /**
     * 财务运营审核不通过
     */
    FINACENOTPASS(4),
    /**
     * 财务运营冻结
     */
    FINACECONGEL(5),
    /**
     * 负责人确认冻结
     */
    CHARGESURECONGEL(6),
    /**
     * 总经办审核通过
     */
    MANAGEPASS(7),
    /**
     * 总经办审核不通过
     */
    MANAGENOTPASS(8),
    /**
     * 申请单有误
     */
    LISTERROR(9)
    ;

    private int code;

    LendStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static String exportStrConvert(LendStatus lendStatus) {
        String name = "";
        if (lendStatus.equals(LendStatus.NONE)) {
            name = "未处理";
        }
        if (lendStatus.equals(LendStatus.CHARGEPASS)) {
            name = "负责人审核通过";
        }
        if (lendStatus.equals(LendStatus.CHARGENOTPASS)) {
            name = "负责人审核不通过";
        }
        if (lendStatus.equals(LendStatus.FINACEPASS)) {
            name = "财务运营审核通过";
        }
        if (lendStatus.equals(LendStatus.FINACENOTPASS)) {
            name = "财务运营审核不通过";
        }
        if (lendStatus.equals(LendStatus.FINACECONGEL)) {
            name = "财务运营冻结";
        }
        if (lendStatus.equals(LendStatus.CHARGESURECONGEL)) {
            name = "负责人确认冻结";
        }
        if (lendStatus.equals(LendStatus.MANAGEPASS)) {
            name = "总经办审核通过";
        }
        if (lendStatus.equals(LendStatus.MANAGENOTPASS)) {
            name = "总经办审核不通过";
        }
        if (lendStatus.equals(LendStatus.LISTERROR)) {
            name = "申请单有误";
        }
        return name;
    }


}
