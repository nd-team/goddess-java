package com.bjike.goddess.carinfo.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 邮箱发送
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-07-25 09:50 ]
 * @Description: [ 邮箱发送 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CarSendEmailTO extends BaseTO {


    /**
     * 岗位Id
     */
    @NotBlank(message = "岗位Id不能为空", groups = {ADD.class, EDIT.class})
    private String positionNameId;
    /**
     * 部门id
     */
    @NotBlank(message = "部门Id不能为空", groups = {ADD.class, EDIT.class})
    private String projectManageId;

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

    public String getPositionNameId() {
        return positionNameId;
    }

    public void setPositionNameId(String positionNameId) {
        this.positionNameId = positionNameId;
    }
    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProjectManageId() {
        return projectManageId;
    }

    public void setProjectManageId(String projectManageId) {
        this.projectManageId = projectManageId;
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