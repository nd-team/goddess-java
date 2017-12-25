package com.bjike.goddess.projectcost.to;

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
public class CusPermissionOperateTO extends BaseTO {

    /**
     * 操作对象
     */
    private String operator;

    /**
     * 客户权限id
     */
    private String cuspermissionId;

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

    public String getCuspermissionId() {
        return cuspermissionId;
    }

    public void setCuspermissionId(String cuspermissionId) {
        this.cuspermissionId = cuspermissionId;
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