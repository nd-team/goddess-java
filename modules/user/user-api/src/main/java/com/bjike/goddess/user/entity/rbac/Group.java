package com.bjike.goddess.user.entity.rbac;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.*;

/**
 * 组
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-28 09:09]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "rbac_group")
public class Group extends BaseEntity {
    /**
     * 系统编号
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(12) COMMENT '系统编号' ")
    private String systemNO;

    /**
     * 组名
     */
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(255) COMMENT '组名' ")
    private String name;
    /**
     * 描述
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '组描述' ")
    private String description;
    /**
     * 状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '组状态'", nullable = false, insertable = false)
    private Status status;

    /**
     * 父角色
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", columnDefinition = "VARCHAR(36) COMMENT '上级组' ")
    private Group parent;


    /**
     * 是否有子节点
     */
    @Column(name = "is_hasChild", columnDefinition = "TINYINT(1) COMMENT '是否有子节点'", nullable = false)
    private Boolean hasChild;

    public String getSystemNO() {
        return systemNO;
    }

    public void setSystemNO(String systemNO) {
        this.systemNO = systemNO;
    }

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

    public Group getParent() {
        return parent;
    }

    public void setParent(Group parent) {
        this.parent = parent;
    }

    public Boolean getHasChild() {
        return hasChild;
    }

    public void setHasChild(Boolean hasChild) {
        this.hasChild = hasChild;
    }
}
