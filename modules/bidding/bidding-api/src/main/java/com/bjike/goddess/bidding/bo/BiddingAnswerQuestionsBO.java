package com.bjike.goddess.bidding.bo;

import com.bjike.goddess.common.api.bo.BaseBO;


/**
 * 投标答疑问题记录业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:54:11.889 ]
 * @Description: [ 投标答疑问题记录业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BiddingAnswerQuestionsBO extends BaseBO {
    /**
     * 编号
     */
    private String biddingNumber;
    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 答疑准备人
     */
    private String peopleAnswerQuestions;

    /**
     * 答疑时间
     */
    private String officeHour;

    /**
     * 解答单位
     */
    private String answersUnit;

    /**
     * 问题
     */
    private String problem;

    /**
     * 回复
     */
    private String reply;

    public String getBiddingNumber() {
        return biddingNumber;
    }

    public void setBiddingNumber(String biddingNumber) {
        this.biddingNumber = biddingNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPeopleAnswerQuestions() {
        return peopleAnswerQuestions;
    }

    public void setPeopleAnswerQuestions(String peopleAnswerQuestions) {
        this.peopleAnswerQuestions = peopleAnswerQuestions;
    }

    public String getOfficeHour() {
        return officeHour;
    }

    public void setOfficeHour(String officeHour) {
        this.officeHour = officeHour;
    }

    public String getAnswersUnit() {
        return answersUnit;
    }

    public void setAnswersUnit(String answersUnit) {
        this.answersUnit = answersUnit;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}