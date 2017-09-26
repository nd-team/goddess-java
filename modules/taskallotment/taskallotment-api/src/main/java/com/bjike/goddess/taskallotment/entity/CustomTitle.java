package com.bjike.goddess.taskallotment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.taskallotment.enums.TitleType;

import javax.persistence.*;
import javax.persistence.Table;


/**
 * 自定义字段
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 02:35 ]
 * @Description: [ 自定义字段 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "taskallotment_customtitle")
public class CustomTitle extends BaseEntity {

    /**
     * 标题下标
     */
    @Column(name = "titleIndex", nullable = false, columnDefinition = "INT(11)   COMMENT '标题下标'")
    private Integer titleIndex;
    /**
     * 字段名
     */
    @Column(name = "title", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '字段名'")
    private String title;

    /**
     * 字段内容
     */
    @Column(name = "content",columnDefinition = "VARCHAR(255)   COMMENT '字段内容'")
    private String content;

    /**
     * 是否必填选项
     */
    @Column(name = "mandatory", nullable = false, columnDefinition = "TINYINT(1)   COMMENT '是否必填选项'")
    private Boolean mandatory;

    /**
     * 字段类型
     */
    @Column(name = "titleType", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '字段类型'")
    private TitleType titleType;

    /**
     * 任务节点信息
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taskNode_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '任务节点信息'")
    private TaskNode taskNode;

    public Integer getTitleIndex() {
        return titleIndex;
    }

    public void setTitleIndex(Integer titleIndex) {
        this.titleIndex = titleIndex;
    }

    public TitleType getTitleType() {
        return titleType;
    }

    public void setTitleType(TitleType titleType) {
        this.titleType = titleType;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public TaskNode getTaskNode() {
        return taskNode;
    }

    public void setTaskNode(TaskNode taskNode) {
        this.taskNode = taskNode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}