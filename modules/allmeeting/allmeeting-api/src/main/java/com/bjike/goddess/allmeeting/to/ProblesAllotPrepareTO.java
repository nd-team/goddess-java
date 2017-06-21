package com.bjike.goddess.allmeeting.to;

import com.bjike.goddess.allmeeting.enums.ProblemStatus;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 问题分配责任模块议题准备信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 04:55 ]
 * @Description: [ 问题分配责任模块议题准备信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblesAllotPrepareTO extends BaseTO {

    /**
     * 会议编号
     */
    @NotBlank(message = "会议编号不能为空", groups = {ADD.class, EDIT.class})
    private String meetingNum;


    /**
     * 问题来源
     */
    @NotBlank(message = "问题来源不能为空", groups = {ADD.class, EDIT.class})
    private String resource;

    /**
     * 获取时间
     */
    @NotBlank(message = "获取时间不能为空", groups = {ADD.class, EDIT.class})
    private String getTime;

    /**
     * 问题描述
     */
    @NotBlank(message = "问题描述不能为空", groups = {ADD.class, EDIT.class})
    private String description;

    /**
     * 问题提出人
     */
    @NotBlank(message = "问题提出人不能为空", groups = {ADD.class, EDIT.class})
    private String putForwardUSer;

    /**
     * 是否有解决方案
     */
    @NotNull(message = "是否有解决方案不能为空", groups = {ADD.class, EDIT.class})
    private Boolean solution;

    /**
     * 优先级
     */
    @NotBlank(message = "优先级不能为空", groups = {ADD.class, EDIT.class})
    private String priority;

    /**
     * 责任模块
     */
    @NotBlank(message = "责任模块不能为空", groups = {ADD.class, EDIT.class})
    private String module;

    /**
     * 责任人
     */
    @NotBlank(message = "责任人不能为空", groups = {ADD.class, EDIT.class})
    private String responsibleUSer;

    /**
     * 处理时间
     */
    @NotBlank(message = "处理时间不能为空", groups = {ADD.class, EDIT.class})
    private String dealTime;

    /**
     * 问题处理状态
     */
    @NotNull(message = "问题处理状态不能为空", groups = {ADD.class, EDIT.class})
    private ProblemStatus problemStatus;


    public String getMeetingNum() {
        return meetingNum;
    }

    public void setMeetingNum(String meetingNum) {
        this.meetingNum = meetingNum;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
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

    public ProblemStatus getProblemStatus() {
        return problemStatus;
    }

    public void setProblemStatus(ProblemStatus problemStatus) {
        this.problemStatus = problemStatus;
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }
}