package com.bjike.goddess.employeecontract.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 合同类型
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 01:55 ]
 * @Description: [ 合同类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContractTypeTO extends BaseTO {

    /**
     * 类型
     */
    private String type;

    /**
     * 描述
     */
    private String description;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}