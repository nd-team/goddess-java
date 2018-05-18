package com.bjike.goddess.rentcar.enums;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-08 14:26]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AgreementStatus {
    /**
     * 在租用中
     */
    INLEASE(0),

    /**
     * 停止租用
     */
    STOPRENTING(1),

    /**
     * 待租用
     */
    WAITRENTING(2),

    /**
     * 待解除
     */
    TOREMOVE(3);

    private int code;

    AgreementStatus(int code){this.code = code;}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
