package com.bjike.goddess.staffentry.to;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.staffentry.entity.EntryRegister;

import java.util.List;

/**
 * 学习经历业务传输对象
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 14:33]
 * @Description: [学习经历业务传输对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class StudyExperienceTO extends BaseTO {

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 就读学校
     */
    private String school;

    /**
     * 毕业证书（学历）
     */
    private String certificate;

    /**
     * 员工入职外键
     */
    private EntryRegister entryRegister;

    /**
     *开始时间集合
     */
    private List<String> studyStartTimes;
    /**
     *结束时间集合
     */
    private List<String> studyEndTimes;
    /**
     *就读学校集合
     */
    private List<String> schools;
    /**
     *毕业证书集合
     */
    private List<String> certificates;


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

    public EntryRegister getEntryRegister() {
        return entryRegister;
    }

    public void setEntryRegister(EntryRegister entryRegister) {
        this.entryRegister = entryRegister;
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
}
