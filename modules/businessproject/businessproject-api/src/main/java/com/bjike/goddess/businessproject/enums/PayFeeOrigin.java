package com.bjike.goddess.businessproject.enums;

/**
 * 结算费用来源
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-20 20:39]
 * @Description: [结算费用来源]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum PayFeeOrigin {

    /**
     * 预付
     */
    PREPAY(0),
    /**
     * 背靠背
     */
    BACKTOBACK(1),
    /**
     * 垫付
     */
    ADVANCE(2);

    private int code;

    PayFeeOrigin(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static PayFeeOrigin getEnumConvert(int code) {
        PayFeeOrigin payFeeOrigin = PayFeeOrigin.PREPAY;
        if (code == PayFeeOrigin.PREPAY.getCode()) {
            payFeeOrigin = PayFeeOrigin.PREPAY;
        }
        if (code == PayFeeOrigin.BACKTOBACK.getCode()) {
            payFeeOrigin = PayFeeOrigin.BACKTOBACK;
        }
        if (code == PayFeeOrigin.ADVANCE.getCode()) {
            payFeeOrigin = PayFeeOrigin.ADVANCE;
        }
        return payFeeOrigin;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == PayFeeOrigin.PREPAY.getCode()) {
            name = "预付";
        }
        if (code == PayFeeOrigin.BACKTOBACK.getCode()) {
            name = "背靠背";
        }
        if (code == PayFeeOrigin.ADVANCE.getCode()) {
            name = "垫付";
        }

        return name;
    }
}
