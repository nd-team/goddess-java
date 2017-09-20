package com.bjike.goddess.task.to;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 问题受理对象传输
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-20 11:46]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AcceptTO extends BaseTO{
    /**
     * 问题id
     */
    @NotBlank(message = "问题id不能为空", groups = {ADD.class, EDIT.class})

    private String problemId;

    /**
     * 问题受理所属部门
     */
    @NotBlank(message = "问题受理所属部门不能为空", groups = {ADD.class, EDIT.class})

    private String acceptDepartment;
    /**
     * 问题跟进处理计划完成时间
     */
    @NotBlank(message = "问题跟进处理计划完成时间不能为空", groups = {ADD.class, EDIT.class})

    private String planFinishTime;
    /**
     * 问题跟进处理实际完成时间
     */
    @NotBlank(message = "问题跟进处理实际完成时间不能为空", groups = {ADD.class, EDIT.class})

    private String finishTime;
    /**
     * 问题处理结果
     */
    @NotBlank(message = "问题处理结果不能为空", groups = {ADD.class, EDIT.class})

    private String result;
    /**
     * 是否闭环
     */
    @NotNull(message = "是否闭环不能为空", groups = {ADD.class, EDIT.class})

    private Boolean closedLoop;
    /**
     * 是否需要协调
     */
    @NotNull(message = "是否需要协调不能为空", groups = {ADD.class, EDIT.class})
    private Boolean needGive;
    /**
     * 协调结果
     */
    @NotBlank(message = "协调结果不能为空", groups = {ADD.class, EDIT.class})

    private String giveResult;

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getAcceptDepartment() {
        return acceptDepartment;
    }

    public void setAcceptDepartment(String acceptDepartment) {
        this.acceptDepartment = acceptDepartment;
    }

    public String getPlanFinishTime() {
        return planFinishTime;
    }

    public void setPlanFinishTime(String planFinishTime) {
        this.planFinishTime = planFinishTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Boolean getClosedLoop() {
        return closedLoop;
    }

    public void setClosedLoop(Boolean closedLoop) {
        this.closedLoop = closedLoop;
    }

    public Boolean getNeedGive() {
        return needGive;
    }

    public void setNeedGive(Boolean needGive) {
        this.needGive = needGive;
    }

    public String getGiveResult() {
        return giveResult;
    }

    public void setGiveResult(String giveResult) {
        this.giveResult = giveResult;
    }
}
