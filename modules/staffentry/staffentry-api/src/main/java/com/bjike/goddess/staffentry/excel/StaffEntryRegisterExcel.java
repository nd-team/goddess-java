package com.bjike.goddess.staffentry.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.to.BaseTO;

import java.time.LocalDate;


/**
 * 员工入职注册导入
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-15 07:03 ]
 * @Description: [ 员工入职注册导入 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffEntryRegisterExcel extends BaseTO {

    /**
     * 所属部门/项目组
     */
    private String department;
    /**
     * 用户名
     */
    private String name;

    /**
     * 员工编号
     */
    private String employeeNumber;
    /**
     * 联系电话
     */
    private String contactNum;

    /**
     * 入职日期
     */
    private LocalDate entryDate;
    /**
     * 是否住宿
     */
    private Boolean lodge;
    /**
     * 是否使用公司电脑
     */
    private Boolean useCompanyComputer;
    /**
     * 入职地址
     */
    private String entryAddress;
    /**
     * 是否入职
     */
    private Boolean entry;
    /**
     * 未入职原因
     */
    private String noEntryCause;
    /**
     * 地区
     */
    private String area;
    /**
     * 是否通告
     */
    private Boolean notice;
    /**
     * 注册邮箱使用手机号
     */
    private String registerUseNum;

    /**
     * 职位
     */
    private String position;

    /**
     * 工作邮箱
     */
    private String workEmail;
    /**
     * 帐号密码是否已告知(是/否)
     */
    private Boolean tellStatus;
    /**
     * 工作邮箱密码
     */
    private String workEmailPassword;
    /**
     * 更改后的邮箱密码
     */
    private String changeEmailPassword;
    /**
     * 用户密码
     */
    private String password;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public Boolean getLodge() {
        return lodge;
    }

    public void setLodge(Boolean lodge) {
        this.lodge = lodge;
    }

    public Boolean getUseCompanyComputer() {
        return useCompanyComputer;
    }

    public void setUseCompanyComputer(Boolean useCompanyComputer) {
        this.useCompanyComputer = useCompanyComputer;
    }

    public String getEntryAddress() {
        return entryAddress;
    }

    public void setEntryAddress(String entryAddress) {
        this.entryAddress = entryAddress;
    }

    public Boolean getEntry() {
        return entry;
    }

    public void setEntry(Boolean entry) {
        this.entry = entry;
    }

    public String getNoEntryCause() {
        return noEntryCause;
    }

    public void setNoEntryCause(String noEntryCause) {
        this.noEntryCause = noEntryCause;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Boolean getNotice() {
        return notice;
    }

    public void setNotice(Boolean notice) {
        this.notice = notice;
    }

    public String getRegisterUseNum() {
        return registerUseNum;
    }

    public void setRegisterUseNum(String registerUseNum) {
        this.registerUseNum = registerUseNum;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public Boolean getTellStatus() {
        return tellStatus;
    }

    public void setTellStatus(Boolean tellStatus) {
        this.tellStatus = tellStatus;
    }

    public String getWorkEmailPassword() {
        return workEmailPassword;
    }

    public void setWorkEmailPassword(String workEmailPassword) {
        this.workEmailPassword = workEmailPassword;
    }

    public String getChangeEmailPassword() {
        return changeEmailPassword;
    }

    public void setChangeEmailPassword(String changeEmailPassword) {
        this.changeEmailPassword = changeEmailPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}