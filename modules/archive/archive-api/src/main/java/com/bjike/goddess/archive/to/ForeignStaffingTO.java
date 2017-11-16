package com.bjike.goddess.archive.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 对外人员信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 03:09 ]
 * @Description: [ 对外人员信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ForeignStaffingTO extends BaseTO {

    /**
     * 使用类型
     */
    @NotNull(message = "使用类型不能为空", groups = {ADD.class, EDIT.class})
    private String typeId;

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空", groups = {ADD.class, EDIT.class})
    private String username;

    /**
     * 员工编号
     */
    @NotNull(message = "员工编号不能为空", groups = {ADD.class, EDIT.class})
    private String employeesNumber;

    /**
     * 项目组
     */
    @NotNull(message = "项目组不能为空", groups = {ADD.class, EDIT.class})
    private String project;

    /**
     * 职位
     */
    @NotNull(message = "职位不能为空", groups = {ADD.class, EDIT.class})
    private String position;

    /**
     * 学历
     */
    @NotNull(message = "学历不能为空", groups = {ADD.class, EDIT.class})
    private String education;

    /**
     * 资质/认证证书
     */
    @NotBlank(message = "学历不能为空", groups = {ADD.class, EDIT.class})
    private String litterae;

    /**
     * 是否购买社保
     */
    @NotNull(message = "学历不能为空", groups = {ADD.class, EDIT.class})
    private Boolean buySecurity;

    /**
     * 社保购买类型
     */
    @NotBlank(message = "学历不能为空", groups = {ADD.class, EDIT.class})
    private String securityType;

    /**
     * 购买社保所属公司
     */
    @NotBlank(message = "学历不能为空", groups = {ADD.class, EDIT.class})
    private String company;

    /**
     * 专业
     */
    @NotNull(message = "专业不能为空", groups = {ADD.class, EDIT.class})
    private String major;

    /**
     * 毕业学校
     */
    @NotNull(message = "毕业学校不能为空", groups = {ADD.class, EDIT.class})
    private String school;

    /**
     * 毕业时间
     */
    @NotNull(message = "毕业时间不能为空", groups = {ADD.class, EDIT.class})
    private String graduate;

    /**
     * 入职时间
     */
    @NotNull(message = "入职时间不能为空", groups = {ADD.class, EDIT.class})
    private String entry;

    /**
     * 在职时间(月)
     */
    @NotNull(message = "在职时间(月)不能为空", groups = {ADD.class, EDIT.class})
    private Integer seniority;

    /**
     * 电话号码
     */
    @NotNull(message = "电话号码不能为空", groups = {ADD.class, EDIT.class})
    private String telephone;

    /**
     * 出生日期
     */
    @NotNull(message = "出生日期不能为空", groups = {ADD.class, EDIT.class})
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


    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
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
}