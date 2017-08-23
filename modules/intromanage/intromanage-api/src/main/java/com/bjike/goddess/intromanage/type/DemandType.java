package com.bjike.goddess.intromanage.type;

/**
 * 需求类型
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-28 14:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum DemandType {

    /**
     * 招投标
     */
    BIDDING(0),
    /**
     * 入职培训
     */
    INDUCTION_TRAINING(1),
    /**
     * 公司官网
     */
    COMPANY_WEBSITE(2),

    /**
     * 招聘
     */
    RECRUIT(3);

    private int code;

    DemandType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
