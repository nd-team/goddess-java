package com.bjike.goddess.recruit.vo;

import com.bjike.goddess.recruit.entity.EmotionThree;

/**
 * 情感标签二级表现层对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:56 ]
 * @Description: [ 情感标签二级表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EmotionTwoVO {

    /**
     * id
     */
    private String id;
    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 三级标签
     */
    private EmotionThree emotionThree;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public EmotionThree getEmotionThree() {
        return emotionThree;
    }

    public void setEmotionThree(EmotionThree emotionThree) {
        this.emotionThree = emotionThree;
    }
}