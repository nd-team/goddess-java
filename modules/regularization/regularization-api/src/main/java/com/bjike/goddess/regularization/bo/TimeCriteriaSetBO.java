package com.bjike.goddess.regularization.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 时间条件设置业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:21 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TimeCriteriaSetBO extends BaseBO {

    /**
     * 参数名称
     */
    private String parameterName;

    /**
     * 参数值
     */
    private String parameterValue;

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }
}