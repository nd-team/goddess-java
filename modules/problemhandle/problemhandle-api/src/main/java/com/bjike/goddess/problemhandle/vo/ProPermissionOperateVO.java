package com.bjike.goddess.problemhandle.vo;

/**
 * 问题权限配置操作对象表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-26 02:12 ]
 * @Description: [ 问题权限配置操作对象表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProPermissionOperateVO {

    /**
     * id
     */
    private String id;
    /**
     * 操作对象
     */
    private String operator;

    /**
     * 问题权限id
     */
    private String propermissionId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getPropermissionId() {
        return propermissionId;
    }

    public void setPropermissionId(String propermissionId) {
        this.propermissionId = propermissionId;
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