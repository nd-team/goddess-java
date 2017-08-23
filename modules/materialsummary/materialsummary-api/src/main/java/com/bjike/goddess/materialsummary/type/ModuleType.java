package com.bjike.goddess.materialsummary.type;

/**
 * 汇总模块
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-16 19:16]
 * @Description: [汇总模块]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ModuleType {

    /**
     * 物质购买
     */
    MATERIALBUY(0),
    /**
     * 物质入库
     */
    MATERIALSTOR(1),
    /**
     * 物质维修
     */
    MATERIALMAINTEN(2);

    private int code;

    ModuleType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}


