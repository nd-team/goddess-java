package com.bjike.goddess.qualifications.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 资质办理计划阶段划分
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:55 ]
 * @Description: [ 资质办理计划阶段划分 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "qualifications_handle_plan_stage")
public class HandlePlanStage extends BaseEntity {

    /**
     * 办理计划
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "plan_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '办理计划'")
    private QualificationsHandlePlan plan;

    /**
     * 阶段划分
     */
    @Column(name = "stage", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '阶段划分'")
    private String stage;

    /**
     * 准备内容
     */
    @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '准备内容'")
    private String content;

    /**
     * 完成时间
     */
    @Column(name = "finishTime", nullable = false, columnDefinition = "DATE   COMMENT '完成时间'")
    private LocalDate finishTime;

    /**
     * 协助人员
     */
    @Column(name = "collaborator", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '协助人员'")
    private String collaborator;

    /**
     * 问题记录
     */
    @Column(name = "question", columnDefinition = "VARCHAR(255)   COMMENT '问题记录'")
    private String question;


    public QualificationsHandlePlan getPlan() {
        return plan;
    }

    public void setPlan(QualificationsHandlePlan plan) {
        this.plan = plan;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDate finishTime) {
        this.finishTime = finishTime;
    }

    public String getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(String collaborator) {
        this.collaborator = collaborator;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}