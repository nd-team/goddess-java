package com.bjike.goddess.qualifications.vo;

/**
 * 资质办理计划阶段实施工作记录表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 05:00 ]
 * @Description: [ 资质办理计划阶段实施工作记录表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HandlePlanImplementVO {

    /**
     * id
     */
    private String id;
    /**
     * 办理计划阶段
     */
    private String stage_id;

    /**
     * 实施内容
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

    /**
     * 实施进度
     */
    private Integer setbacks;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStage_id() {
        return stage_id;
    }

    public void setStage_id(String stage_id) {
        this.stage_id = stage_id;
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

    public Integer getSetbacks() {
        return setbacks;
    }

    public void setSetbacks(Integer setbacks) {
        this.setbacks = setbacks;
    }
}