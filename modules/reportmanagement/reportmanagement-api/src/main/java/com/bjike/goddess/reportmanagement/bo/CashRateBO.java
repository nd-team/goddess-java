package com.bjike.goddess.reportmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 现金流量比率表业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 05:15 ]
 * @Description: [ 现金流量比率表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CashRateBO extends BaseBO {

    /**
     * 现金流量ID
     */
    private String projectId;

    /**
     * 比率
     */
    private Double rate;

    /**
     * 对应的公式
     */
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