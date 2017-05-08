package com.bjike.goddess.businessproject.enums;

/**
 * 业务类型
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-20 19:56]
 * @Description: [业务类型]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum BusinessType {

    /**
     * 移动通信类
     */
    MOBILECOMMUNICATION(0),
    /**
     * 软件开发类
     */
    SOFTDEVELOP(1),
    /**
     * 智能系统集成类
     */
    INTELLIGENCESYSTEM(2),
    /**
     * 广告策划营销类
     */
    ADVERT(3);

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
        if (code == BusinessType.SOFTDEVELOP.getCode()) {
            businessType = BusinessType.SOFTDEVELOP;
        }
        if (code == BusinessType.INTELLIGENCESYSTEM.getCode()) {
            businessType = BusinessType.INTELLIGENCESYSTEM;
        }
        if (code == BusinessType.ADVERT.getCode()) {
            businessType = BusinessType.ADVERT;
        }
        return businessType;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == BusinessType.MOBILECOMMUNICATION.getCode()) {
            name = "移动通信类";
        }
        if (code == BusinessType.SOFTDEVELOP.getCode()) {
            name = "软件开发类";
        }
        if (code == BusinessType.INTELLIGENCESYSTEM.getCode()) {
            name = "智能系统集成类";
        }
        if (code == BusinessType.ADVERT.getCode()) {
            name = "广告策划营销类";
        }
        return name;
    }

    public static String getFirstLetter(BusinessType businessType) {
        String name = "";
        if (BusinessType.MOBILECOMMUNICATION.equals(businessType)) {
            name = "TX";
        }
        if (BusinessType.SOFTDEVELOP.equals(businessType)) {
            name = "SD";
        }
        if (BusinessType.INTELLIGENCESYSTEM.equals(businessType)) {
            name = "XJ";
        }
        if (BusinessType.ADVERT.equals(businessType)) {
            name = "AM";
        }
        return name;
    }
}
