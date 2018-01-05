package com.bjike.goddess.staffentry.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;


/**
 * 员工入职注册导出模板
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-15 07:03 ]
 * @Description: [ 员工入职注册导出模板 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffEntryRegisterExpTemplate extends BaseBO {

    /**
     * 所属部门/项目组
     */
    @ExcelHeader(name="所属部门/项目组")
    private String department;
    /**
     * 用户名
     */
    @ExcelHeader(name="用户名",notNull = true)
    private String userName;

    /**
     * 员工编号
     */
    @ExcelHeader(name="员工编号",notNull = true)
    private String empNumber;
    /**
     * 联系电话
     */
    @ExcelHeader(name="联系电话",notNull = true)
    private String contactNum;

    /**
     * 入职日期
     */
    @ExcelHeader(name="入职日期",notNull = true)
    private String entryDate;
    /**
     * 是否住宿
     */
    @ExcelHeader(name="是否住宿",notNull = true)
    private String lodge;
    /**
     * 是否使用公司电脑
     */
    @ExcelHeader(name="是否使用公司电脑",notNull = true)
    private String useCompanyComputer;
    /**
     * 入职地址
     */
    @ExcelHeader(name="入职地址",notNull = true)
    private String entryAddress;
    /**
     * 是否入职
     */
    @ExcelHeader(name="是否入职",notNull = true)
    private String entry;
    /**
     * 未入职原因
     */
    @ExcelHeader(name="未入职原因")
    private String noEntryCause;
    /**
     * 地区
     */
    @ExcelHeader(name="地区",notNull = true)
    private String area;
    /**
     * 是否通告
     */
    @ExcelHeader(name="是否通告")
    private String notice;
    /**
     * 注册邮箱使用手机号
     */
    @ExcelHeader(name="注册邮箱使用手机号")
    private String registerUseNum;

    /**
     * 职位
     */
    @ExcelHeader(name="职位")
    private String position;

    /**
     * 工作邮箱
     */
    @ExcelHeader(name="工作邮箱")
    private String workEmail;
    /**
     * 帐号密码是否已告知(是/否)
     */
    @ExcelHeader(name="帐号密码是否已告知")
    private String tellStatus;
    /**
     * 工作邮箱密码
     */
    @ExcelHeader(name="工作邮箱密码")
    private String workEmailPassword;
    /**
     * 用户密码
     */
    @ExcelHeader(name="用户密码",notNull = true)
    private String password;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

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

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getLodge() {
        return lodge;
    }

    public void setLodge(String lodge) {
        this.lodge = lodge;
    }

    public String getUseCompanyComputer() {
        return useCompanyComputer;
    }

    public void setUseCompanyComputer(String useCompanyComputer) {
        this.useCompanyComputer = useCompanyComputer;
    }

    public String getEntryAddress() {
        return entryAddress;
    }

    public void setEntryAddress(String entryAddress) {
        this.entryAddress = entryAddress;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
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

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
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

    public String getTellStatus() {
        return tellStatus;
    }

    public void setTellStatus(String tellStatus) {
        this.tellStatus = tellStatus;
    }

    public String getWorkEmailPassword() {
        return workEmailPassword;
    }

    public void setWorkEmailPassword(String workEmailPassword) {
        this.workEmailPassword = workEmailPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}