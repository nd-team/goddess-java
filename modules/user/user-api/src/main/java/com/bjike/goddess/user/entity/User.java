package com.bjike.goddess.user.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.user.enums.UserType;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity {
    /**
     * 系统编号
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(12) COMMENT '系统编号' ")
    private String systemNO;

    /**
     * 用户名
     */
    @Column(unique = true, length = 16, nullable = false, columnDefinition = "VARCHAR(255) COMMENT '用户名' ")
    private String username;

    /**
     * 登录手机(注册验证手机)
     */
    @Column(unique = true, length = 11, columnDefinition = "VARCHAR(11) COMMENT '手机号码' ")
    private String phone;
    /**
     * 登录邮箱
     */

    @Email
    @Column(unique = true, columnDefinition = "VARCHAR(255) COMMENT '用户邮箱'")
    private String email;
    /**
     * 登陆密码
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT '用户密码'")
    private String password;
    /**
     * 头像
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '用户头像'")
    private String headSculpture;
    /**
     * 昵称
     */
    @Column(unique = true, columnDefinition = "VARCHAR(255) COMMENT '用户昵称'")
    private String nickname;
    /**
     * 员工编号
     */
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(255) COMMENT '用户员工编号'")
    private String employeeNumber;

    /**
     * 企业/团体名称
     */
    @Column(unique = true,  columnDefinition = "VARCHAR(255) COMMENT '企业/团体名称'")
    private String enterpriseName;

    /*
     * 用户状态
     */
    @Column(columnDefinition = "TINYINT(2)  COMMENT '用户状态'", nullable = false)
    private Status status;

    /**
     * 用户类型
     */
    @Column(columnDefinition = "TINYINT(2)  COMMENT '用户类型' ", nullable = false)
    private UserType userType;

    /**
     * 用户积分；
     */
    @Column(name = "integral", columnDefinition = "VARCHAR(255)   COMMENT '用户积分；'")
    private String integral;

    public String getSystemNO() {
        return systemNO;
    }

    public void setSystemNO(String systemNO) {
        this.systemNO = systemNO;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadSculpture() {
        return headSculpture;
    }

    public void setHeadSculpture(String headSculpture) {
        this.headSculpture = headSculpture;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }
}
