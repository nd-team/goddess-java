package com.bjike.goddess.employeecontract.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 合同性质
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 01:58 ]
 * @Description: [ 合同性质 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContractNatureTO extends BaseTO {

    /**
     * 性质
     */
    private String nature;

    /**
     * 描述
     */
    private String description;


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
}