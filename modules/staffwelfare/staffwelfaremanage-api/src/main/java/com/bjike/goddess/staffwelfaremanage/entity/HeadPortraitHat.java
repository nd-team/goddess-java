package com.bjike.goddess.staffwelfaremanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 头像帽
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 04:00 ]
 * @Description: [ 头像帽 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffwelfaremanage_headportraithat")
public class HeadPortraitHat extends BaseEntity {

    /**
     * 填写人
     */
    @Column(name = "createUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '填写人'")
    private String createUser;

    /**
     * 头像帽名称
     */
    @Column(name = "name", unique = true, nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '头像帽名称'")
    private String name;

    /**
     * 头像帽图片URL名称
     */
    @Column(name = "url", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '头像帽图片URL名称'")
    private String url;

    /**
     * 更新人
     */
    @Column(name = "updateUser", columnDefinition = "VARCHAR(255)   COMMENT '更新人'")
    private String updateUser;


    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}