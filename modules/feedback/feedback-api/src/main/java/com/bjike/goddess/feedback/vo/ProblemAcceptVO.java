package com.bjike.goddess.feedback.vo;

import com.bjike.goddess.feedback.bo.ProblemFeedbackBO;

/**
 * 问题受理表表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 06:12 ]
 * @Description: [ 问题受理表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblemAcceptVO {

    /**
     * id
     */
    private String id;
    /**
     * 问题受理编号(对内)
     */
    private String acceptNum;

    /**
     * 问题受理所属部门
     */
    private String acceptDepartment;

    /**
     * 问题受理所属模块
     */
    private String acceptModule;

    /**
     * 问题受理人
     */
    private String acceptPerson;

    /**
     * 问题跟进处理计划完成时间
     */
    private String acceptTime;

    /**
     * 意见收集完成时间
     */
    private String ideaTime;

    /**
     * 问题反馈模块
     */
    private ProblemFeedbackVO problemFeedbackVO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAcceptNum() {
        return acceptNum;
    }

    public void setAcceptNum(String acceptNum) {
        this.acceptNum = acceptNum;
    }

    public String getAcceptDepartment() {
        return acceptDepartment;
    }

    public void setAcceptDepartment(String acceptDepartment) {
        this.acceptDepartment = acceptDepartment;
    }

    public String getAcceptModule() {
        return acceptModule;
    }

    public void setAcceptModule(String acceptModule) {
        this.acceptModule = acceptModule;
    }

    public String getAcceptPerson() {
        return acceptPerson;
    }

    public void setAcceptPerson(String acceptPerson) {
        this.acceptPerson = acceptPerson;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getIdeaTime() {
        return ideaTime;
    }

    public void setIdeaTime(String ideaTime) {
        this.ideaTime = ideaTime;
    }

    public ProblemFeedbackVO getProblemFeedbackVO() {
        return problemFeedbackVO;
    }

    public void setProblemFeedbackVO(ProblemFeedbackVO problemFeedbackVO) {
        this.problemFeedbackVO = problemFeedbackVO;
    }
}