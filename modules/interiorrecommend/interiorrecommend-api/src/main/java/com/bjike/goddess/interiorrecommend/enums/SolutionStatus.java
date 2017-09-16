package com.bjike.goddess.interiorrecommend.enums;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-12 10:33]
 * @Description: [方案状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum SolutionStatus {
    /**
     * 进行中
     */
    UNDERWAY(0),

    /**
     * 已结束
     */
    ENDED(1);


    private int code;

    SolutionStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
