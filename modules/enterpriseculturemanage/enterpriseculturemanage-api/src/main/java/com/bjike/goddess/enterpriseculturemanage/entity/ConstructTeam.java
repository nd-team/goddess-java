package com.bjike.goddess.enterpriseculturemanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 建设小组
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 03:33 ]
 * @Description: [ 建设小组 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "enterpriseculture_constructteam")
public class ConstructTeam extends BaseEntity {


    /**
     * 员工Id
     */
    @Column(name = "userId", unique = true, nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '员工Id'")
    private String userId;

    /**
     * 员工姓名
     */
    @Column(name = "userName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工姓名'")
    private String userName;

    /**
     * 员工编号
     */
    @Column(name = "userNumber",unique = true,nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String userNumber;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

}