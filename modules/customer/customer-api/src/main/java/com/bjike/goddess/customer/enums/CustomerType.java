package com.bjike.goddess.customer.enums;


import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 客户类别枚举
 * @Author: [tanghaixiang]
 * @Date: [2017-03-15 16:48]
 * @Description: [客户类别枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CustomerType {

    /**
     * VIP客户
     */
    @ExcelValue(name = "VIP客户")
    VIP(0),
    /**
     * 老客户
     */
    @ExcelValue(name = "老客户")
    OLD(1),
    /**
     * 合作伙伴
     */
    @ExcelValue(name = "合作伙伴")
    COOPERATOR(2),
    /**
     * 普通客户
     */
    @ExcelValue(name = "普通客户")
    ORDINARY(3),
    /**
     * 其他
     */
    @ExcelValue(name = "其他")
    OTHER(4)
    ;

    private int code;

    CustomerType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


    public static CustomerType getEnumConvert(int code){
        CustomerType customerType = CustomerType.VIP;
        if( code == CustomerType.VIP.getCode() ){
            customerType = CustomerType.VIP;
        }
        if( code == CustomerType.OLD.getCode() ){
            customerType = CustomerType.OLD;
        }
        if( code == CustomerType.COOPERATOR.getCode() ){
            customerType = CustomerType.COOPERATOR;
        }
        if( code == CustomerType.ORDINARY.getCode() ){
            customerType = CustomerType.ORDINARY;
        }
        return  customerType ;
    }

    public static String getStrConvert(int code){
        String name = "";
        if( code == CustomerType.VIP.getCode() ){
            name = "VIP客户";
        }
        if( code == CustomerType.OLD.getCode() ){
            name = "老客户";
        }
        if( code == CustomerType.COOPERATOR.getCode() ){
            name = "合作伙伴";
        }
        if( code == CustomerType.ORDINARY.getCode() ){
            name = "普通客户";
        }
        return  name ;
    }
}
