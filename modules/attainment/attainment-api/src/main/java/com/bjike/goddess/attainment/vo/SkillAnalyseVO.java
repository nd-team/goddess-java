package com.bjike.goddess.attainment.vo;

/**
 * 技能分析表表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:45 ]
 * @Description: [ 技能分析表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SkillAnalyseVO {

    /**
     * id
     */
    private String id;
    /**
     * 员工
     */
    private String user;

    /**
     * 调研编号
     */
    private String serialNumber;

    /**
     * 调研类型
     */
    private String surveyType;

    /**
     * 技能名称
     */
    private String skill;

    /**
     * 技能水平
     */
    private String skillLevel;

    /**
     * 填写人
     */
    private String writer;

    /**
     * 填写时间
     */
    private String writerTime;

    /**
     * 备注
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getWriterTime() {
        return writerTime;
    }

    public void setWriterTime(String writerTime) {
        this.writerTime = writerTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}