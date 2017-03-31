package com.bjike.goddess.qualifications.vo;

/**
 * 资质办理计划阶段划分表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:55 ]
 * @Description: [ 资质办理计划阶段划分表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HandlePlanStageVO {

    /**
     * id
     */
    private String id;
    /**
     * 办理计划
     */
    private String plan_id;

    /**
     * 阶段划分
     */
    private String stage;

    /**
     * 准备内容
     */
    private String content;

    /**
     * 完成时间
     */
    private String finishTime;

    /**
     * 协助人员
     */
    private String collaborator;

    /**
     * 问题记录
     */
    private String question;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
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

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
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