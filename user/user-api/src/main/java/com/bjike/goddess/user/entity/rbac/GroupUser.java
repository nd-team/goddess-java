package com.bjike.goddess.user.entity.rbac;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.user.entity.User;

import javax.persistence.*;

/**
 * 组用户
 *
 * @Author: [liguiqin]
 * @Date: [2017-02-28 14:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "rbac_group_user")
public class GroupUser extends BaseEntity {
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '组id' ")
    private Group group;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '用户id' ")
    private User user;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
