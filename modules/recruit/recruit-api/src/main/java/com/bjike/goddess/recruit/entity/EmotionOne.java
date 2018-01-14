package com.bjike.goddess.recruit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.util.Set;


/**
 * 情感标签一级
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:57 ]
 * @Description: [ 情感标签一级 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "recruit_emotionone")
public class EmotionOne extends BaseEntity {

    /**
     * 标签名称
     */
    @Column(name = "labelName",  columnDefinition = "VARCHAR(255)   COMMENT '标签名称'")
    private String labelName;

    /**
     * 二级标签
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "emotionOne_id")
    private Set<EmotionTwo> emotionTwo;


    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Set<EmotionTwo> getEmotionTwo() {
        return emotionTwo;
    }

    public void setEmotionTwo(Set<EmotionTwo> emotionTwo) {
        this.emotionTwo = emotionTwo;
    }
}