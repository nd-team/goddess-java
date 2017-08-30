package com.bjike.goddess.marketdevelopment.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 月计划选择对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-21 14:25]
 * @Description: [ 月计划选择对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MonthPlanChoiceVO {

    /**
     * id
     */
    private String id;

    /**
     * 显示字段
     */
    private String showValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShowValue() {
        return showValue;
    }

    public void setShowValue(String showValue) {
        this.showValue = showValue;
    }
}
