package com.bjike.goddess.qualifications.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 资质办理计划阶段划分
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:55 ]
 * @Description: [ 资质办理计划阶段划分 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HandlePlanStageTO extends BaseTO {

    /**
     * 办理计划
     */
    @NotBlank(message = "办理计划不能为空", groups = {ADD.class, EDIT.class})
    private String plan_id;

    /**
     * 阶段划分
     */
    @NotBlank(message = "阶段划分不能为空", groups = {ADD.class, EDIT.class})
    private String stage;

    /**
     * 准备内容
     */
    @NotBlank(message = "准备内容不能为空", groups = {ADD.class, EDIT.class})
    private String content;

    /**
     * 完成时间
     */
    @NotBlank(message = "完成时间不能为空", groups = {ADD.class, EDIT.class})
    private String finishTime;

    /**
     * 协助人员
     */
    @NotBlank(message = "协助人员不能为空", groups = {ADD.class, EDIT.class})
    private String collaborator;

    /**
     * 问题记录
     */
    @NotBlank(message = "问题记录不能为空", groups = {ADD.class, EDIT.class})
    private String question;


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