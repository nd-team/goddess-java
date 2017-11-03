package com.bjike.goddess.customer.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 客户接触阶段权重设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:46 ]
 * @Description: [ 客户接触阶段权重设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerContactWeightSetTO extends BaseTO {

    /**
     * 客户接触阶段类型
     */
    @NotBlank(message = "客户接触阶段类型不能为空",groups = {ADD.class,EDIT.class})
    private String customerContactType;

    /**
     * 客户接触阶段类型权重
     */
    @NotNull(message = "客户接触阶段类型权重不能为空",groups = {ADD.class,EDIT.class})
    private Double customerContactTypeWeight;


    public String getCustomerContactType() {
        return customerContactType;
    }

    public void setCustomerContactType(String customerContactType) {
        this.customerContactType = customerContactType;
    }

    public Double getCustomerContactTypeWeight() {
        return customerContactTypeWeight;
    }

    public void setCustomerContactTypeWeight(Double customerContactTypeWeight) {
        this.customerContactTypeWeight = customerContactTypeWeight;
    }
}