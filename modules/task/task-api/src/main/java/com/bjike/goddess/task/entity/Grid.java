package com.bjike.goddess.task.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-15 17:35]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@javax.persistence.Table(name = "task_grid")
public class Grid extends BaseEntity{
    /**
     * 行
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "rid", columnDefinition = "VARCHAR(36) COMMENT '行' ",nullable = false)
    private Row row;
    /**
     * 列
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "fid", columnDefinition = "VARCHAR(36) COMMENT '列' ",nullable = false)
    private Field field;
    /**
     * 值
     */
    @OneToOne(optional = false, cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JoinColumn(name="vid",nullable = false)
    @JSONField(serialize = false)
    private Val val;

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Val getVal() {
        return val;
    }

    public void setVal(Val val) {
        this.val = val;
    }
}
