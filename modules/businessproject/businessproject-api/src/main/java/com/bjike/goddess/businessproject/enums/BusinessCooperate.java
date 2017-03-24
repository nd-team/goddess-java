package com.bjike.goddess.businessproject.enums;

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
    RENTCONTRACT(0),
    /**
     * 承包的项目合同
     */
    CHARCONTRACT(1),
    /**
     * 分包项目合同
     */
    DISTRIBUTECONTRACT(2),
    /**
     * 销售合同
     */
    SALECONTRACT(3);

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
        }
        return name;
    }
}
