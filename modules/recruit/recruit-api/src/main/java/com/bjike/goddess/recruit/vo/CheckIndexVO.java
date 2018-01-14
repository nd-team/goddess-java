package com.bjike.goddess.recruit.vo;

import com.bjike.goddess.recruit.entity.LabelOG;

/**
 * 考核指标表现层对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:26 ]
 * @Description: [ 考核指标表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CheckIndexVO {

    /**
     * id
     */
    private String id;
    /**
     * 考核名称
     */
    private String checkName;

    /**
     * 对赌标签
     */
    private LabelOG labelOG;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public LabelOG getLabelOG() {
        return labelOG;
    }

    public void setLabelOG(LabelOG labelOG) {
        this.labelOG = labelOG;
    }
}