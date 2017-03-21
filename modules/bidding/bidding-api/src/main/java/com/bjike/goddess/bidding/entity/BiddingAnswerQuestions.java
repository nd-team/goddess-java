package com.bjike.goddess.bidding.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 投标答疑问题记录
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:54:11.883 ]
 * @Description: [ 投标答疑问题记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "bidding_biddinganswerquestions")
public class BiddingAnswerQuestions extends BaseEntity {

    /**
     * 项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 答疑准备人
     */
    @Column(name = "peopleAnswerQuestions", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '答疑准备人'")
    private String peopleAnswerQuestions;

    /**
     * 答疑时间
     */
    @Column(name = "officeHour", nullable = false, columnDefinition = "DATE   COMMENT '答疑时间'")
    private LocalDate officeHour;

    /**
     *
     */
    @Column(name = "answersUnit", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT ''")
    private String answersUnit;

    /**
     * 问题
     */
    @Column(name = "problem", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题'")
    private String problem;

    /**
     * 回复
     */
    @Column(name = "reply", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '回复'")
    private String reply;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPeopleAnswerQuestions() {
        return peopleAnswerQuestions;
    }

    public void setPeopleAnswerQuestions(String peopleAnswerQuestions) {
        this.peopleAnswerQuestions = peopleAnswerQuestions;
    }

    public LocalDate getOfficeHour() {
        return officeHour;
    }

    public void setOfficeHour(LocalDate officeHour) {
        this.officeHour = officeHour;
    }

    public String getAnswersUnit() {
        return answersUnit;
    }

    public void setAnswersUnit(String answersUnit) {
        this.answersUnit = answersUnit;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}