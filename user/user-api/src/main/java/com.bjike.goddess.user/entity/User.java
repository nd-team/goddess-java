package com.bjike.goddess.user.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(unique = true, length = 16, nullable = false)
    private String username; //登录用户名

    @Column(unique = true, length = 11, nullable = false)
    private String phone;//登录手机(注册验证手机)

    @Email
    private String email;//登录邮箱

    @Column(nullable = false)
    private String password; //登陆密码

    private String headSculpture;//头像

    @Column(unique = true)
    private String nickname; //昵称

    @Column(unique = true,nullable = false)
    private String employeeNumber;


    private LocalDateTime createTime ;//创建时间

    private Status status = Status.THAW;//用户状态


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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

}
