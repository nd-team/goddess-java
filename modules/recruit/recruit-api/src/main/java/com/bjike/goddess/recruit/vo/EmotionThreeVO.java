package com.bjike.goddess.recruit.vo;

/**
 * 情感标签三级表现层对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:52 ]
 * @Description: [ 情感标签三级表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EmotionThreeVO {

    /**
     * id
     */
    private String id;
    /**
     * 标签名称
     */
    private String labelName;


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
}