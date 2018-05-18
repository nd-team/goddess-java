package com.bjike.goddess.user.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-11 16:42]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AppUserRegisterTO extends BaseTO {

    /**
     * 注册类型
     */
    @NotBlank(message = "注册类型不能为空",groups = {ADD.class})
    private String registerType;
    /**
     * 手机号码
     */
    @NotBlank(message = "手机号码不能为空",groups = {ADD.class})
    private String phone;
    /**
     * 注册用名
     */
    @NotBlank(message = "用户名不能为空",groups = {ADD.class})
    private String username;
    /**
     * 注册密码
     */
    @NotBlank(message = "密码不能为空",groups = {ADD.class})
    private String password;
    /**
     * 编号
     */
    private String employeeNumber;

    /**
     * 企业/团体名称
     */
    private String enterpriseName;

    /**
     * 验证码
     */
    private String authCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
