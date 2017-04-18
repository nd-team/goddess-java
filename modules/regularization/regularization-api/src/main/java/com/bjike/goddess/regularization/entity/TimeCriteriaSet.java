package com.bjike.goddess.regularization.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 时间条件设置
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:21 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "regularization_timecriteriaset")
public class TimeCriteriaSet extends BaseEntity {

    /**
     * 参数名称
     */
    @Column(name = "parameterName", unique = true, nullable = false, columnDefinition = "VARCHAR(255) COMMENT '参数名称'")
    private String parameterName;

    /**
     * 参数值
     */
    @Column(name = "parameterValue", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '参数值'")
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