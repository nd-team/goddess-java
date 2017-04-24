package com.bjike.goddess.function.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 用户功能
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-22 01:56 ]
 * @Description: [ 用户功能 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class UserFunctionTO extends BaseTO {

    /**
     * 功能
     */
    @NotBlank(message = "功能ID不能为空", groups = {ADD.class, EDIT.class})
    private String functionId;


    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

}