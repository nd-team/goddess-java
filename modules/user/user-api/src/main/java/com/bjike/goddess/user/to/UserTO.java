package com.bjike.goddess.user.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.user.enums.UserType;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户业务传送对象
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-18 15:25]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserTO extends BaseTO {

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public interface UPDATEPWD{}

    public interface UPDATEPHONE{}

    public interface BECOMEENTERPRISE{}

    /**
     * 用户名
     */
    @NotBlank(groups ={ UserTO.BECOMEENTERPRISE.class} , message = "用户名不能为空")
    private String username;

    /**
     * 登录手机(注册验证手机)
     */
    @NotBlank(groups ={ UserTO.UPDATEPHONE.class} , message = "手机号不能为空")
    private String phone;
    /**
     * 登录邮箱
     */

    private String email;
    /**
     * 登陆密码
     */
    @NotBlank(groups ={ UserTO.UPDATEPWD.class} , message = "密码不能为空")
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
    @NotBlank(groups ={ UserTO.BECOMEENTERPRISE.class} , message = "员工编号不能为空")
    private String employeeNumber;

    /**
     * 用户状态
     */
    private Status status;

    /**
     * 用户类型
     */
    private UserType userType;

    /**
     * 用户积分；String
     */
    private String integral;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
