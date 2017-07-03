package com.bjike.goddess.user.vo.rbac;


/**
 * 权限值实体
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-02 17:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PermissionVO {
    /**
     * id
     */
    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
