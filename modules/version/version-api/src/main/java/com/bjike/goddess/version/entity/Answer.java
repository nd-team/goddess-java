package com.bjike.goddess.version.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 答案
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:10 ]
 * @Description: [ 答案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "version_answer")
public class Answer extends BaseEntity {

    /**
     * 参考答案
     */
    @Column(name = "answer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '参考答案'")
    private String answer;

    /**
     * 提供人
     */
    @Column(name = "provider", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '提供人'")
    private String provider;

    /**
     * 问题信息
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "help_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '问题信息'")
    private Help help;

    public Help getHelp() {
        return help;
    }

    public void setHelp(Help help) {
        this.help = help;
    }

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
}