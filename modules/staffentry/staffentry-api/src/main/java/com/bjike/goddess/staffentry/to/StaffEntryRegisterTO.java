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
public class StaffEntryRegisterTO extends BaseTO {

    public interface TestAdd{}
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
     * 所属部门
     */
    private String department;

    /**
     * 角色列表
     */
    private String role;

    /**
     * 职位
     */
    private String position;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 工作邮箱
     */
    private String workEmail;

    /**
     * 工作邮箱密码
     */
    private String workEmailPassword;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 帐号密码是否已告知(是/否)
     */
    private String tellStatus;
    /**
     * 用户id
     */
    private String userId;

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTellStatus() {
        return tellStatus;
    }

    public void setTellStatus(String tellStatus) {
        this.tellStatus = tellStatus;
    }
}