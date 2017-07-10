package com.bjike.goddess.analysis.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 收入成本分析数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-31 03:37 ]
 * @Description: [ 收入成本分析数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class IncomeCostAnalysisDTO extends BaseDTO {
    /**
     * 月份
     */
    private Integer month;

    /**
     * 根据名字查询金额
     */
    private String[] condi;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String[] getCondi() {
        return condi;
    }

    public void setCondi(String[] condi) {
        this.condi = condi;
    }
}