package com.bjike.goddess.materialinstock.type;

/**
 * @Author: [sunfengtao]
 * @Date: [2017-05-09 17:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum UseState {

    /**
     * 领用
     */
    RECEIVE(0),
    /**
     *　外借
     */
    CHECKOUT(1),

    /**
     * 调动
     */
    TRANSFER(2),

    /**
     * 在库
     */
    INSTOCK(3);

    private int code;

    UseState(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
