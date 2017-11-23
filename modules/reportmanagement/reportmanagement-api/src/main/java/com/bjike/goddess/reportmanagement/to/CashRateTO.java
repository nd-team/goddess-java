package com.bjike.goddess.reportmanagement.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 现金流量比率表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 05:15 ]
 * @Description: [ 现金流量比率表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CashRateTO extends BaseTO {

    /**
     * 现金流量ID
     */
    private String projectId;

    /**
     * 比率
     */
    @NotBlank(message = "比率不能为空", groups = {EDIT.class})
    private Double rate;

    /**
     * 对应的公式
     */
    @NotBlank(message = "对应的公式不能为空", groups = {EDIT.class})
    private String formula;


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}