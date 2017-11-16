package com.bjike.goddess.archive.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 人员简历信息
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-14 11:30 ]
 * @Description: [ 人员简历信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ResumeInfoTO extends BaseTO {

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空", groups = {ADD.class, EDIT.class})
    private String username;

    /**
     * 员工编号
     */
    @NotBlank(message = "员工编号不能为空", groups = {ADD.class, EDIT.class})
    private String serialNumber;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 项目组
     */
    @NotBlank(message = "项目组不能为空", groups = {ADD.class, EDIT.class})
    private String project;

    /**
     * 职位
     */
    @NotBlank(message = "职位不能为空", groups = {ADD.class, EDIT.class})
    private String position;

    /**
     * 学历
     */
    @NotBlank(message = "学历不能为空", groups = {ADD.class, EDIT.class})
    private String education;

    /**
     * 专业
     */
    @NotBlank(message = "专业不能为空", groups = {ADD.class, EDIT.class})
    private String major;

    /**
     * 毕业学校
     */
    @NotBlank(message = "毕业学校不能为空", groups = {ADD.class, EDIT.class})
    private String school;

    /**
     * 毕业时间
     */
    @NotBlank(message = "毕业时间不能为空", groups = {ADD.class, EDIT.class})
    private String graduate;

    /**
     * 入职时间
     */
    @NotBlank(message = "入职时间不能为空", groups = {ADD.class, EDIT.class})
    private String entryTime;

    /**
     * 兴趣爱好
     */
    private String hobby;

    /**
     * 工作经历描述
     */
    private String description;

    /**
     * 行业
     */
    private String industry;

//    /**
//     * 状态
//     */
//    private Status status;


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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

}