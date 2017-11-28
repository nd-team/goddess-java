package com.bjike.goddess.archive.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * @Author: [dengjunren]
 * @Date: [2017-05-22 17:46]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class StaffRecordsExcelTO extends BaseTO {

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名", notNull = true)
    private String username;

    /**
     * 员工编号
     */
    @ExcelHeader(name = "员工编号", notNull = true)
    private String serialNumber;

    /**
     * 项目组
     */
    @ExcelHeader(name = "项目组", notNull = true)
    private String project;

    /**
     * 职位
     */
    @ExcelHeader(name = "职位", notNull = true)
    private String position;

    /**
     * 学历
     */
    @ExcelHeader(name = "学历", notNull = true)
    private String education;

    /**
     * 专业
     */
    @ExcelHeader(name = "专业")
    private String major;

    /**
     * 毕业学校
     */
    @ExcelHeader(name = "毕业学校")
    private String school;

    /**
     * 毕业时间
     */
    @ExcelHeader(name = "毕业时间")
    private String graduate;

    /**
     * 入职时间
     */
    @ExcelHeader(name = "入职时间", notNull = true)
    private String entryTime;

    /**
     * 在职时间(月)
     */
    @ExcelHeader(name = "在职时间", notNull = true)
    private Integer seniority;

    /**
     * 电话号码
     */
    @ExcelHeader(name = "电话号码", notNull = true)
    private String telephone;

    /**
     * 出生日期
     */
    @ExcelHeader(name = "出生日期", notNull = true)
    private String birth;

    /**
     * 户籍地址
     */
    @ExcelHeader(name = "户籍地址")
    private String address;

    /**
     * 身份证号码
     */
    @ExcelHeader(name = "身份证号码", notNull = true)
    private String identityCard;

    /**
     * 银行卡号码
     */
    @ExcelHeader(name = "银行卡号码")
    private String bankCard;

    /**
     * 开户行
     */
    @ExcelHeader(name = "开户行")
    private String bank;

    /**
     * 邮箱
     */
    @ExcelHeader(name = "邮箱")
    private String email;

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
}
