package com.bjike.goddess.task.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 汇总方案子表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-18 04:56 ]
 * @Description: [ 汇总方案子表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "task_schemeson")
public class SchemeSon extends BaseEntity {

    /**
     * 汇总方案id
     */
    @Column(name = "schemeId", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '汇总方案id'")
    private String schemeId;

    /**
     * 字段
     */
    @Column(name = "title", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '字段'")
    private String title;

    /**
     * 字段下标
     */
    @Column(name = "titleIndex", nullable = false, columnDefinition = "INT(11)   COMMENT '字段下标'")
    private Integer titleIndex;


    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTitleIndex() {
        return titleIndex;
    }

    public void setTitleIndex(Integer titleIndex) {
        this.titleIndex = titleIndex;
    }
}