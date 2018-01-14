package com.bjike.goddess.recruit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.util.Set;


/**
 * 情感标签二级
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:56 ]
 * @Description: [ 情感标签二级 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "recruit_emotiontwo")
public class EmotionTwo extends BaseEntity {

    /**
     * 标签名称
     */
    @Column(name = "labelName", columnDefinition = "VARCHAR(255)   COMMENT '标签名称'")
    private String labelName;

    /**
     * 三级标签
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "emotionTwo_id")
    private Set<EmotionThree> emotionThree;


    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Set<EmotionThree> getEmotionThree() {
        return emotionThree;
    }

    public void setEmotionThree(Set<EmotionThree> emotionThree) {
        this.emotionThree = emotionThree;
    }
}