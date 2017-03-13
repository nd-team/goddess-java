package com.bjike.goddess.staffentry.to;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 入职登记业务传输对象
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 11:09]
 * @Description: [入职登记业务传输对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class EntryRegisterTO {

    /**
     * id
     */
    private String id ;
    /**
     * 员工编号
     */
    private String empNumber;
    /**
     * 姓名
     */
    private String username;

    /**
     *性别0男1女
     */
    private Integer gender;

    /**
     *出生年月日
     */
    private String birthday;

    /**
     *民族
     */
    private String nation;

    /**
     *婚姻状况
     */
    private String marriage;

    /**
     *政治面貌
     */
    private String politicsStatus;

    /**
     *籍贯
     */
    private String nativePlace;

    /**
     *身高
     */
    private String stature;

    /**
     *专业
     */
    private String profession;

    /**
     *学历
     */
    private String education;

    /**
     *毕业学校
     */
    private String schoolTag;

    /**
     *毕业时间
     */
    private String graduationDate;

    /**
     *健康状况
     */
    private String healthStatus;

    /**
     *QQ号
     */
    private String qq;

    /**
     *手机号
     */
    private String phone;

    /**
     *个人邮箱账号
     */
    private String email;

    /**
     *紧急情况联系人
     */
    private String emergencyContact;

    /**
     *联系电话
     */
    private String phoneNumber;

    /**
     *身份证号码
     */
    private String idCard;

    /**
     *户口地址
     */
    private String registeredAddress;

    /**
     *目前住宿地址
     */
    private String location;

    /**
     *兴趣爱好
     */
    private String hobbies;

    /**
     * 家庭成员集合
     */
    private List<FamilyMemberTO> familyMemberBOList;

    /**
     * 学习经历集合
     */
    private List<StudyExperienceTO> studyExperienceBOList ;

    /**
     * 工作经历集合
     */
    private List<WorkExperienceTO> workExperienceBOList ;

    /**
     * 证书集合
     */
    private List<CredentialTO> credentialBOList ;

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

    public List<FamilyMemberTO> getFamilyMemberBOList() {
        return familyMemberBOList;
    }

    public void setFamilyMemberBOList(List<FamilyMemberTO> familyMemberBOList) {
        this.familyMemberBOList = familyMemberBOList;
    }

    public List<StudyExperienceTO> getStudyExperienceBOList() {
        return studyExperienceBOList;
    }

    public void setStudyExperienceBOList(List<StudyExperienceTO> studyExperienceBOList) {
        this.studyExperienceBOList = studyExperienceBOList;
    }

    public List<WorkExperienceTO> getWorkExperienceBOList() {
        return workExperienceBOList;
    }

    public void setWorkExperienceBOList(List<WorkExperienceTO> workExperienceBOList) {
        this.workExperienceBOList = workExperienceBOList;
    }

    public List<CredentialTO> getCredentialBOList() {
        return credentialBOList;
    }

    public void setCredentialBOList(List<CredentialTO> credentialBOList) {
        this.credentialBOList = credentialBOList;
    }
}
