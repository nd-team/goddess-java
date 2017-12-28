package com.bjike.goddess.version.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * @Author: [chenjunhao]
 * @Date: [2017-08-15 11:24]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class HelpBO1 extends BaseBO{
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
    private List<AnswerBO> answers;

    public List<AnswerBO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerBO> answers) {
        this.answers = answers;
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
