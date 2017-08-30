package com.bjike.goddess.managepromotion.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 员工职能定级业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-22 04:53 ]
 * @Description: [ 员工职能定级业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EmployeeFunctionLevelBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 体系
     */
    private String system;

    /**
     * 部门/项目组
     */
    private String department;

    /**
     * 岗位
     */
    private String jobs;

    /**
     * 姓名
     */
    private String name;

    /**
     * 入职时间
     */
    private String entryTime;

    /**
     * 入职-职能定位-专业（业务范围包含的技能）
     */
    private String entryMajor;

    /**
     * 入职主项技能等级
     */
    private String entryLevelSkill;

    /**
     * 转正技能等级
     */
    private String transferSkillLevel;

    /**
     * 转正时间
     */
    private String positiveTime;

    /**
     * 主项/小项（是否为主项）
     */
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