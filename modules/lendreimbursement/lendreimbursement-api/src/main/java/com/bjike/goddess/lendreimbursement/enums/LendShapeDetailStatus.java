package com.bjike.goddess.lendreimbursement.enums;

/**
 * 借款图形显示状态
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-04-06 10:47]
 * @Description: [借款图形显示状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum LendShapeDetailStatus {

    /**
     * 申请借款
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
    HASPAY(3),
    /**
     * 还款记录
     */
    HASRETURN(4),
    /**
     * 帐务核对
     */
    CHECK(5);

    private int code;

    LendShapeDetailStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static String nameExplain (int code){
        String str = "";
        switch ( code ){
            case 0:
                str = "申请借款";
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
            case 4:
                str = "还款记录";
                break;
            case 5:
                str = "帐务核对";
                break;
        }
        return  str;
    }

}
