package com.bjike.goddess.user.bo.rbac;


import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 资源认证业务传输
 *
 * @Author: [liguiqin]
 * @Date: [2017-02-28 10:12]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PermissionBO extends BaseBO {
    /**
     * 认证名
     */
    private String name;
    /**
     * 认证资源
     */
    private String resource;

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
    private Boolean hasChild;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
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

    public Boolean getHasChild() {
        return hasChild;
    }

    public void setHasChild(Boolean hasChild) {
        this.hasChild = hasChild;
    }
}
