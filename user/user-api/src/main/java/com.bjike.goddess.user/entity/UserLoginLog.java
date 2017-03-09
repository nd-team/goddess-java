package com.bjike.goddess.user.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.user.enums.LoginType;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 用户登陆日志
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "user_login_log")
public class UserLoginLog extends BaseEntity {

    /**
     * 登录时间
     */
    @OrderBy(value = "loginTime desc ")
    @Column(columnDefinition = "DATETIME  DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间'  ", nullable = false)
    private LocalDateTime loginTime;
    /**
     * 登录地点
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '登录地点' ")
    private String loginAddress;
    /**
     * ip地址
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '登录ip' ")
    private String loginIp;
    /**
     * 登录方式
     */
    @Column(columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '登录方式'", nullable = false, insertable = false)
    private LoginType loginType;

    /**
     * 用户关联
     */
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '所属用户' ")
    private User user;

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginAddress() {
        return loginAddress;
    }

    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
