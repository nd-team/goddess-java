package com.bjike.goddess.market.enums;

/**
 * 项目类型
 *
 * @Author: [lijuntao]
 * @Date: [17-3-22]
 * @Description: [项目类型]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum ProjectType {
    /**
     * 工程
     */
    ENGINEERING(0),
    /**
     * 租赁
     */
    LEASE(1);
    private int code;

    ProjectType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
