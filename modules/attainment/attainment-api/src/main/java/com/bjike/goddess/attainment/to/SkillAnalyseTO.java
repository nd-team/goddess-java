package com.bjike.goddess.attainment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 技能分析表
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:45 ]
 * @Description: [ 技能分析表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SkillAnalyseTO extends BaseTO {

    /**
     * 员工
     */
    @NotNull(message = "员工不能为空", groups = {ADD.class, EDIT.class})
    private String user;

    /**
     * 调研编号
     */
    @NotNull(message = "调研编号不能为空", groups = {ADD.class, EDIT.class})
    private String serialNumber;

    /**
     * 调研类型
     */
    @NotNull(message = "调研类型不能为空", groups = {ADD.class, EDIT.class})
    private String surveyType;

    /**
     * 技能名称
     */
    @NotNull(message = "技能名称不能为空", groups = {ADD.class, EDIT.class})
    private String skill;

    /**
     * 技能水平
     */
    @NotNull(message = "技能水平不能为空", groups = {ADD.class, EDIT.class})
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