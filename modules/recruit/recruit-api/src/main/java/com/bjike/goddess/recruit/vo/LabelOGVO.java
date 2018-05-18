package com.bjike.goddess.recruit.vo;

/**
 * 对赌标签表现层对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:23 ]
 * @Description: [ 对赌标签表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LabelOGVO {

    /**
     * id
     */
    private String id;
    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 标签说明
     */
    private String labelExplain;


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

    public String getLabelExplain() {
        return labelExplain;
    }

    public void setLabelExplain(String labelExplain) {
        this.labelExplain = labelExplain;
    }
}