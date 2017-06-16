package com.bjike.goddess.projectissuehandle.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 项目执行中的问题导出
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-20T20:13:56.346 ]
 * @Description: [ 项目执行中的问题导出 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InvolvedProcessingTaskExport extends BaseBO {
    /**
     * 项目问题编号
     */
    @ExcelHeader(name = "项目问题编号",notNull = true)
    private String projectNum;

    /**
     * 内部项目名称
     */
    @ExcelHeader(name = "内部项目名称",notNull = true)
    private String internalProjectName;


    /**
     * 处理人员
     */
    @ExcelHeader(name = "处理人员",notNull = true)
    private String handler;

    /**
     * 每日计划
     */
    @ExcelHeader(name = "每日计划",notNull = true)
    private String dailyPlan;

    /**
     * 临时任务
     */
    @ExcelHeader(name = "临时任务",notNull = true)
    private String temporaryTask;

    /**
     * 实际完成情况
     */
    @ExcelHeader(name = "实际完成情况",notNull = true)
    private String actualCompletion;

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
}