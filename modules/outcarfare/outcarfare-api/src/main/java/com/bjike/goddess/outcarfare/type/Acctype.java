package com.bjike.goddess.outcarfare.type;

/**
 * 科目类型
 *
 * @Author: [Jason]
 * @Date: [17-4-13 下午5:27]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Acctype {

    /**
     * 主营业务成本
     */
    MAIN(0),
    /**
     * 市场费
     */
    MARKET(1),
    /**
     * 培训费
     */
    TRAINING(2);

    private int code;

    Acctype(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
