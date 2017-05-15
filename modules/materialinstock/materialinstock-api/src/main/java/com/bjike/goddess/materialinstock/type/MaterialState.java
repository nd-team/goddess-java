package com.bjike.goddess.materialinstock.type;

/**
 * 物资状态
 *
 * @Author: [sunfengtao]
 * @Date: [2017-04-21 17:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum MaterialState {

    /**
     * 完好
     */
    INTACT(0),
    /**
     * 人为损坏
     */
    MANUAL_DAMAGE(1),
    /**
     * 自然损坏
     */
    NATURAL_DAMAGE(2),

    /**
     * 维修中
     */
    REPAIRING(3),

    /**
     * 已报废
     */
    SCRAP(4);

    private int code;

    MaterialState(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
