package com.bjike.goddess.managepromotion.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 员工职能定级
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-22 04:53 ]
 * @Description: [ 员工职能定级 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EmployeeFunctionLevelTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 体系
     */
    @NotBlank(message = "体系不能为空",groups = {ADD.class, EDIT.class})
    private String system;

    /**
     * 部门/项目组
     */
    @NotBlank(message = "部门/项目组不能为空",groups = {ADD.class, EDIT.class})
    private String department;

    /**
     * 岗位
     */
    @NotBlank(message = "岗位不能为空",groups = {ADD.class, EDIT.class})
    private String jobs;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空",groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 入职时间
     */
    @NotBlank(message = "入职时间不能为空",groups = {ADD.class, EDIT.class})
    private String entryTime;

    /**
     * 入职-职能定位-专业（业务范围包含的技能）
     */
    @NotBlank(message = "入职-职能定位-专业（业务范围包含的技能）不能为空",groups = {ADD.class, EDIT.class})
    private String entryMajor;

    /**
     * 入职主项技能等级
     */
    @NotBlank(message = "入职主项技能等级不能为空",groups = {ADD.class, EDIT.class})
    private String entryLevelSkill;

    /**
     * 转正技能等级
     */
    @NotBlank(message = "转正技能等级不能为空",groups = {ADD.class, EDIT.class})
    private String transferSkillLevel;

    /**
     * 转正时间
     */
    @NotBlank(message = "转正时间不能为空",groups = {ADD.class, EDIT.class})
    private String positiveTime;

    /**
     * 主项/小项（是否为主项）
     */
    @NotNull(message = "主项/小项（是否为主项）不能为空",groups = {ADD.class, EDIT.class})
    private Boolean subject;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getEntryMajor() {
        return entryMajor;
    }

    public void setEntryMajor(String entryMajor) {
        this.entryMajor = entryMajor;
    }

    public String getEntryLevelSkill() {
        return entryLevelSkill;
    }

    public void setEntryLevelSkill(String entryLevelSkill) {
        this.entryLevelSkill = entryLevelSkill;
    }

    public String getTransferSkillLevel() {
        return transferSkillLevel;
    }

    public void setTransferSkillLevel(String transferSkillLevel) {
        this.transferSkillLevel = transferSkillLevel;
    }

    public String getPositiveTime() {
        return positiveTime;
    }

    public void setPositiveTime(String positiveTime) {
        this.positiveTime = positiveTime;
    }

    public Boolean getSubject() {
        return subject;
    }

    public void setSubject(Boolean subject) {
        this.subject = subject;
    }
}