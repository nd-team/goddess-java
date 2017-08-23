package com.bjike.goddess.user.bo;


import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.*;

/**
 * 职位
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-28 11:06]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

public class PositionBO extends BaseBO {
    /**
     * 职位名
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
     * 父节点
     */
    private String parentName;
    /**
     * 父节点ID
     */
    private String parentId;

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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
