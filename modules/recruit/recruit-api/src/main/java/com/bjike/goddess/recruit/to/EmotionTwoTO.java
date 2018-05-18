package com.bjike.goddess.recruit.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.recruit.entity.EmotionThree;

/**
 * 情感标签二级
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:56 ]
 * @Description: [ 情感标签二级 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EmotionTwoTO extends BaseTO {

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 三级标签
     */
    private EmotionThree emotionThree;


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