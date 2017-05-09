package com.bjike.goddess.market.enums;

/**
 * 项目性质
 *
 * @Author: [xiazhili]
 * @Date: [17-3-22]
 * @Description: [项目性质]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum MarketProjectNature {
    /**
     * 新项目市场信息数量
     */
    NEWPROJECT(0),
    /**
     * 已有项目or进行中项目市场信息数量
     */
    OLDPROJECT(1),;
    private int code;

    MarketProjectNature(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
