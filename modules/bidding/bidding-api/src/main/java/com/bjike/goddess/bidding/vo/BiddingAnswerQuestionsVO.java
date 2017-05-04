package com.bjike.goddess.bidding.vo;


/**
 * 投标答疑问题记录表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:54:11.899 ]
 * @Description: [ 投标答疑问题记录表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BiddingAnswerQuestionsVO {
    /**
     * id
     */
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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