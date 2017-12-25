package com.bjike.goddess.archive.vo;

import com.bjike.goddess.common.api.type.Status;

/**
 * 对外人员信息表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 03:09 ]
 * @Description: [ 对外人员信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ForeignStaffingVO {

    /**
     * id
     */
    private String id;

    /**
     * 更新时间
     */
    protected String modifyTime;

    /**
     * 使用类型id
     */
    private String typeId;

    /**
     * 使用类型
     */
    private String typeName;

    /**
     * 姓名
     */
    private String username;

    /**
     * 员工编号
     */
    private String employeesNumber;

    /**
     * 项目组
     */
    private String project;

    /**
     * 职位
     */
    private String position;

    /**
     * 学历
     */
    private String education;

    /**
     * 资质/认证证书
     */
    private String litterae;

    /**
     * 是否购买社保
     */
    private Boolean buySecurity;

    /**
     * 社保购买类型
     */
    private String securityType;

    /**
     * 购买社保所属公司
     */
    private String company;

    /**
     * 专业
     */
    private String major;

    /**
     * 毕业学校
     */
    private String school;

    /**
     * 毕业时间
     */
    private String graduate;

    /**
     * 入职时间
     */
    private String entry;

    /**
     * 在职时间(月)
     */
    private Integer seniority;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 出生日期
     */
    private String birth;

    /**
     * 户籍地址
     */
    private String address;

    /**
     * 身份证号码
     */
    private String identityCard;

    /**
     * 银行卡号
     */
    private String bankCard;

    /**
     * 开户行
     */
    private String bank;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     */
    private Status status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(String employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public Integer getSeniority() {
        return seniority;
    }

    public void setSeniority(Integer seniority) {
        this.seniority = seniority;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getLitterae() {
        return litterae;
    }

    public void setLitterae(String litterae) {
        this.litterae = litterae;
    }

    public Boolean getBuySecurity() {
        return buySecurity;
    }

    public void setBuySecurity(Boolean buySecurity) {
        this.buySecurity = buySecurity;
    }

    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}