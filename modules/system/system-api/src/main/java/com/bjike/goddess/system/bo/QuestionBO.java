package com.bjike.goddess.system.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 问题业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:48 ]
 * @Description: [ da业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class QuestionBO extends BaseBO {

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