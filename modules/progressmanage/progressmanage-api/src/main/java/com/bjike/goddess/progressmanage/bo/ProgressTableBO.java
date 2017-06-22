package com.bjike.goddess.progressmanage.bo;

import com.alibaba.fastjson.annotation.JSONField;
import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.progressmanage.entity.ProjectInfo;

/**
 * 进度表业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 04:46 ]
 * @Description: [ 进度表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProgressTableBO extends BaseBO {

    /**
     * 表名
     */
    private String tabName;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改人
     */
    private String updateUser;

    //所属项目
    private ProjectInfoBO projectInfoBO;

    /**
     * 状态
     */
    private Status status;


    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public ProjectInfoBO getProjectInfoBO() {
        return projectInfoBO;
    }

    public void setProjectInfoBO(ProjectInfoBO projectInfoBO) {
        this.projectInfoBO = projectInfoBO;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}