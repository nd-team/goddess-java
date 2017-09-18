package com.bjike.goddess.regularization.type;

/**
 * 模板名称
 *
 * @Author: [lijuntao]
 * @Date: [2017-06-20 12:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ModuleName {
    /**
     * 转正面谈模板
     */
    INTERVIEWMODULE(0),
    /**
     * 转正面谈通知
     */
    INTERVIEWINFORM(1),
    /**
     * 转正通过告知函
     */
    INTERVIEWNOTIFI(2);



    private int code;

    ModuleName(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
