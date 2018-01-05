package com.bjike.goddess.staffentry.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 员工入职注册
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-15 07:03 ]
 * @Description: [ 员工入职注册 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RegisterTO extends BaseTO {

    /**
     * 员工编号
     */
    @NotBlank(groups = {StaffEntryRegisterTO.TestAdd.class} , message = "员工编号不能为空")
    private String empNumber;
    /**
     * 用户名
     */
    @NotBlank(groups = {StaffEntryRegisterTO.TestAdd.class} , message = "用户名不能为空")
    private String userName;
    /**
     * 密码
     */
    @NotBlank(groups = {StaffEntryRegisterTO.TestAdd.class} , message = "密码不能为空")
    private String password;

    /**
     * 用户id
     */
    private String userId;
    /**
     * 工作邮箱
     */
    private String workEmail;

    /**
     * 工作邮箱密码
     */
    private String workEmailPassword;

    /**
     * 注册邮箱使用手机号
     */
    private String registerUseNum;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getWorkEmailPassword() {
        return workEmailPassword;
    }

    public void setWorkEmailPassword(String workEmailPassword) {
        this.workEmailPassword = workEmailPassword;
    }

    public String getRegisterUseNum() {
        return registerUseNum;
    }

    public void setRegisterUseNum(String registerUseNum) {
        this.registerUseNum = registerUseNum;
    }
}