package com.bjike.goddess.accommodation.enums;

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
     * 项目经理通过
     */
    MANAGEPASS(0),
    /**
     * 项目经理未通过
     */
    MANAGENOPASS(1),
    /**
     * 总经办通过
     */
    GENERALPASS(2),
    /**
     * 总经办未通过
     */
    GENERALNOPASS(3),
    ;

    private int code;

    PassStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
