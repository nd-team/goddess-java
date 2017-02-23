package com.bjike.goddess.user.entity;


import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 职位
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-28 11:06]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Entity
@Table(name = "user_position")
public class Position extends BaseEntity {
    /**
     * 职位名
     */
    @Column(unique = true, nullable = false)
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
    @Column(columnDefinition = "dateTime")
    private LocalDateTime createTime;
    /**
     * 父节点
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Position parent;


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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Position getParent() {
        return parent;
    }

    public void setParent(Position parent) {
        this.parent = parent;
    }
}
