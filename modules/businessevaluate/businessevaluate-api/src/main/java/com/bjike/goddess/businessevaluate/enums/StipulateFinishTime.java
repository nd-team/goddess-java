package com.bjike.goddess.businessevaluate.enums;

/**
 * 规定完成时间
 *
 * @Author: [Jason]
 * @Date: [17-3-29 下午2:29]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum StipulateFinishTime {

    /**
     * 4小时内
     */
    LESSFOUR(0),
    /**
     * 4至24小时
     */
    FORTOTWENTYFOR(1),
    /**
     * 24小时以上
     */
    MORETWENTYFOR(2);


    private int code;

    StipulateFinishTime(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
