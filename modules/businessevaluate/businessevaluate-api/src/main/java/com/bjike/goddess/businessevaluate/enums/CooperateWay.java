package com.bjike.goddess.businessevaluate.enums;

/**
 * 合作方式
 *
 * @Author: [Jason]
 * @Date: [17-3-27 下午3:43]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CooperateWay {

    /**
     * 长期合作
     */
    LONGTERM(0),
    /**
     * 事项合作
     */
    ITEM(1),
    /**
     * 中介合作
     */
    AGENCY(2),
    /**
     * 其他
     */
    ANOTHER(3);

    private int code;

    CooperateWay(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
