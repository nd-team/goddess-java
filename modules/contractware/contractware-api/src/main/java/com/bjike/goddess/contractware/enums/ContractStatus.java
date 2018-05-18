package com.bjike.goddess.contractware.enums;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-09 15:47]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ContractStatus {
    /**
     * 正常
     */
    NORMAL(0),

    /**
     * 作废
     */
    CANCELLATION(1);

    private int code;

    ContractStatus(int code){this.code = code;}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
