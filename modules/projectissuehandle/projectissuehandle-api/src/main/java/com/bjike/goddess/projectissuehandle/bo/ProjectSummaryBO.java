package com.bjike.goddess.projectissuehandle.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 项目中问题受理和处理业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-09 10:17 ]
 * @Description: [ 项目中问题受理和处理业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectSummaryBO extends BaseBO {

    /**
     * 项目组
     */
    private String project;

    /**
     * 问题总规模
     */
    private Integer problemTotalNum;

    /**
     * 已完成
     */
    private Integer finishNum;

    /**
     * 未完成
     */
    private Integer unFinishNum;

    /**
     * 人员类
     */
    private Integer personnelClassNum;

    /**
     * 设备类
     */
    private Integer deviveClassNum;

    /**
     * 派工类
     */
    private Integer dispatchingClassNum;

    /**
     * 交付类
     */
    private Integer deliverClassNum;

    /**
     * 培训类
     */
    private Integer trainingClassNum;

    /**
     * 进度类
     */
    private Integer progressClassNum;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Integer getProblemTotalNum() {
        return problemTotalNum;
    }

    public void setProblemTotalNum(Integer problemTotalNum) {
        this.problemTotalNum = problemTotalNum;
    }

    public Integer getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(Integer finishNum) {
        this.finishNum = finishNum;
    }

    public Integer getUnFinishNum() {
        return unFinishNum;
    }

    public void setUnFinishNum(Integer unFinishNum) {
        this.unFinishNum = unFinishNum;
    }

    public Integer getPersonnelClassNum() {
        return personnelClassNum;
    }

    public void setPersonnelClassNum(Integer personnelClassNum) {
        this.personnelClassNum = personnelClassNum;
    }

    public Integer getDeviveClassNum() {
        return deviveClassNum;
    }

    public void setDeviveClassNum(Integer deviveClassNum) {
        this.deviveClassNum = deviveClassNum;
    }

    public Integer getDispatchingClassNum() {
        return dispatchingClassNum;
    }

    public void setDispatchingClassNum(Integer dispatchingClassNum) {
        this.dispatchingClassNum = dispatchingClassNum;
    }

    public Integer getDeliverClassNum() {
        return deliverClassNum;
    }

    public void setDeliverClassNum(Integer deliverClassNum) {
        this.deliverClassNum = deliverClassNum;
    }

    public Integer getTrainingClassNum() {
        return trainingClassNum;
    }

    public void setTrainingClassNum(Integer trainingClassNum) {
        this.trainingClassNum = trainingClassNum;
    }

    public Integer getProgressClassNum() {
        return progressClassNum;
    }

    public void setProgressClassNum(Integer progressClassNum) {
        this.progressClassNum = progressClassNum;
    }
}