package com.bjike.goddess.rentutilitiespay.enums;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-20 11:30]
 * @Description: [缴费方]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum PayParty {
    /**
     * 公司缴纳
     */
    COMPANY(0),

    /**
     * 员工缴纳
     */
    EMPLOYEE(1),

    /**
     *　员工预缴
     */
    PREPAY(2);

    private int code;

    PayParty(int code){this.code = code;}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
