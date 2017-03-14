package com.bjike.goddess.user.entity;


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

@Entity
@Table(name = "user_position")
public class Position extends BaseEntity {
    /**
     * 职位名
     */
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(255) COMMENT '职位名' ")
    private String name;
    /**
     * 描述
     */
    @Column(columnDefinition = " VARCHAR(255) COMMENT '职位描述' ")

    private String description;
    /**
     * 状态
     */
    @Column(columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '职位状态'", nullable = false, insertable = false)

    private Status status;

    /**
     * 父节点
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", columnDefinition = "VARCHAR(36) COMMENT '父职位' ")
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


    public Position getParent() {
        return parent;
    }

    public void setParent(Position parent) {
        this.parent = parent;
    }
}
