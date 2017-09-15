package com.bjike.goddess.businessproject.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 合作方式
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-20 19:57]
 * @Description: [合作方式]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum BusinessCooperate {
    /**
     * 租赁合同
     */
    @ExcelValue(name = "租赁合同")
    RENTCONTRACT(0),
    /**
     * 承包的项目合同
     */
    @ExcelValue(name = "承包的项目合同")
    CHARCONTRACT(1),
    /**
     * 分包项目合同
     */
    @ExcelValue(name = "分包项目合同")
    DISTRIBUTECONTRACT(2),
    /**
     * 销售合同
     */
    @ExcelValue(name = "销售合同")
    SALECONTRACT(3),
    /**
     * 背靠背
     */
    @ExcelValue(name = "背靠背")
    BACKTOBACK(4);

    private int code;

    BusinessCooperate(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static BusinessCooperate getEnumConvert(int code) {
        BusinessCooperate businessCooperate = BusinessCooperate.CHARCONTRACT;
        if (code == BusinessCooperate.RENTCONTRACT.getCode()) {
            businessCooperate = BusinessCooperate.RENTCONTRACT;
        }
        if (code == BusinessCooperate.CHARCONTRACT.getCode()) {
            businessCooperate = BusinessCooperate.CHARCONTRACT;
        }
        if (code == BusinessCooperate.DISTRIBUTECONTRACT.getCode()) {
            businessCooperate = BusinessCooperate.DISTRIBUTECONTRACT;
        }
        if (code == BusinessCooperate.SALECONTRACT.getCode()) {
            businessCooperate = BusinessCooperate.SALECONTRACT;
        }if (code == BusinessCooperate.BACKTOBACK.getCode()) {
            businessCooperate = BusinessCooperate.BACKTOBACK;
        }
        return businessCooperate;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == BusinessCooperate.RENTCONTRACT.getCode()) {
            name = "租赁合同";
        }
        if (code == BusinessCooperate.CHARCONTRACT.getCode()) {
            name = "承包的项目合同";
        }
        if (code == BusinessCooperate.DISTRIBUTECONTRACT.getCode()) {
            name = "分包项目合同";
        }
        if (code == BusinessCooperate.SALECONTRACT.getCode()) {
            name = "销售合同";
        }if (code == BusinessCooperate.BACKTOBACK.getCode()) {
            name = "背靠背";
        }
        return name;
    }

    public static String getFirstLetter(BusinessCooperate businessCooperate) {
        String name = "";
        if (BusinessCooperate.RENTCONTRACT.equals(businessCooperate)) {
            name = "ZL";
        }
        if (BusinessCooperate.CHARCONTRACT.equals(businessCooperate)) {
            name = "CB";
        }
        if (BusinessCooperate.DISTRIBUTECONTRACT.equals(businessCooperate)) {
            name = "WB";
        }
        if (BusinessCooperate.SALECONTRACT.equals(businessCooperate)) {
            name = "XS";
        }if (BusinessCooperate.BACKTOBACK.equals(businessCooperate)) {
            name = "BKB";
        }
        return name;
    }

    public static String exportStrConvert(BusinessCooperate businessCooperate) {
        String name = "";
        if (businessCooperate.equals(BusinessCooperate.RENTCONTRACT)) {
            name = "租赁合同";
        }
        if (businessCooperate.equals(BusinessCooperate.CHARCONTRACT)) {
            name = "承包的项目合同";
        }
        if (businessCooperate.equals(BusinessCooperate.DISTRIBUTECONTRACT)) {
            name = "分包项目合同";
        }
        if (businessCooperate.equals(BusinessCooperate.SALECONTRACT)) {
            name = "销售合同";
        } if (businessCooperate.equals(BusinessCooperate.BACKTOBACK)) {
            name = "背靠背";
        }
        return name;
    }

}
