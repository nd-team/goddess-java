package com.bjike.goddess.regularization.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 操作汇总
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-09 02:00 ]
 * @Description: [ 操作汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "regularization_count")
public class Count extends BaseEntity {

    /**
     * 导航名
     */
    @Column(name = "navigation", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '导航名'")
    private String navigation;

    /**
     * 功能名
     */
    @Column(name = "function", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '功能名'")
    private String function;

    /**
     * 用户
     */
    @Column(name = "user", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '用户'")
    private String user;

    /**
     * 操作时间
     */
    @Column(name = "time", nullable = false, columnDefinition = "DATETIME   COMMENT '操作时间'")
    private LocalDateTime time;


    public String getNavigation() {
        return navigation;
    }

    public void setNavigation(String navigation) {
        this.navigation = navigation;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}