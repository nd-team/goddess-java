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
    @Column(name = "index", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '表头下标'")
    private Integer index;

    /**
     * 表头
     */
    @Column(name = "table", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '表头'")
    private String table;

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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
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