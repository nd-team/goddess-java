package com.bjike.goddess.contractcommunicat.enums;

/**汇总类型
 * Created by jiangzaixuan on 17-7-27.
 */
public enum CollectType {
    /**
     * 项目承包
     */
    CONTRACT(0),

    /**
     * 项目外包
     */
    OUTSOURCING(1);

    private int code;


    CollectType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
