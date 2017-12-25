package com.bjike.goddess.employeecontract.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;

/**
 * 合同性质业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 01:58 ]
 * @Description: [ 合同性质业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContractNatureBO extends BaseBO {

    /**
     * 性质
     */
    private String nature;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private Status status;


    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}