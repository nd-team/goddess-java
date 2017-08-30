package com.bjike.goddess.feedback.enums;

/**
 * Created by ike on 17-8-3.
 */
public enum AdoptStatus {
    /**
     * 采纳
     */
    ADOPT(0),
    /**
     * 未被采纳
     */
    NOADOPT(1),;
    private int code;

    AdoptStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
