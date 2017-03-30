package com.bjike.goddess.businessinteraction.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 留言
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:11 ]
 * @Description: [ 留言 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessinteraction_leavingmessage")
public class LeavingMessage extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 联系方式
     */
    @Column(name = "contactWay",  columnDefinition = "VARCHAR(255)   COMMENT '联系方式'")
    private String contactWay;

    /**
     * 留言内容
     */
    @Column(name = "contactContent", columnDefinition = "VARCHAR(255)   COMMENT '留言内容'")
    private String contactContent;

    /**
     * 互动联系信息
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "interactionRelation_id",  columnDefinition = "VARCHAR(36)   COMMENT '互动联系信息'")
    private InteractionRelation interactionRelation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getContactContent() {
        return contactContent;
    }

    public void setContactContent(String contactContent) {
        this.contactContent = contactContent;
    }

    public InteractionRelation getInteractionRelation() {
        return interactionRelation;
    }

    public void setInteractionRelation(InteractionRelation interactionRelation) {
        this.interactionRelation = interactionRelation;
    }
}