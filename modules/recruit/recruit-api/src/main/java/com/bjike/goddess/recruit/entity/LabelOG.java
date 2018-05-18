package com.bjike.goddess.recruit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 对赌标签
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:23 ]
 * @Description: [ 对赌标签 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "recruit_labelog")
public class LabelOG extends BaseEntity {

    /**
     * 标签名称
     */
    @Column(name = "labelName",  columnDefinition = "VARCHAR(255)   COMMENT '标签名称'")
    private String labelName;

    /**
     * 标签说明
     */
    @Column(name = "labelExplain",  columnDefinition = "VARCHAR(255)   COMMENT '标签说明'")
    private String labelExplain;


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