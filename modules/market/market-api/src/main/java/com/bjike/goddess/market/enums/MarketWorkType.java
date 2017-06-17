package com.bjike.goddess.market.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 行业类别
 *
 * @Author: [xiazhili]
 * @Date: [17-3-22]
 * @Description: [行业类别]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum MarketWorkType {
    /**
     * 移动通信行业市场信息数量
     */
    @ExcelValue(name = "移动通信行业市场信息数量")
    MOBILECOMMUNICATION(0),
    /**
     * 软件开发行业市场信息数量
     */
    @ExcelValue(name = "软件开发行业市场信息数量")
    SOFTWAREDEVELOPMENT(1),
    /**
     * 智能系统集成行业市场信息数量
     */
    @ExcelValue(name = "智能系统集成行业市场信息数量")
    INTELLIGENTSYSTEMINTEGRATION(2),
    /**
     * 策划与营销方案行业市场信息数量
     */
    @ExcelValue(name = "策划与营销方案行业市场信息数量")
    PLANNINGMARKETINGSOLUTIONS(3),;

    private int code;

    MarketWorkType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


    public static MarketWorkType getEnumConvert(int code) {
        MarketWorkType marketWorkType = MarketWorkType.MOBILECOMMUNICATION;
        if (code == MarketWorkType.MOBILECOMMUNICATION.getCode()) {
            marketWorkType = MarketWorkType.MOBILECOMMUNICATION;
        }
        if (code == MarketWorkType.SOFTWAREDEVELOPMENT.getCode()) {
            marketWorkType = MarketWorkType.SOFTWAREDEVELOPMENT;
        }
        if (code == MarketWorkType.INTELLIGENTSYSTEMINTEGRATION.getCode()) {
            marketWorkType = MarketWorkType.INTELLIGENTSYSTEMINTEGRATION;
        }
        if (code == MarketWorkType.PLANNINGMARKETINGSOLUTIONS.getCode()) {
            marketWorkType = MarketWorkType.PLANNINGMARKETINGSOLUTIONS;
        }
        return marketWorkType;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == MarketWorkType.MOBILECOMMUNICATION.getCode()) {
            name = "移动通信";
        }
        if (code == MarketWorkType.SOFTWAREDEVELOPMENT.getCode()) {
            name = "软件开发";
        }
        if (code == MarketWorkType.INTELLIGENTSYSTEMINTEGRATION.getCode()) {
            name = "智能系统集成";
        }
        if (code == MarketWorkType.PLANNINGMARKETINGSOLUTIONS.getCode()) {
            name = "策划与营销方案";
        }
        return name;
    }
    public static String exportStrConvert(MarketWorkType marketWorkType) {
        String name = "";
        if (marketWorkType.equals(MarketWorkType.MOBILECOMMUNICATION)) {
            name = "移动通信";
        }
        if (marketWorkType.equals(MarketWorkType.SOFTWAREDEVELOPMENT)) {
            name = "软件开发";
        }
        if (marketWorkType.equals(MarketWorkType.INTELLIGENTSYSTEMINTEGRATION)) {
            name = "智能系统集成";
        }
        if (marketWorkType.equals(MarketWorkType.PLANNINGMARKETINGSOLUTIONS)) {
            name = "策划与营销方案";
        }
        return name;
    }

}
