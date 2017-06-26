package com.bjike.goddess.voucher.enums;

/**
 * 审核状态
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-04-17 18:01]
 * @Description: [审核状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ExportStatus {

    /**
     * 未审核状态
     */
    NONE(0),
    /**
     * 已审核状态
     */
    AUDIT(1),
    /**
     * 已过账状态
     */
    TRANS(2),
    /**
     * 已结帐状态
     */
    CHECK(3),
    /**
     * 记账凭证记录状态
     */
    RECORD(4);

    private int code;

    ExportStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
