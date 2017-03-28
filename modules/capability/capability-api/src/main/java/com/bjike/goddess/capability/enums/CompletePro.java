package com.bjike.goddess.capability.enums;

/**
 * 合作对象商务展示是否独立完成
 * @Author: [tanghaixiang]
 * @Date: [2017-03-23 16:42]
 * @Description: [合作对象商务展示是否独立完成]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CompletePro {

    /**
     * 独立完成
     */
    INDEPENDENT(0),
    /**
     * 合作完成
     */
    COOPER(1),
    /**
     * 阶段参与
     */
    STAGEPARTICIPATION(2)
    ;

    private int code;

    CompletePro(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static CompletePro getEnumConvert(int code) {
        CompletePro completePro = CompletePro.INDEPENDENT;
        if (code == CompletePro.INDEPENDENT.getCode()) {
            completePro = CompletePro.INDEPENDENT;
        }
        if (code == CompletePro.COOPER.getCode()) {
            completePro = CompletePro.COOPER;
        }
        if (code == CompletePro.STAGEPARTICIPATION.getCode()) {
            completePro = CompletePro.STAGEPARTICIPATION;
        }
        return completePro;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == CompletePro.INDEPENDENT.getCode()) {
            name = "独立完成";
        }
        if (code == CompletePro.COOPER.getCode()) {
            name = "合作完成";
        }
        if (code == CompletePro.STAGEPARTICIPATION.getCode()) {
            name = "阶段参与";
        }
        return name;
    }

}
