package com.bjike.goddess.contractware.enums;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-31 14:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ContractCharacter {
    /**
     * 业务合同
     */
    BUSINESS(0),

    /**
     * 财务合同
     */
    FINANCIAL(1),

    /**
     * 外包合同
     */
    OUTSOURCING(2);

    private Integer code;

    ContractCharacter(int code){this.code = code;}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
