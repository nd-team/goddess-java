package com.bjike.goddess.version.vo;

import java.util.List;

/**
 * 帮助与解答表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:07 ]
 * @Description: [ 帮助与解答表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HelpVO1 {

    /**
     * id
     */
    private String id;
    /**
     * 问题提出人
     */
    private String name;

    /**
     * 问题描述
     */
    private String rate;

    /**
     * 参考答案条数
     */
    private Long answerCount;

    /**
     * 答案集合
     */
    private List<AnswerVO> answers;

    public List<AnswerVO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerVO> answers) {
        this.answers = answers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Long getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Long answerCount) {
        this.answerCount = answerCount;
    }
}