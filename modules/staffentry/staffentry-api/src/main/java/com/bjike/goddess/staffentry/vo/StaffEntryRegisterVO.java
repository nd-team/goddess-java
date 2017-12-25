package com.bjike.goddess.staffentry.vo;

/**
 * 员工入职注册表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-15 07:03 ]
 * @Description: [ 员工入职注册表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffEntryRegisterVO {

    /**
     * id
     */
    private String id;
    /**
     * 员工编号
     */
    private String empNumber;
    /**
     * 员工密码
     */
    private String password;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 所属部门/项目组
     */
    private String department;
    /**
     * 联系电话
     */
    private String contactNum;

    /**
     * 入职日期
     */
    private String entryDate;
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
     * 更改后的邮箱密码
     */
    private String changeEmailPassword;

    /**
     * 职位
     */
    private String position;

    /**
     * 工作邮箱
     */
    private String workEmail;
    /**
     * 工作邮箱密码
     */
    private String workEmailPassword;
    /**
     * 帐号密码是否已告知
     */
    private String tellStatus;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 用户id
     */
    private String userId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getChangeEmailPassword() {
        return changeEmailPassword;
    }

    public void setChangeEmailPassword(String changeEmailPassword) {
        this.changeEmailPassword = changeEmailPassword;
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