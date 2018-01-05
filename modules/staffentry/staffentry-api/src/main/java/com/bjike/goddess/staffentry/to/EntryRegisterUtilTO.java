package com.bjike.goddess.staffentry.to;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 入职登记业务传输辅助对象
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 11:09]
 * @Description: [入职登记业务传输辅助对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class EntryRegisterUtilTO implements Serializable  {

    public interface TestBaseInfo{}
    /**
     * id
     */
    private String id;
    /**
     * 员工编号
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "员工编号不能为空")
    private String empNumber;
    /**
     * 姓名
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "姓名不能为空")
    private String username;

    /**
     * 性别0男1女
     */
    @NotNull(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "性别不能为空,0男1女")
    private Integer gender;

    /**
     * 出生年月日,日期格式2017-10-01
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "出生年月日不能为空,日期格式2017-10-01")
    private String birthday;

    /**
     * 民族
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "民族不能为空")
    private String nation;

    /**
     * 婚姻状况
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "婚姻状况不能为空")
    private String marriage;

    /**
     * 政治面貌
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "政治面貌不能为空")
    private String politicsStatus;

    /**
     * 籍贯
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "籍贯不能为空")
    private String nativePlace;

    /**
     * 身高
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "身高不能为空")
    private String stature;

    /**
     * 专业
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "专业不能为空")
    private String profession;

    /**
     * 学历
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "学历不能为空")
    private String education;

    /**
     * 毕业学校
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "毕业学校不能为空")
    private String schoolTag;

    /**
     * 毕业时间，日期格式2017-01-01
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "毕业时间不能为空,日期格式")
    private String graduationDate;

    /**
     * 健康状况
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "健康状况不能为空")
    private String healthStatus;

    /**
     * QQ号
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "QQ号不能为空")
    private String qq;

    /**
     * 手机号
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "手机号不能为空")
    private String phone;

    /**
     * 个人邮箱账号
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "个人邮箱账号不能为空")
    private String email;

    /**
     * 紧急情况联系人
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "紧急情况联系人不能为空")
    private String emergencyContact;

    /**
     * 联系电话
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "联系电话不能为空")
    private String phoneNumber;

    /**
     * 身份证号码
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "身份证号码不能为空")
    private String idCard;

    /**
     * 户口地址
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "户口地址不能为空")
    private String registeredAddress;

    /**
     * 目前住宿地址
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "目前住宿地址不能为空")
    private String location;

    /**
     * 兴趣爱好
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "兴趣爱好不能为空")
    private String hobbies;
    /**
     * 地区
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "地区不能为空")
    private String area;
    /**
     * 部门/项目组
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "部门/项目组不能为空")
    private String department;
    /**
     * 职位 
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "职位不能为空")
    private String position;
    /**
     * 入职日期 
     */
    @NotBlank(groups = {EntryRegisterUtilTO.TestBaseInfo.class} , message = "入职日期不能为空")
    private String inductionDate;
    /**
     * 家庭成员称谓集合
     */
    private List<String> titles;

    /**
     * 家庭成员姓名集合
     */
    private List<String> names;

    /**
     * 家庭成员年龄集合年龄在1-200之间
     */
    private List<Integer> ages;

    /**
     * 家庭成员单位集合
     */
    private List<String> units;

    /**
     * 家庭成员职务集合
     */
    private List<String> positions;

    /**
     * 家庭成员联系方式集合
     */
    private List<String> phones;

    /**
     * 学习经历开始时间集合
     */
    private List<String> studyStartTimes;
    /**
     * 学习经历结束时间集合
     */
    private List<String> studyEndTimes;
    /**
     * 学习经历就读学校集合
     */
    private List<String> schools;
    /**
     * 学习经历毕业证书集合
     */
    private List<String> certificates;

    /**
     * 工作经历开始时间集合
     */
    private List<String> workStartTimes;
    /**
     * 工作经历结束时间集合
     */
    private List<String> workEndTimes;

    /**
     * 工作经历公司名称集合
     */
    private List<String> firms;

    /**
     * 工作经历工作描述集合
     */
    private List<String> jobDescriptions;

    /**
     * 证书名称集合
     */
    private List<String> nameses;
    /**
     * 获得证书时间集合
     */
    private List<String> obtainTimes;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
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

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
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

    public String getInductionDate() {
        return inductionDate;
    }

    public void setInductionDate(String inductionDate) {
        this.inductionDate = inductionDate;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<Integer> getAges() {
        return ages;
    }

    public void setAges(List<Integer> ages) {
        this.ages = ages;
    }

    public List<String> getUnits() {
        return units;
    }

    public void setUnits(List<String> units) {
        this.units = units;
    }

    public List<String> getPositions() {
        return positions;
    }

    public void setPositions(List<String> positions) {
        this.positions = positions;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public List<String> getStudyStartTimes() {
        return studyStartTimes;
    }

    public void setStudyStartTimes(List<String> studyStartTimes) {
        this.studyStartTimes = studyStartTimes;
    }

    public List<String> getStudyEndTimes() {
        return studyEndTimes;
    }

    public void setStudyEndTimes(List<String> studyEndTimes) {
        this.studyEndTimes = studyEndTimes;
    }

    public List<String> getSchools() {
        return schools;
    }

    public void setSchools(List<String> schools) {
        this.schools = schools;
    }

    public List<String> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<String> certificates) {
        this.certificates = certificates;
    }

    public List<String> getWorkStartTimes() {
        return workStartTimes;
    }

    public void setWorkStartTimes(List<String> workStartTimes) {
        this.workStartTimes = workStartTimes;
    }

    public List<String> getWorkEndTimes() {
        return workEndTimes;
    }

    public void setWorkEndTimes(List<String> workEndTimes) {
        this.workEndTimes = workEndTimes;
    }

    public List<String> getFirms() {
        return firms;
    }

    public void setFirms(List<String> firms) {
        this.firms = firms;
    }

    public List<String> getJobDescriptions() {
        return jobDescriptions;
    }

    public void setJobDescriptions(List<String> jobDescriptions) {
        this.jobDescriptions = jobDescriptions;
    }

    public List<String> getNameses() {
        return nameses;
    }

    public void setNameses(List<String> nameses) {
        this.nameses = nameses;
    }

    public List<String> getObtainTimes() {
        return obtainTimes;
    }

    public void setObtainTimes(List<String> obtainTimes) {
        this.obtainTimes = obtainTimes;
    }
}
