package com.bjike.goddess.system.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * @Author: [xiazhili]
 * @Date: [2017-09-30 11:13]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class QuestionBO1 extends BaseBO {
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
    private List<AuswerBO> auswerBOS;

    public List<AuswerBO> getAuswerBOS() {
        return auswerBOS;
    }

    public void setAuswerBOS(List<AuswerBO> auswerBOS) {
        this.auswerBOS = auswerBOS;
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
