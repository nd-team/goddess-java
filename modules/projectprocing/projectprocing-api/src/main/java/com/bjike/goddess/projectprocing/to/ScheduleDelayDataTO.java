package com.bjike.goddess.projectprocing.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 进度延后
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:19 ]
 * @Description: [ 进度延后 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ScheduleDelayDataTO extends BaseTO {

    /**
     * 节点
     */
    @NotBlank(message = "节点不能为空",groups = {ADD.class})
    private String node;

    /**
     * 节点id
     */
    @NotBlank(message = "节点id不能为空",groups = {ADD.class})
    private String nodeId;
    /**
     * 修改内容
     */
    @NotBlank(message = "修改内容不能为空",groups = {ADD.class})
    private String nodeContent;

    /**
     * 问题描述（修改原因）
     */
    @NotBlank(message = "问题描述不能为空",groups = {ADD.class})
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

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeContent() {
        return nodeContent;
    }

    public void setNodeContent(String nodeContent) {
        this.nodeContent = nodeContent;
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