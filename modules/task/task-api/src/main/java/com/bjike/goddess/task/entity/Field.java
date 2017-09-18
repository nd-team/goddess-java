package com.bjike.goddess.task.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.DataType;

import javax.persistence.*;
import java.util.Set;

/**
 * 列
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-15 16:48]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@javax.persistence.Table(name = "task_field")
public class Field extends BaseEntity {
    /**
     * 列名称
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(36) comment '列名称' ")
    private String name;

    /**
     * 节点名称
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(36) comment '节点名称' ")
    private String node;

    /**
     * 所属表
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "tid", columnDefinition = "VARCHAR(36) COMMENT '所属表' ",nullable = false)
    private Table table;

    /**
     * 行列表
     */
    @OneToMany(mappedBy = "table", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Field> fieldSet;

    /**
     * 行列表
     */
    @OneToMany(mappedBy = "field", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Grid> gridSet;

    /**
     * 序列
     */
    @Column(columnDefinition = "TINYINT COMMENT '序列'")
    private Integer seq;
    /**
     * 是否必填
     */
    @Column(name = "is_need", columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '是否必填'", nullable = false, insertable = false)
    private boolean need;
    /**
     * 数据类型
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '执行状态' ", nullable = false, insertable = false)
    private DataType type;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public boolean isNeed() {
        return need;
    }

    public void setNeed(boolean need) {
        this.need = need;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Set<Field> getFieldSet() {
        return fieldSet;
    }

    public void setFieldSet(Set<Field> fieldSet) {
        this.fieldSet = fieldSet;
    }

    public Set<Grid> getGridSet() {
        return gridSet;
    }

    public void setGridSet(Set<Grid> gridSet) {
        this.gridSet = gridSet;
    }
}
