package com.bjike.goddess.businessprojectmanage.to;

import com.bjike.goddess.businessprojectmanage.enums.CusPermissionType;
import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 客户权限设置
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-12 05:56 ]
 * @Description: [ 客户权限设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CusPermissionTO extends BaseTO {

    /**
     * 辅助id
     */
    private String idFlag;

    /**
     * 描述
     */
    private String description;

    /**
     * 操作对象
     */
    private String operator;

    /**
     * 操作对象数组
     */
    private String[] operators;

    /**
     * 类型
     */
    private CusPermissionType type;


    public String getIdFlag() {
        return idFlag;
    }

    public void setIdFlag(String idFlag) {
        this.idFlag = idFlag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public CusPermissionType getType() {
        return type;
    }

    public void setType(CusPermissionType type) {
        this.type = type;
    }

    public String[] getOperators() {
        return operators;
    }

    public void setOperators(String[] operators) {
        this.operators = operators;
    }
}