package com.bjike.goddess.regularization.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 时间条件设置
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:21 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TimeCriteriaSetTO extends BaseTO {

    /**
     * 参数名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "参数名称不能为空")
    private String parameterName;

    /**
     * 参数值
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "参数值不能为空")
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