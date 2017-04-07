package com.bjike.goddess.market.enums;

/**
 * @Author: [xiazhili]
 * @Date: [17-3-22]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum MarketScaleType {
    /**
     * 项目级别-A级市场信息数量
     */
    PROJECTLEVELA(0),
    /**
     * 项目级别-B级市场信息数量
     */
    PROJECTLEVELB(1),
    /**
     * 项目级别-C级市场信息数量
     */
    PROJECTLEVELC(2),;

    private int code;

    MarketScaleType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


    public static MarketScaleType getEnumConvert(int code) {
        MarketScaleType marketScaleType = MarketScaleType.PROJECTLEVELA;
        if (code == MarketScaleType.PROJECTLEVELA.getCode()) {
            marketScaleType = MarketScaleType.PROJECTLEVELA;
        }
        if (code == MarketScaleType.PROJECTLEVELB.getCode()) {
            marketScaleType = MarketScaleType.PROJECTLEVELB;
        }
        if (code == MarketScaleType.PROJECTLEVELC.getCode()) {
            marketScaleType = MarketScaleType.PROJECTLEVELC;
        }
        return marketScaleType;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == MarketScaleType.PROJECTLEVELA.getCode()) {
            name = "项目级别-A级";
        }
        if (code == MarketScaleType.PROJECTLEVELB.getCode()) {
            name = "项目级别-B级";
        }
        if (code == MarketScaleType.PROJECTLEVELC.getCode()) {
            name = "项目级别-C级";
        }
        return name;
    }

}
