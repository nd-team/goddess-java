package com.bjike.goddess.archive.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 人员简历信息业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-14 11:30 ]
 * @Description: [ 人员简历信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ResumeInfoExportExcel extends BaseBO {

    /**
     * 更新时间
     */
    @ExcelHeader(name = "更新时间")
    private String modifyTime;

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名")
    private String username;

    /**
     * 员工编号
     */
    @ExcelHeader(name = "员工编号")
    private String serialNumber;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区")
    private String area;

    /**
     * 项目组
     */
    @ExcelHeader(name = "项目组")
    private String project;

    /**
     * 职位
     */
    @ExcelHeader(name = "职位")
    private String position;

    /**
     * 学历
     */
    @ExcelHeader(name = "学历")
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
    @ExcelHeader(name = "入职时间")
    private String entryTime;

    /**
     * 兴趣爱好
     */
    @ExcelHeader(name = "兴趣爱好")
    private String hobby;

    /**
     * 工作经历描述
     */
    @ExcelHeader(name = "工作经历描述")
    private String description;

    /**
     * 行业
     */
    @ExcelHeader(name = "行业")
    private String industry;

    /**
     * 状态
     */
    @ExcelHeader(name = "状态")
    private String status;


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}