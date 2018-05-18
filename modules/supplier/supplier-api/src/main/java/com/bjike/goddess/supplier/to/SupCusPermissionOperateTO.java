package com.bjike.goddess.supplier.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 客户权限配置操作对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-25 02:12 ]
 * @Description: [ 客户权限配置操作对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SupCusPermissionOperateTO extends BaseTO {

    /**
     * 操作对象
     */
    private String operator;

    /**
     * 客户权限id
     */
    private String supCuspermissionId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getSupCuspermissionId() {
        return supCuspermissionId;
    }

    public void setSupCuspermissionId(String supCuspermissionId) {
        this.supCuspermissionId = supCuspermissionId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}