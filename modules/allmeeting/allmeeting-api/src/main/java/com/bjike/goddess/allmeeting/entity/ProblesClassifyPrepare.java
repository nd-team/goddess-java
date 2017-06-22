package com.bjike.goddess.allmeeting.entity;

import com.bjike.goddess.allmeeting.enums.AffectNode;
import com.bjike.goddess.allmeeting.enums.ProblemClassify;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 问题分类议题准备信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 05:44 ]
 * @Description: [ 问题分类议题准备信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "allmeeting_problesclassifyprepare")
public class ProblesClassifyPrepare extends BaseEntity {

    /**
     * 会议编号
     */
    @Column(name = "meetingNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议编号'")
    private String meetingNum;

    /**
     * 问题来源
     */
    @Column(name = "resource", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题来源'")
    private String resource;

    /**
     * 获取时间
     */
    @Column(name = "getTime", nullable = false, columnDefinition = "DATETIME   COMMENT '获取时间'")
    private LocalDateTime getTime;

    /**
     * 描述
     */
    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String description;

    /**
     * 问题提出人
     */
    @Column(name = "putForwardUSer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题提出人'")
    private String putForwardUSer;

    /**
     * 影响节点
     */
    @Column(name = "affectNode", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '影响节点'")
    private AffectNode affectNode;

    /**
     * 问题分类
     */
    @Column(name = "problemClassify", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题分类'")
    private ProblemClassify problemClassify;

    /**
     * 最晚处理时间
     */
    @Column(name = "latestDealTime", nullable = false, columnDefinition = "DATETIME   COMMENT '最晚处理时间'")
    private LocalDateTime latestDealTime;

    /**
     * 责任模块
     */
    @Column(name = "module", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '责任模块'")
    private String module;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '状态'")
    private Status status;


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

    public LocalDateTime getGetTime() {
        return getTime;
    }

    public void setGetTime(LocalDateTime getTime) {
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

    public LocalDateTime getLatestDealTime() {
        return latestDealTime;
    }

    public void setLatestDealTime(LocalDateTime latestDealTime) {
        this.latestDealTime = latestDealTime;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}