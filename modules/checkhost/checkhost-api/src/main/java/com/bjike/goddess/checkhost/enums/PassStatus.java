package com.bjike.goddess.checkhost.enums;

/**
 * 是否通过
 * @Author: [xiazhili]
 * @Date: [17-4-17]
 * @Description: [是否通过]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum PassStatus {
    /**
     * 福利模块负责人通过
     */
    MANAGEPASS(0),
    /**
     * 福利模块负责人未通过
     */
    MANAGENOPASS(1),
    ;

    private int code;

    PassStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
