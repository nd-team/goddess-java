package com.bjike.goddess.reportmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 利润分析指标
 *
 * @Author: [chenjunhao]
 * @Date: [2017-06-30 09:42]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProfitAnalyzeIndicatorBO extends BaseBO {
    /**
     * 指标类型
     */
    private String indicator;
    /**
     * 指标值
     */
    private String indicatorValue;
    /**
     * 说明
     */
    private String state;

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getIndicatorValue() {
        return indicatorValue;
    }

    public void setIndicatorValue(String indicatorValue) {
        this.indicatorValue = indicatorValue;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
