package com.bjike.goddess.allmeeting.bo;

import com.bjike.goddess.allmeeting.enums.ProblemStatus;
import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;

import java.time.LocalDateTime;

/**
 * 问题分配责任模块议题准备信息业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 04:55 ]
 * @Description: [ 问题分配责任模块议题准备信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblesAllotPrepareBO extends BaseBO {

    /**
     * 会议编号
     */
    private String meetingNum;

    /**
     * 会议内容
     */
    private String content;

    /**
     * 问题来源
     */
    private String resource;

    /**
     * 获取时间
     */
    private String getTime;

    /**
     * 描述
     */
    private String description;

    /**
     * 问题提出人
     */
    private String putForwardUSer;

    /**
     * 是否有解决方案
     */
    private Boolean solution;

    /**
     * 关联模块
     */
    private String relation;

    /**
     * 优先级
     */
    private String priority;

    /**
     * 责任模块
     */
    private String module;

    /**
     * 责任人
     */
    private String responsibleUSer;

    /**
     * 处理时间
     */
    private String dealTime;

    /**
     * 问题处理状态
     */
    private ProblemStatus problemStatus;

    /**
     * 状态
     */
    private Status status;


    public String getMeetingNum() {
        return meetingNum;
    }

    public void setMeetingNum(String meetingNum) {
        this.meetingNum = meetingNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPutForwardUSer() {
        return putForwardUSer;
    }

    public void setPutForwardUSer(String putForwardUSer) {
        this.putForwardUSer = putForwardUSer;
    }

    public Boolean getSolution() {
        return solution;
    }

    public void setSolution(Boolean solution) {
        this.solution = solution;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getResponsibleUSer() {
        return responsibleUSer;
    }

    public void setResponsibleUSer(String responsibleUSer) {
        this.responsibleUSer = responsibleUSer;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public ProblemStatus getProblemStatus() {
        return problemStatus;
    }

    public void setProblemStatus(ProblemStatus problemStatus) {
        this.problemStatus = problemStatus;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}