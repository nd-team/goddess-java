package com.bjike.goddess.allmeeting.vo;

import com.bjike.goddess.allmeeting.enums.AffectNode;
import com.bjike.goddess.allmeeting.enums.ProblemClassify;
import com.bjike.goddess.common.api.type.Status;

import java.time.LocalDateTime;

/**
 * 问题分类议题准备信息表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 05:44 ]
 * @Description: [ 问题分类议题准备信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblesClassifyPrepareVO {

    /**
     * id
     */
    private String id;
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
     * 问题描述
     */
    private String description;

    /**
     * 问题提出人
     */
    private String putForwardUSer;

    /**
     * 影响节点
     */
    private AffectNode affectNode;

    /**
     * 问题分类
     */
    private ProblemClassify problemClassify;

    /**
     * 最晚处理时间
     */
    private String latestDealTime;

    /**
     * 责任模块
     */
    private String module;

    /**
     * 关联模块
     */
    private String relation;

    /**
     * 状态
     */
    private Status status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public AffectNode getAffectNode() {
        return affectNode;
    }

    public void setAffectNode(AffectNode affectNode) {
        this.affectNode = affectNode;
    }

    public ProblemClassify getProblemClassify() {
        return problemClassify;
    }

    public void setProblemClassify(ProblemClassify problemClassify) {
        this.problemClassify = problemClassify;
    }

    public String getLatestDealTime() {
        return latestDealTime;
    }

    public void setLatestDealTime(String latestDealTime) {
        this.latestDealTime = latestDealTime;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}