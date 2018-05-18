package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 阶段表头数据
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 04:30 ]
 * @Description: [ 阶段表头数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_filesdata")
public class FilesData extends BaseEntity {

    /**
     * 日期id
     */
    @Column(name = "dateDataId", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '日期id'")
    private String dateDataId;

    /**
     * 表头下标
     */
    @Column(name = "tableIndex", nullable = true, columnDefinition = "INTEGER   COMMENT '表头下标'")
    private Integer tableIndex;

    /**
     * 表头
     */
    @Column(name = "tableName", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '表头'")
    private String tableName;

    /**
     * 内容
     */
    @Column(name = "context", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '内容'")
    private String context;

    /**
     * 父级id
     */
    @Column(name = "fatherId", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '父级id'")
    private String fatherId;


    public String getDateDataId() {
        return dateDataId;
    }

    public void setDateDataId(String dateDataId) {
        this.dateDataId = dateDataId;
    }

    public Integer getTableIndex() {
        return tableIndex;
    }

    public void setTableIndex(Integer tableIndex) {
        this.tableIndex = tableIndex;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }
}