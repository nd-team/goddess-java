package com.bjike.goddess.businessproject.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 合同属性
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-20 19:57]
 * @Description: [合同属性]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ContractProperty {

    /**
     * 框架合同
     */
    @ExcelValue(name = "框架合同")
    FRAMECONTRACT(0),
    /**
     * 单次合同
     */
    @ExcelValue(name = "单次合同")
    SINGLECONTRACT(1);

    private int code;

    ContractProperty(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static ContractProperty getEnumConvert(int code) {
        ContractProperty contractProperty = ContractProperty.SINGLECONTRACT;
        if (code == ContractProperty.FRAMECONTRACT.getCode()) {
            contractProperty = ContractProperty.FRAMECONTRACT;
        }
        if (code == ContractProperty.SINGLECONTRACT.getCode()) {
            contractProperty = ContractProperty.SINGLECONTRACT;
        }
        return contractProperty;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == ContractProperty.FRAMECONTRACT.getCode()) {
            name = "框架合同";
        }
        if (code == ContractProperty.SINGLECONTRACT.getCode()) {
            name = "单次合同";
        }
        return name;
    }
    public static String exportStrConvert(ContractProperty contractProperty) {
        String name = "";
        if (contractProperty.equals(ContractProperty.FRAMECONTRACT)) {
            name = "框架合同";
        }
        if (contractProperty.equals( ContractProperty.SINGLECONTRACT)) {
            name = "单次合同";
        }
        return name;
    }
}


