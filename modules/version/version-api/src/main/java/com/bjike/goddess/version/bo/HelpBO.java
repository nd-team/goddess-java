package com.bjike.goddess.version.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 帮助与解答业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:07 ]
 * @Description: [ 帮助与解答业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HelpBO extends BaseBO {

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

//    /**
//     * 答案集合
//     */
//    private List<AnswerBO> answers;
//
//    public List<AnswerBO> getAnswers() {
//        return answers;
//    }
//
//    public void setAnswers(List<AnswerBO> answers) {
//        this.answers = answers;
//    }

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