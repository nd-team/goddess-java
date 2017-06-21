package com.bjike.goddess.progressmanage.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.progressmanage.entity.ProjectInfo;

/**
 * 进度表表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 04:46 ]
 * @Description: [ 进度表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProgressTableVO {

    /**
     * id
     */
    private String id;
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

    /**
     * 状态
     */
    private Status status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}