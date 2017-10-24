package com.bjike.goddess.push.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 推送的用户装置信息
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-11 10:18 ]
 * @Description: [ 推送的用户装置信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "push_pushuserinfo")
public class PushUserInfo extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 移动端装置的唯一标识
     */
    @Column(name = "deviceToken", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '移动端装置的唯一标识'")
    private String deviceToken;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}