package com.bjike.goddess.allmeeting.to;

import com.bjike.goddess.allmeeting.enums.AffectNode;
import com.bjike.goddess.allmeeting.enums.ProblemClassify;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 问题分类议题准备信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 05:44 ]
 * @Description: [ 问题分类议题准备信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblesClassifyPrepareTO extends BaseTO {

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
     * 影响节点
     */
    @NotNull(message = "影响节点不能为空", groups = {ADD.class, EDIT.class})
    private AffectNode affectNode;

    /**
     * 问题分类
     */
    @NotNull(message = "问题分类不能为空", groups = {ADD.class, EDIT.class})
    private ProblemClassify problemClassify;

    /**
     * 最晚处理时间
     */
    @NotBlank(message = "最晚处理时间不能为空", groups = {ADD.class, EDIT.class})
    private String latestDealTime;

    /**
     * 责任模块
     */
    @NotBlank(message = "责任模块不能为空", groups = {ADD.class, EDIT.class})
    private String module;

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


}
