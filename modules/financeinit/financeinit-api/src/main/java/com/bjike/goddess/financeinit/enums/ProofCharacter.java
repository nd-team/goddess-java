package com.bjike.goddess.financeinit.enums;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-29 16:50]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ProofCharacter {

    /**
     * 记账凭证
     */
    POCTAA(0),
    /**
     * 收款凭证
     */
    WTROP(1),
    /**
     * 付款凭证
     */
    POP(2),
    /**
     * 转账凭证
     */
    TV(3);


    private int code;

    ProofCharacter(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
