package com.bjike.goddess.lendreimbursement.enums;

/**
 * 报销图形显示状态
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-04-06 10:47]
 * @Description: [报销图形显示状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ReimburseShapeDetailStatus {

    /**
     * 申报报销记录
     */
    ALL(0),
    /**
     * 待审核
     */
    WAITAUIT(1),
    /**
     * 待支付
     */
    WAITPAY(2),
    /**
     * 已支付
     */
    HASPAY(3);

    private int code;

    ReimburseShapeDetailStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static String nameExplain (int code){
        String str = "";
        switch ( code ){
            case 0:
                str = "申报报销记录";
                break;
            case 1:
                str = "待审核";
                break;
            case 2:
                str = "待支付";
                break;
            case 3:
                str = "已支付";
                break;
        }
        return  str;
    }

}
