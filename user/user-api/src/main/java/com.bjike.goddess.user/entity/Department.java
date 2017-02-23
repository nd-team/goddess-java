package com.bjike.goddess.user.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 部门
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-24 10:16]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "user_department")
public class Department extends BaseEntity {
    /**
     * 部门名
     */
    @Column(unique = true, nullable = false)
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建时间
     */
    @Column(columnDefinition = "dateTime")
    private LocalDateTime createTime;

    /**
     * 上级部门
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Department parent;

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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Department getParent() {
        return parent;
    }

    public void setParent(Department parent) {
        this.parent = parent;
    }
}
