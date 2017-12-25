package com.bjike.goddess.financeinit.enums;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-29 16:50]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum UseComm {

    /**
     * 提现
     */
    CASHWITHDRAWAL(0),
    /**
     * 存现
     */
    EXISENTIAL(1),
    /**
     * 计提本月工资
     */
    GTSTM(2),
    /**
     * 本月服务收入
     */
    STIM(3),
    /**
     * 收到货款
     */
    RECEIVEPAYMENT(4),
    /**
     * 支付货款
     */
    PAYMENT(5),
    /**
     * 支付本月社保
     */
    PSSTM(6),
    /**
     * 支付办公费
     */
    POE(7),
    /**
     * 报销差旅费
     */
    ROTE(8),
    /**
     * 支付交通费
     */
    PAYFORT(9),
    /**
     * 支付业务招待费
     */
    PBE(10),
    /**
     * 支付本月税金
     */
    PTMST(11),
    /**
     * 计提本月税金及附加
     */
    MTAAT(12);


    private int code;

    UseComm(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
