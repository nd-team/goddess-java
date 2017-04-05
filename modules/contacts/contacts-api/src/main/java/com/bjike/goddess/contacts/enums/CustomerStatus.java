package com.bjike.goddess.contacts.enums;

/**
 * 客户状态枚举
 * @Author: [tanghaixiang]
 * @Date: [2017-03-15 16:52]
 * @Description: [客户状态枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CustomerStatus {
    /**
     * 已完成项目客户
     */
    COMPLETEPROJECT(0),
    /**
     * 现项目客户
     */
    PROJECTING(1),
    /**
     * 潜在客户
     */
    POTENTIAL(2)
    ;

    private int code;

    CustomerStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static CustomerStatus getEnumConvert(int code){
        CustomerStatus customerStatus = CustomerStatus.COMPLETEPROJECT;
        if( code == CustomerStatus.COMPLETEPROJECT.getCode() ){
            customerStatus = CustomerStatus.COMPLETEPROJECT;
        }
        if( code == CustomerStatus.PROJECTING.getCode() ){
            customerStatus = CustomerStatus.PROJECTING;
        }
        if( code == CustomerStatus.POTENTIAL.getCode() ){
            customerStatus = CustomerStatus.POTENTIAL;
        }
        return  customerStatus ;
    }

    public static String getStrConvert(int code){
        String name = "";
        if( code == CustomerStatus.COMPLETEPROJECT.getCode() ){
            name = "已完成项目客户";
        }
        if( code == CustomerStatus.PROJECTING.getCode() ){
            name = "现项目客户";
        }
        if( code == CustomerStatus.POTENTIAL.getCode() ){
            name = "潜在客户";
        }
        return  name ;
    }
}

