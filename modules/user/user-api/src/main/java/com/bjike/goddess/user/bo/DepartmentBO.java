package com.bjike.goddess.user.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 部门业务传输对象
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-18 15:25]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DepartmentBO extends BaseBO {
    /**
     * 部门名
     */
    private String name;
    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 是否有子节点
     */
    private String hasChild;


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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getHasChild() {
        return hasChild;
    }

    public void setHasChild(String hasChild) {
        this.hasChild = hasChild;
    }
}
