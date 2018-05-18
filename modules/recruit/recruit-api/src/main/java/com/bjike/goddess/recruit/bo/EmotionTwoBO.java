package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.Set;

/**
 * 情感标签二级业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:56 ]
 * @Description: [ 情感标签二级业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EmotionTwoBO extends BaseBO {

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 三级标签
     */
    private Set<EmotionThreeBO> emotionThreeBOS;


    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Set<EmotionThreeBO> getEmotionThreeBOS() {
        return emotionThreeBOS;
    }

    public void setEmotionThreeBOS(Set<EmotionThreeBO> emotionThreeBOS) {
        this.emotionThreeBOS = emotionThreeBOS;
    }
}