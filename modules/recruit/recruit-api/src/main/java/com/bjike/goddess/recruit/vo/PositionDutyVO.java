package com.bjike.goddess.recruit.vo;

import com.bjike.goddess.common.api.type.Status;

/**
 * 公司岗位分类岗位职责表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-30 09:31 ]
 * @Description: [ 公司岗位分类岗位职责表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PositionDutyVO {

    /**
     * id
     */
    private String id;
    /**
     * 所属体系
     */
    private String hierarchy;

    /**
     * 业务方向分类
     */
    private String businessDirection;

    /**
     * 业务方向-科目
     */
    private String subject;

    /**
     * 所属类别
     */
    private String category;

    /**
     * 岗位
     */
    private String position;

    /**
     * 岗位描述
     */
    private String positionDescribe;

    /**
     * 岗位要求
     */
    private String positionRequire;

    /**
     * 招聘要求
     */
    private String recruitRequire;

    /**
     * 面试试题
     */
    private Boolean interviewQuestion;

    /**
     * 备注
     */
    private String remark;

    /**
     * 面试官
     */
    private String interviewer;

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

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getBusinessDirection() {
        return businessDirection;
    }

    public void setBusinessDirection(String businessDirection) {
        this.businessDirection = businessDirection;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPositionDescribe() {
        return positionDescribe;
    }

    public void setPositionDescribe(String positionDescribe) {
        this.positionDescribe = positionDescribe;
    }

    public String getPositionRequire() {
        return positionRequire;
    }

    public void setPositionRequire(String positionRequire) {
        this.positionRequire = positionRequire;
    }

    public String getRecruitRequire() {
        return recruitRequire;
    }

    public void setRecruitRequire(String recruitRequire) {
        this.recruitRequire = recruitRequire;
    }

    public Boolean getInterviewQuestion() {
        return interviewQuestion;
    }

    public void setInterviewQuestion(Boolean interviewQuestion) {
        this.interviewQuestion = interviewQuestion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}