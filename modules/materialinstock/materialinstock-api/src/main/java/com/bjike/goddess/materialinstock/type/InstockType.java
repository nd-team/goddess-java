package com.bjike.goddess.materialinstock.type;

/**
 * 入库类型
 *
 * @Author: [sunfengtao]
 * @Date: [2017-04-21 17:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum InstockType {

    /**
     * 购买入库
     */
    BUY(0),
    /**
     * 外借入库
     */
    LEND(1);

    private int code;

    InstockType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
