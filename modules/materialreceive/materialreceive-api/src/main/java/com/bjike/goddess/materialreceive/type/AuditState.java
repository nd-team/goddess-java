package com.bjike.goddess.materialreceive.type;

/**
 * 审核状态
 *
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
     * 通过
     */
    AUDITED(1),

    /**
     * 拒绝
     */
    REJECT(2);

    private int code;

    AuditState(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
