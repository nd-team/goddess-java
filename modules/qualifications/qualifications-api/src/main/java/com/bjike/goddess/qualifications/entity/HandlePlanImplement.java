package com.bjike.goddess.qualifications.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 资质办理计划阶段实施工作记录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 05:00 ]
 * @Description: [ 资质办理计划阶段实施工作记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "qualifications_handle_plan_implement")
public class HandlePlanImplement extends BaseEntity {

    /**
     * 办理计划阶段
     */
    @Column(name = "stage_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '办理计划阶段'")
    private HandlePlanStage stage;

    /**
     * 实施内容
     */
    @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '实施内容'")
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
    @Column(name = "question", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题记录'")
    private String question;

    /**
     * 实施进度
     */
    @Column(name = "setbacks", nullable = false, columnDefinition = "INT(11)   COMMENT '实施进度'")
    private Integer setbacks;


    public HandlePlanStage getStage() {
        return stage;
    }

    public void setStage(HandlePlanStage stage) {
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

    public Integer getSetbacks() {
        return setbacks;
    }

    public void setSetbacks(Integer setbacks) {
        this.setbacks = setbacks;
    }
}