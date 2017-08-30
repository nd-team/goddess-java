package com.bjike.goddess.staffing.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 人工成本计划子表详细
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 02:24 ]
 * @Description: [ 人工成本计划子表详细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffing_expendplansondetail")
public class ExpendPlanSonDetail extends BaseEntity {

    /**
     * 内容
     */
    @Column(name = "content", columnDefinition = "VARCHAR(255)   COMMENT '内容'")
    private String content;

    /**
     * 标题下标
     */
    @Column(name = "titleIndex", nullable = false, columnDefinition = "INT(11)   COMMENT '标题下标'")
    private Integer titleIndex;

    /**
     * 人工成本计划子表信息
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "son_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '人工成本计划子表信息'")
    private Son son;

    public Son getSon() {
        return son;
    }

    public void setSon(Son son) {
        this.son = son;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTitleIndex() {
        return titleIndex;
    }

    public void setTitleIndex(Integer titleIndex) {
        this.titleIndex = titleIndex;
    }
}