package com.bjike.goddess.materialreceive.type;

/**
 * @Author: [sunfengtao]
 * @Date: [2017-04-25 08:46]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AuditState {

    /**
     * 未审核
     */
    UNAUDITED(0),
    /**
     * 已审核
     */
    AUDITED(1);

    private int code;

    AuditState(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
