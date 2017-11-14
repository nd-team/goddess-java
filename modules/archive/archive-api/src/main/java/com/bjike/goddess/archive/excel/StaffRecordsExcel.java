package com.bjike.goddess.archive.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 员工档案表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 10:32 ]
 * @Description: [ 员工档案表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffRecordsExcel extends BaseTO{
    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名")
    private String username;

    /**
     * 员工编号
     */
    @ExcelHeader(name = "姓名")
    private String serialNumber;

    /**
     * 项目组
     */
    @ExcelHeader(name = "姓名")
    private String project;

    /**
     * 职位
     */
    @ExcelHeader(name = "姓名")
    private String position;

    /**
     * 学历
     */
    @ExcelHeader(name = "姓名")
    private String education;

    /**
     * 专业
     */
    @ExcelHeader(name = "姓名")
    private String major;

    /**
     * 毕业学校
     */
    @ExcelHeader(name = "姓名")
    private String school;

    /**
     * 毕业时间
     */
    @ExcelHeader(name = "姓名")
    private String graduate;

    /**
     * 入职时间
     */
    @ExcelHeader(name = "姓名")
    private String entryTime;

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
     * 银行卡号码
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
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
}