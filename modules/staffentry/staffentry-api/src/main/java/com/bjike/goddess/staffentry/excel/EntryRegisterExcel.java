package com.bjike.goddess.staffentry.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;

/**
 * 员工入职登记导出模板
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-08 10:23]
 * @Description: [员工入职登记导出模板]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class EntryRegisterExcel extends BaseBO {

    /**
     * 员工编号
     */
    @ExcelHeader(name = "员工编号", notNull = true)
    private String empNumber;
    /**
     * 用户名
     */
    @ExcelHeader(name = "用户名", notNull = true)
    private String username;

    /**
     * 性别0男1女
     */
    @ExcelHeader(name = "性别0男1女", notNull = true)
    private Integer gender;

    /**
     * 出生年月日
     */
    @ExcelHeader(name = "出生年月日", notNull = true)
    private LocalDate birthday;

    /**
     * 民族
     */
    @ExcelHeader(name = "民族", notNull = true)
    private String nation;

    /**
     * 婚姻状况
     */
    @ExcelHeader(name = "婚姻状况", notNull = true)
    private String marriage;

    /**
     * 政治面貌
     */
    @ExcelHeader(name = "政治面貌", notNull = true)
    private String politicsStatus;

    /**
     * 籍贯
     */
    @ExcelHeader(name = "籍贯", notNull = true)
    private String nativePlace;

    /**
     * 身高
     */
    @ExcelHeader(name = "身高", notNull = true)
    private String stature;

    /**
     * 专业
     */
    @ExcelHeader(name = "专业", notNull = true)
    private String profession;

    /**
     * 学历
     */
    @ExcelHeader(name = "学历", notNull = true)
    private String education;

    /**
     * 毕业学校
     */
    @ExcelHeader(name = "毕业学校", notNull = true)
    private String schoolTag;

    /**
     * 毕业时间
     */
    @ExcelHeader(name = "毕业时间", notNull = true)
    private LocalDate graduationDate;

    /**
     * 健康状况
     */
    @ExcelHeader(name = "健康状况", notNull = true)
    private String healthStatus;

    /**
     * QQ号
     */
    @ExcelHeader(name = "QQ号", notNull = true)
    private String qq;

    /**
     * 手机号
     */
    @ExcelHeader(name = "手机号", notNull = true)
    private String phone;

    /**
     * 个人邮箱账号
     */
    @ExcelHeader(name = "个人邮箱账号", notNull = true)
    private String email;

    /**
     * 紧急情况联系人
     */
    @ExcelHeader(name = "紧急情况联系人", notNull = true)
    private String emergencyContact;

    /**
     * 联系电话
     */
    @ExcelHeader(name = "联系电话", notNull = true)
    private String phoneNumber;

    /**
     * 身份证号码
     */
    @ExcelHeader(name = "身份证号码", notNull = true)
    private String idCard;

    /**
     * 户口地址
     */
    @ExcelHeader(name = "户口地址", notNull = true)
    private String registeredAddress;

    /**
     * 目前住宿地址
     */
    @ExcelHeader(name = "目前住宿地址", notNull = true)
    private String location;

    /**
     * 兴趣爱好
     */
    @ExcelHeader(name = "兴趣爱好", notNull = true)
    private String hobbies;
    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;
    /**
     * 部门/项目组
     */
    @ExcelHeader(name = "部门/项目组", notNull = true)
    private String department;
    /**
     * 职位 
     */
    @ExcelHeader(name = "职位", notNull = true)
    private String position;
    /**
     * 入职日期 
     */
    @ExcelHeader(name = "入职日期", notNull = true)
    private LocalDate inductionDate;
    /**
     * 称谓
     */
    @ExcelHeader(name = "称谓")
    private String title;

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名")
    private String name;

    /**
     * 年龄
     */
    @ExcelHeader(name = "年龄")
    private Integer age;

    /**
     * 单位
     */
    @ExcelHeader(name = "单位")
    private String unit;

    /**
     * 职务
     */
    @ExcelHeader(name = "职务")
    private String positionf;

    /**
     * 联系方式
     */
    @ExcelHeader(name = "联系方式")
    private String phonef;

    /**
     * 学习经历开始时间
     */
    @ExcelHeader(name = "学习经历开始时间")
    private LocalDate startTime;

    /**
     * 学习经历结束时间
     */
    @ExcelHeader(name = "学习经历结束时间")
    private LocalDate endTime;

    /**
     * 就读学校
     */
    @ExcelHeader(name = "就读学校")
    private String school;

    /**
     * 毕业证书（学历）
     */
    @ExcelHeader(name = "毕业证书（学历）")
    private String certificate;
    /**
     * 工作经历开始时间
     */
    @ExcelHeader(name = "工作经历开始时间")
    private LocalDate workStartTime;

    /**
     * 工作经历结束时间
     */
    @ExcelHeader(name = "工作经历结束时间")
    private LocalDate workEndTime;

    /**
     * 公司名称
     */
    @ExcelHeader(name = "公司名称")
    private String firm;

    /**
     * 工作描述
     */
    @ExcelHeader(name = "工作描述")
    private String jobDescription;
    /**
     * 证书名称
     */
    @ExcelHeader(name = "证书名称")
    private String name1;

    /**
     * 获得证书时间
     */
    @ExcelHeader(name = "获得证书时间")
    private LocalDate obtainTime;

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getStature() {
        return stature;
    }

    public void setStature(String stature) {
        this.stature = stature;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSchoolTag() {
        return schoolTag;
    }

    public void setSchoolTag(String schoolTag) {
        this.schoolTag = schoolTag;
    }

    public LocalDate getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(LocalDate graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getInductionDate() {
        return inductionDate;
    }

    public void setInductionDate(LocalDate inductionDate) {
        this.inductionDate = inductionDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPositionf() {
        return positionf;
    }

    public void setPositionf(String positionf) {
        this.positionf = positionf;
    }

    public String getPhonef() {
        return phonef;
    }

    public void setPhonef(String phonef) {
        this.phonef = phonef;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public LocalDate getWorkStartTime() {
        return workStartTime;
    }

    public void setWorkStartTime(LocalDate workStartTime) {
        this.workStartTime = workStartTime;
    }

    public LocalDate getWorkEndTime() {
        return workEndTime;
    }

    public void setWorkEndTime(LocalDate workEndTime) {
        this.workEndTime = workEndTime;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public LocalDate getObtainTime() {
        return obtainTime;
    }

    public void setObtainTime(LocalDate obtainTime) {
        this.obtainTime = obtainTime;
    }
}
