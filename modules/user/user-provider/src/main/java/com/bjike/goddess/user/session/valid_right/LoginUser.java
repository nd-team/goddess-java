package com.bjike.goddess.user.session.valid_right;

import com.bjike.goddess.user.bo.rbac.PermissionBO;
import com.bjike.goddess.user.entity.rbac.Permission;

import java.io.Serializable;
import java.util.List;

/**
 * 登录用户
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-12 08:59]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class LoginUser implements Serializable {
    /**
     * 用户id
     */
    private String id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 登录手机(注册验证手机)
     */
    private String phone;
    /**
     * 登录邮箱
     */

    private String email;
    /**
     * 登陆密码
     */
    private String password;
    /**
     * 头像
     */
    private String headSculpture;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 员工编号
     */
    private String employeeNumber;


    private List<String> permissions;

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

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
