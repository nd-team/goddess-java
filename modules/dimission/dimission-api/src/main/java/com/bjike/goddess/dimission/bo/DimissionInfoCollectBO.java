package com.bjike.goddess.dimission.bo;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-04-17 16:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DimissionInfoCollectBO implements Serializable {

    /**
     * 项目组
     */
    private String department;

    /**
     * 岗位
     */
    private String position;

    /**
     * 入职时间
     */
    private String entryTime;

    /**
     * 在司工龄
     */
    private String seniority;

    /**
     * 学历
     */
    private String education;

    /**
     * 正常离职人数
     */
    private Integer normal;

    /**
     * 自离人数
     */
    private Integer presume;

    /**
     * 辞退人数
     */
    private Integer refuse;

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

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getNormal() {
        return normal;
    }

    public void setNormal(Integer normal) {
        this.normal = normal;
    }

    public Integer getPresume() {
        return presume;
    }

    public void setPresume(Integer presume) {
        this.presume = presume;
    }

    public Integer getRefuse() {
        return refuse;
    }

    public void setRefuse(Integer refuse) {
        this.refuse = refuse;
    }
}
