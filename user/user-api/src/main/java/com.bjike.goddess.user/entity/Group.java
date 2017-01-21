package com.bjike.goddess.user.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @Author: [liguiqin]
 * @Date: [2016-12-28 09:09]
 * @Description: [用户组]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "user_group")
public class Group extends BaseEntity {

    @Column(unique = true,nullable = false)
    private String name; //组名
    private String description; //描述
    private Status status; //状态
    @Column(columnDefinition = "dateTime")
    private LocalDateTime createTime; //创建时间

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
}
