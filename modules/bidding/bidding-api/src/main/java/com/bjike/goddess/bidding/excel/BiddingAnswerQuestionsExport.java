package com.bjike.goddess.bidding.excel;


import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 投标答疑问题记录表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:54:11.899 ]
 * @Description: [ 投标答疑问题记录表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BiddingAnswerQuestionsExport {
    /**
     * 编号
     */
    @ExcelHeader(name = "编号",notNull = true)
    private String biddingNumber;
    /**
     * 项目名称
     */
    @ExcelHeader(name = "项目名称" ,notNull = true)
    private String projectName;

    /**
     * 答疑准备人
     */
    @ExcelHeader(name = "答疑准备人" ,notNull = true)
    private String peopleAnswerQuestions;

    /**
     * 答疑时间
     */
    @ExcelHeader(name = "答疑时间" ,notNull = true)
    private String officeHour;

    /**
     * 解答单位
     */
    @ExcelHeader(name = "解答单位" ,notNull = true)
    private String answersUnit;

    /**
     * 问题
     */
    @ExcelHeader(name = "问题" ,notNull = true)
    private String problem;

    /**
     * 回复
     */
    @ExcelHeader(name = "回复" ,notNull = true)
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