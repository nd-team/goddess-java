package com.bjike.goddess.businessprojectmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 客户权限配置业务传输对象list返回对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-12 05:43 ]
 * @Description: [ 客户权限配置业务传输对象list返回对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CusOperateBO extends BaseBO{

    /**
     * 操作对象
     */
    private String operator;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
