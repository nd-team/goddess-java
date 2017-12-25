package com.bjike.goddess.coststandard.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 费用标准对比
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-24 11:15 ]
 * @Description: [ 费用标准对比 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostStandardContrastTO extends BaseTO {

    /**
     * 费用标准id
     */
    @NotBlank(message = "费用标准id不能为空", groups = {ADD.class, EDIT.class})
    private String standardId;

    /**
     * 资金准备
     */
    @NotNull(message = "资金准备不能为空", groups = {ADD.class, EDIT.class})
    private Double plan;

    /**
     * 实际
     */
    @NotNull(message = "实际不能为空", groups = {ADD.class, EDIT.class})
    private Double actual;


    public String getStandardId() {
        return standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }

    public Double getPlan() {
        return plan;
    }

    public void setPlan(Double plan) {
        this.plan = plan;
    }

    public Double getActual() {
        return actual;
    }

    public void setActual(Double actual) {
        this.actual = actual;
    }
}