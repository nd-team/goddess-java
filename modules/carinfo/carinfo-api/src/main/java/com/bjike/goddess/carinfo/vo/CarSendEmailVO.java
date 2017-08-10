package com.bjike.goddess.carinfo.vo;

/**
 * 邮箱发送
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-07-25 09:50 ]
 * @Description: [ 邮箱发送 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CarSendEmailVO {

    /**
     * id
     */
    private String id;

    /**
     * 部门Id
     */
    private String projectManageId;
    /**
     * 岗位Id
     */
    private String positionNameId;

    /**
     * 部门名称
     */
    private String projectManage;

    /**
     * 岗位名称
     */
    private String positionName;

    /**
     * 备注
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectManageId() {
        return projectManageId;
    }

    public void setProjectManageId(String projectManagerId) {
        this.projectManageId = projectManageId;
    }

    public String getPositionNameId() {
        return positionNameId;
    }

    public void setPositionNameId(String positionNameId) {
        this.positionNameId = positionNameId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getProjectManage() {
        return projectManage;
    }

    public void setProjectManage(String projectManage) {
        this.projectManage = projectManage;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}