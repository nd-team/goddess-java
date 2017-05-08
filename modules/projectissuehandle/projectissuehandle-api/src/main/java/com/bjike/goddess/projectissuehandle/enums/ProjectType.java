package com.bjike.goddess.projectissuehandle.enums;

/**
 * 工程类型枚举
 *
 * @Author: [xiazhili]
 * @Date: [17-4-25]
 * @Description: [工程类型枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum ProjectType {
    /**
     * 承包
     */
    CONTRACT(0),
    /**
     * 外包
     */
    OUTSOURCING(1),
    /**
     * 租赁
     */
    RENT(2),
    ;

    private int code;

    ProjectType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
