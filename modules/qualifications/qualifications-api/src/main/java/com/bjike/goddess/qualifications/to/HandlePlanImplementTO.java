package com.bjike.goddess.qualifications.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 资质办理计划阶段实施工作记录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 05:00 ]
 * @Description: [ 资质办理计划阶段实施工作记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HandlePlanImplementTO extends BaseTO {

    /**
     * 办理计划阶段
     */
    @NotBlank(message = "办理计划阶段不能为空", groups = {ADD.class, EDIT.class})
    private String stageId;

    /**
     * 实施内容
     */
    @NotBlank(message = "实施内容不能为空", groups = {ADD.class, EDIT.class})
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

    /**
     * 实施进度
     */
    @NotNull(message = "实施进度不能为空", groups = {ADD.class, EDIT.class})
    private Integer setbacks;


    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
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