package com.bjike.goddess.staffentry.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.staffentry.entity.EntryRegister;

import java.util.List;

/**
 * 工作经历业务传输对象
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 14:34]
 * @Description: [工作经历业务传输对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class WorkExperienceTO extends BaseTO {


    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 公司名称
     */
    private String firm;

    /**
     * 工作描述
     */
    private String jobDescription;


    /**
     * 开始时间集合
     */
    private List<String> workStartTimes;
    /**
     * 结束时间集合
     */
    private List<String> workEndTimes;

    /**
     * 公司名称集合
     */
    private List<String> firms;

    /**
     * 工作描述集合
     */
    private List<String> jobDescriptions;

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

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
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
}
