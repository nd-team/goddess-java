package com.bjike.goddess.task.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.task.enums.CollectSuitation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 自定义汇总子表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-18 11:09 ]
 * @Description: [ 自定义汇总子表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "task_customizeson")
public class CustomizeSon extends BaseEntity {

    /**
     * 自定义汇总id
     */
    @Column(name = "customizeId", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '自定义汇总id'")
    private String customizeId;

    /**
     * 汇总字段
     */
    @Column(name = "title", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '汇总字段'")
    private String title;

    /**
     * 汇总条件
     */
    @Column(name = "collectSuitation", nullable = false, columnDefinition = "TINYINT(2)  COMMENT '汇总条件'")
    private CollectSuitation collectSuitation;

    /**
     * 任务类型
     * 2018-01-05
     * 内容
     */
    @Column(name = "type", nullable = false, columnDefinition = "TEXT   COMMENT '任务类型'")
    private String type;

    /**
     * 汇总表头字段
     */
    @Column(name = "tableTitle", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '汇总表头字段'")
    private String tableTitle;

    /**
     * 字段下标
     */
    @Column(name = "titleIndex", nullable = false, columnDefinition = "INT(11)   COMMENT '字段下标'")
    private Integer titleIndex;

    public Integer getTitleIndex() {
        return titleIndex;
    }

    public void setTitleIndex(Integer titleIndex) {
        this.titleIndex = titleIndex;
    }

    public String getCustomizeId() {
        return customizeId;
    }

    public void setCustomizeId(String customizeId) {
        this.customizeId = customizeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CollectSuitation getCollectSuitation() {
        return collectSuitation;
    }

    public void setCollectSuitation(CollectSuitation collectSuitation) {
        this.collectSuitation = collectSuitation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTableTitle() {
        return tableTitle;
    }

    public void setTableTitle(String tableTitle) {
        this.tableTitle = tableTitle;
    }
}