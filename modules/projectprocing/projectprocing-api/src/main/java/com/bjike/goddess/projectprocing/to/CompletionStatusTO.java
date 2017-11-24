package com.bjike.goddess.projectprocing.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 填写完成情况
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:19 ]
 * @Description: [ 填写完成情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompletionStatusTO extends BaseTO {

    /**
     * 是否完成
     */
    @NotNull(message = "是否完成不能为空",groups = {ADD.class})
    private Boolean complete;
    /**
     * 时间
     */
    private String dateTime;

    /**
     * 问题描述（修改原因）
     */
    private String problemDescription;

    /**
     * 问题类型
     */
    private String problemType;

    /**
     * 协助人
     */
    private String assistPeoper;

    /**
     * 协助内容
     */
    private String assistContent;

    /**
     * 协助完成时间
     */
    private String assistDate;

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getAssistPeoper() {
        return assistPeoper;
    }

    public void setAssistPeoper(String assistPeoper) {
        this.assistPeoper = assistPeoper;
    }

    public String getAssistContent() {
        return assistContent;
    }

    public void setAssistContent(String assistContent) {
        this.assistContent = assistContent;
    }

    public String getAssistDate() {
        return assistDate;
    }

    public void setAssistDate(String assistDate) {
        this.assistDate = assistDate;
    }
}