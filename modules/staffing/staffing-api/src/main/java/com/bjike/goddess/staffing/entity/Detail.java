package com.bjike.goddess.staffing.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 人工成本计划详细
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 02:59 ]
 * @Description: [ 人工成本计划详细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffing_detail")
public class Detail extends BaseEntity {

    /**
     * 内容
     */
    @Column(name = "content",  columnDefinition = "VARCHAR(255)   COMMENT '内容'")
    private String content;

    /**
     * 列表标题下标
     */
    @Column(name = "listTitleIndex", columnDefinition = "INT(11)   COMMENT '列表标题下标'")
    private Integer listTitleIndex;

    /**
     * 人工成本计划信息
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "expendPlan_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '人工成本计划信息'")
    private ExpendPlan expendPlan;

    public ExpendPlan getExpendPlan() {
        return expendPlan;
    }

    public void setExpendPlan(ExpendPlan expendPlan) {
        this.expendPlan = expendPlan;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getListTitleIndex() {
        return listTitleIndex;
    }

    public void setListTitleIndex(Integer listTitleIndex) {
        this.listTitleIndex = listTitleIndex;
    }
}