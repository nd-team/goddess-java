package com.bjike.goddess.bidding.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 业务类型枚举
 *
 * @Author: [xiazhili]
 * @Date: [17-3-20]
 * @Description: [业务类型枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum BusinessType {
    /**
     * 移动通信
     */
    @ExcelValue(name = "移动通信")
    MOBILECOMMUNICATION(0),
    /**
     * 软件开发
     */
    @ExcelValue(name = "软件开发")
    SOFTWAREDEVELOPMENT(1),
    /**
     * 智能系统集成
     */
    @ExcelValue(name = "智能系统集成")
    INTELLIGENTSYSTEMINTEGRATION(2),
    /**
     * 策划与营销方案
     */
    @ExcelValue(name = "策划与营销方案")
    PLANNINGMARKETINGSOLUTIONS(3);

    private int code;

    BusinessType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


    public static BusinessType getEnumConvert(int code) {
        BusinessType businessType = BusinessType.MOBILECOMMUNICATION;
        if (code == BusinessType.MOBILECOMMUNICATION.getCode()) {
            businessType = BusinessType.MOBILECOMMUNICATION;
        }
        if (code == BusinessType.SOFTWAREDEVELOPMENT.getCode()) {
            businessType = BusinessType.SOFTWAREDEVELOPMENT;
        }
        if (code == BusinessType.INTELLIGENTSYSTEMINTEGRATION.getCode()) {
            businessType = BusinessType.INTELLIGENTSYSTEMINTEGRATION;
        }
        if (code == BusinessType.PLANNINGMARKETINGSOLUTIONS.getCode()) {
            businessType = BusinessType.PLANNINGMARKETINGSOLUTIONS;
        }
        return businessType;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == BusinessType.MOBILECOMMUNICATION.getCode()) {
            name = "移动通信";
        }
        if (code == BusinessType.SOFTWAREDEVELOPMENT.getCode()) {
            name = "软件开发";
        }
        if (code == BusinessType.INTELLIGENTSYSTEMINTEGRATION.getCode()) {
            name = "智能系统集成";
        }
        if (code == BusinessType.PLANNINGMARKETINGSOLUTIONS.getCode()) {
            name = "策划与营销方案";
        }
        return name;
    }

    public static String exportStrConvert(BusinessType businessType) {
        String name = "";
        if (businessType.equals(BusinessType.MOBILECOMMUNICATION)) {
            name = "移动通信";
        }
        if (businessType.equals(BusinessType.SOFTWAREDEVELOPMENT)) {
            name = "软件开发";
        }
        if (businessType.equals(BusinessType.INTELLIGENTSYSTEMINTEGRATION)) {
            name = "智能系统集成";
        }
        if (businessType.equals(BusinessType.PLANNINGMARKETINGSOLUTIONS)) {
            name = "策划与营销方案";
        }
        return name;
    }
}
