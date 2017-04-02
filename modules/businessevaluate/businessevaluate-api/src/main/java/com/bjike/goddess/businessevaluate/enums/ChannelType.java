package com.bjike.goddess.businessevaluate.enums;

/**
 * 渠道
 *
 * @Author: [Jason]
 * @Date: [17-3-29 上午11:34]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ChannelType {
    /**
     * 邮箱
     */
    EMAIL(0),
    /**
     * 书面
     */
    WRITTEN(1),
    /**
     * 口头
     */
    VERBAL(2),
    /**
     * 系统
     */
    SYSTEM(3);

    private int code;

    ChannelType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
