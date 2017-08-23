package com.bjike.goddess.intromanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 个人简介显示用户名称集合
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-06 04:12 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "intromanage_individualdisplayuser")
public class IndividualDisplayUser extends BaseEntity {

    /**
     * 用户姓名集合
     */
    @Column(name = "usernames", nullable = false, columnDefinition = "TEXT COMMENT '用户姓名集合'")
    private String usernames;

    /**
     * 个人简介显示字段id
     */
    @Column(name = "displayId", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '个人简介显示字段id'")
    private String displayId;


    public String getUsernames() {
        return usernames;
    }

    public void setUsernames(String usernames) {
        this.usernames = usernames;
    }

    public String getDisplayId() {
        return displayId;
    }

    public void setDisplayId(String displayId) {
        this.displayId = displayId;
    }
}