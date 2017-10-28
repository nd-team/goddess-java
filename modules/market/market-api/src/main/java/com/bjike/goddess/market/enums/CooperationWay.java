package com.bjike.goddess.market.enums;

/**
 * 合作方式
 *
 * @Author: [lijuntao]
 * @Date: [17-3-22]
 * @Description: [合作方式]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum CooperationWay {
    /**
     * 人员租赁
     */
    PERSONNELLEASE(0),
    /**
     * 分包合作
     */
    SUBCONTRACTCOOPERATION(1);
    private int code;

    CooperationWay(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
