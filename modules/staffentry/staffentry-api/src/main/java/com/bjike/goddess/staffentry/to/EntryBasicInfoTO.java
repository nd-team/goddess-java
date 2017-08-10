package com.bjike.goddess.staffentry.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 入职基本信息业务传输对象
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-10 13:55]
 * @Description: [入职基本信息业务传输对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class EntryBasicInfoTO extends BaseTO {

    public interface TestAdd {
    }

    /**
     * 地区
     */
    @NotBlank(groups = {EntryBasicInfoTO.TestAdd.class}, message = "地区不能为空")
    private String area;
    /**
     * 部门
     */
    @NotBlank(groups = {EntryBasicInfoTO.TestAdd.class}, message = "部门不能为空")
    private String department;
    /**
     * 项目组
     */
    @NotBlank(groups = {EntryBasicInfoTO.TestAdd.class}, message = "项目组不能为空")
    private String projectGroup;
    /**
     * 岗位
     */
    @NotBlank(groups = {EntryBasicInfoTO.TestAdd.class}, message = "岗位不能为空")
    private String position;
    /**
     * 入职时间，格式为日期2017-01-01
     */
    @NotBlank(groups = {EntryBasicInfoTO.TestAdd.class}, message = "入职时间不能为空，格式为日期2017-01-01")
    private String entryTime;
    /**
     * 姓名
     */
    @NotBlank(groups = {EntryBasicInfoTO.TestAdd.class}, message = "姓名不能为空")
    private String name;
    /**
     * 员工编号
     */
    @NotBlank(groups = {EntryBasicInfoTO.TestAdd.class}, message = "员工编号不能为空")
    private String employeeID;
    /**
     * 邮箱账号
     */
    private String email;
    /**
     * 联系电话
     */
    @NotBlank(groups = {EntryBasicInfoTO.TestAdd.class}, message = "地区不能为空")
    private String phone;
    /**
     * 专业
     */
    @NotBlank(groups = {EntryBasicInfoTO.TestAdd.class}, message = "专业不能为空")
    private String profession;
    /**
     * 银行卡账号
     */
    private String bankCardID;
    /**
     * 开户行
     */
    private String bankOfDeposit;
    /**
     * 开户账户
     */
    private String bankAccount;
    /**
     * 薪资是否确认（true/false）
     */
    @NotNull(groups = {EntryBasicInfoTO.TestAdd.class}, message = "薪资是否确认不能为空（true/false）")
    private Boolean salaryConfirm;

    /**
     * 是否有身份件复印件（true/false）
     */
    @NotNull(groups = {EntryBasicInfoTO.TestAdd.class}, message = "是否有身份件复印件不能为空（true/false）")
    private Boolean iDFile;

    /**
     * 是否有学历学位证书复印件（true/false）
     */
    @NotNull(groups = {EntryBasicInfoTO.TestAdd.class}, message = "是否有学历学位证书复印件不能为空（true/false）")
    private Boolean educationFile;

    /**
     * 是否有照片纸质版（true/false）
     */
    @NotNull(groups = {EntryBasicInfoTO.TestAdd.class}, message = "是否有照片纸质版不能为空（true/false）")
    private Boolean photoFile;

    /**
     * 是否有照片电子版（true/false）
     */
    @NotNull(groups = {EntryBasicInfoTO.TestAdd.class}, message = "是否有照片电子版不能为空（true/false）")
    private Boolean photoEletricFile;

    /**
     * 是否有其本人银行卡、身份证、签名的复印件（true/false）
     */
    @NotNull(groups = {EntryBasicInfoTO.TestAdd.class}, message = "是否有其本人银行卡、身份证、签名的复印件不能为空（true/false）")
    private Boolean bankAndOtherFile;

    /**
     * 是否已邮件发送入职通告（true/false）
     */
    private Boolean emailInfo;

    /**
     * 邮件通知对象邮箱
     */
    private String[] emails;

    /**
     * 入职人数
     */
    private Long entryCount;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getBankCardID() {
        return bankCardID;
    }

    public void setBankCardID(String bankCardID) {
        this.bankCardID = bankCardID;
    }

    public String getBankOfDeposit() {
        return bankOfDeposit;
    }

    public void setBankOfDeposit(String bankOfDeposit) {
        this.bankOfDeposit = bankOfDeposit;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Boolean getSalaryConfirm() {
        return salaryConfirm;
    }

    public void setSalaryConfirm(Boolean salaryConfirm) {
        this.salaryConfirm = salaryConfirm;
    }

    public Boolean getiDFile() {
        return iDFile;
    }

    public void setiDFile(Boolean iDFile) {
        this.iDFile = iDFile;
    }

    public Boolean getEducationFile() {
        return educationFile;
    }

    public void setEducationFile(Boolean educationFile) {
        this.educationFile = educationFile;
    }

    public Boolean getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(Boolean photoFile) {
        this.photoFile = photoFile;
    }

    public Boolean getPhotoEletricFile() {
        return photoEletricFile;
    }

    public void setPhotoEletricFile(Boolean photoEletricFile) {
        this.photoEletricFile = photoEletricFile;
    }

    public Boolean getBankAndOtherFile() {
        return bankAndOtherFile;
    }

    public void setBankAndOtherFile(Boolean bankAndOtherFile) {
        this.bankAndOtherFile = bankAndOtherFile;
    }

    public Boolean getEmailInfo() {
        return emailInfo;
    }

    public void setEmailInfo(Boolean emailInfo) {
        this.emailInfo = emailInfo;
    }

    public String[] getEmails() {
        return emails;
    }

    public void setEmails(String[] emails) {
        this.emails = emails;
    }

    public Long getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(Long entryCount) {
        this.entryCount = entryCount;
    }
}
