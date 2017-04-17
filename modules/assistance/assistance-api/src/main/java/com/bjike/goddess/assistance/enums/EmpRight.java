package com.bjike.goddess.assistance.enums;

/**
 * 权限设置类型
 * @Author: [tanghaixiang]
 * @Date: [2017-04-14 14:33]
 * @Description: [ 权限设置 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum EmpRight {

    /**
     * 总经办
     */
    MANAGE(0),
    /**
     * 综合资源模块
     */
    RESOURCE(1),
    /**
     * 规划模块
     */
    PLAN(2),
    /**
     * 财务运营模块
     */
    FINANCE(3),
    /**
     * 商务运营模块
     */
    BUSINESS(4),
    /**
     * 福利模块
     */
    WAREFARE(5),
    ;

    private int code;

    EmpRight(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
