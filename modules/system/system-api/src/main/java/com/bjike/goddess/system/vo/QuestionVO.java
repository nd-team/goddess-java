package com.bjike.goddess.system.vo;

/**
 * 问题表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:48 ]
 * @Description: [ da表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class QuestionVO {

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