package com.bjike.goddess.market.enums;

/**
 * 信息来源
 *
 * @Author: [lijuntao]
 * @Date: [17-3-22]
 * @Description: [信息来源]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum SourceInfo {
    /**
     * 商务洽谈
     */
    BUSSNEGOTIATION(0),
    /**
     * 市场招待
     */
    MARKETFOR(1),
    /**
     * 介绍
     */
    INTRODUCE(2),
    /**
     * 招投标
     */
    TENDER(3);
    private int code;

    SourceInfo(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
