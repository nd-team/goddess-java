package com.bjike.goddess.system.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 答案
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:49 ]
 * @Description: [ 答案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "system_auswer")
public class Auswer extends BaseEntity {

    /**
     * 参考答案
     */
    @Column(name = "answer", columnDefinition = "VARCHAR(255)   COMMENT '参考答案'")
    private String answer;

    /**
     * 提供人
     */
    @Column(name = "provider", columnDefinition = "VARCHAR(255)   COMMENT '提供人'")
    private String provider;
    /**
     * 问题
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "question_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '答案'")
    private Question question;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}