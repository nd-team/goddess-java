package com.bjike.goddess.feedback.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 问题受理表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 06:12 ]
 * @Description: [ 问题受理表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "feedback_problemaccept")
public class ProblemAccept extends BaseEntity {

    /**
     * 问题受理编号(对内)
     */
    @Column(name = "acceptNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题受理编号(对内)'")
    private String acceptNum;

    /**
     * 问题受理所属部门
     */
    @Column(name = "acceptDepartment", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题受理所属部门'")
    private String acceptDepartment;

    /**
     * 问题受理所属模块
     */
    @Column(name = "acceptModule", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题受理所属模块'")
    private String acceptModule;

    /**
     * 问题受理人
     */
    @Column(name = "acceptPerson", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题受理人'")
    private String acceptPerson;

    /**
     * 问题跟进处理计划完成时间
     */
    @Column(name = "acceptTime", nullable = false, columnDefinition = "DATETIME   COMMENT '问题跟进处理计划完成时间'")
    private LocalDateTime acceptTime;

    /**
     * 意见收集完成时间
     */
    @Column(name = "ideaTime", nullable = false, columnDefinition = "DATETIME   COMMENT '意见收集完成时间'")
    private LocalDateTime ideaTime;

    /**
     * 问题反馈模块
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "problemFeedback_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '问题反馈模块'")
    private ProblemFeedback problemFeedback;


    public ProblemFeedback getProblemFeedback() {
        return problemFeedback;
    }

    public void setProblemFeedback(ProblemFeedback problemFeedback) {
        this.problemFeedback = problemFeedback;
    }

    public String getAcceptNum() {
        return acceptNum;
    }

    public void setAcceptNum(String acceptNum) {
        this.acceptNum = acceptNum;
    }

    public String getAcceptDepartment() {
        return acceptDepartment;
    }

    public void setAcceptDepartment(String acceptDepartment) {
        this.acceptDepartment = acceptDepartment;
    }

    public String getAcceptModule() {
        return acceptModule;
    }

    public void setAcceptModule(String acceptModule) {
        this.acceptModule = acceptModule;
    }

    public String getAcceptPerson() {
        return acceptPerson;
    }

    public void setAcceptPerson(String acceptPerson) {
        this.acceptPerson = acceptPerson;
    }

    public LocalDateTime getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(LocalDateTime acceptTime) {
        this.acceptTime = acceptTime;
    }

    public LocalDateTime getIdeaTime() {
        return ideaTime;
    }

    public void setIdeaTime(LocalDateTime ideaTime) {
        this.ideaTime = ideaTime;
    }
}