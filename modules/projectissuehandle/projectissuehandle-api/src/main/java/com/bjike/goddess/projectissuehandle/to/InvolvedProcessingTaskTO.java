package com.bjike.goddess.projectissuehandle.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 参与处理人员的任务分配
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 05:02 ]
 * @Description: [ 参与处理人员的任务分配 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InvolvedProcessingTaskTO extends BaseTO {
    /**
     * 项目问题编号
     */
    private String projectNum;

    /**
     * 内部项目名称
     */
    private String internalProjectName;

    /**
     * 处理人员
     */
    @NotBlank(message = "处理人员不能为空",groups = {ADD.class, EDIT.class})
    private String handler;

    /**
     * 每日计划
     */
    @NotBlank(message = "每日计划不能为空",groups = {ADD.class, EDIT.class})
    private String dailyPlan;

    /**
     * 临时任务
     */
    @NotBlank(message = "临时任务不能为空",groups = {ADD.class, EDIT.class})
    private String temporaryTask;

    /**
     * 实际完成情况
     */
    @NotBlank(message = "实际完成情况不能为空",groups = {ADD.class, EDIT.class})
    private String actualCompletion;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getInternalProjectName() {
        return internalProjectName;
    }

    public void setInternalProjectName(String internalProjectName) {
        this.internalProjectName = internalProjectName;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getDailyPlan() {
        return dailyPlan;
    }

    public void setDailyPlan(String dailyPlan) {
        this.dailyPlan = dailyPlan;
    }

    public String getTemporaryTask() {
        return temporaryTask;
    }

    public void setTemporaryTask(String temporaryTask) {
        this.temporaryTask = temporaryTask;
    }

    public String getActualCompletion() {
        return actualCompletion;
    }

    public void setActualCompletion(String actualCompletion) {
        this.actualCompletion = actualCompletion;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}