package com.bjike.goddess.user.to.rbac;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;

import java.io.Serializable;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-11 13:59]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RoleTO extends BaseTO {
    /**
     * 角色名
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态
     */
    private Status status;
    /**
     * 创建时间
     */
    private String createTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
