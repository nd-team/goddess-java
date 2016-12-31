package com.bjike.goddess.user.common.entity;

import com.bjike.goddess.dbs.jpa.entity.BaseEntity;
import com.bjike.goddess.dbs.jpa.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author: [liguiqin]
 * @Date: [2016-12-28 11:06]
 * @Description: [职位]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */

@Entity
@Table(name = "user_position")
public class Position extends BaseEntity {
    @Column(unique = true,nullable = false)
    private String name; //职位名
    private String description; //描述
    private Status status; //状态
    @Column(columnDefinition = "dateTime")
    private LocalDateTime createTime; //创建时间

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
