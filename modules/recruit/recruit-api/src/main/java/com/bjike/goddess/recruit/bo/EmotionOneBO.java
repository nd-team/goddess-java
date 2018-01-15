package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.recruit.entity.EmotionTwo;

import java.util.Set;

/**
 * 情感标签二级业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:57 ]
 * @Description: [ 情感标签二级业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EmotionOneBO extends BaseBO {

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 二级标签
     */
    private Set<EmotionTwoBO> emotionTwoBOS;


    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Set<EmotionTwoBO> getEmotionTwoBOS() {
        return emotionTwoBOS;
    }

    public void setEmotionTwoBOS(Set<EmotionTwoBO> emotionTwoBOS) {
        this.emotionTwoBOS = emotionTwoBOS;
    }
}