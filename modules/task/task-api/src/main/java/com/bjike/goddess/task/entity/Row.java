package com.bjike.goddess.task.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.task.enums.ExecStatus;

import javax.persistence.*;
import java.util.Set;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-15 17:14]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@javax.persistence.Table(name = "task_row")
public class Row extends BaseEntity{
    /**
     * 所属表
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "tid", columnDefinition = "VARCHAR(36) COMMENT '所属表' ",nullable = false)
    private Table table;

    /**
     * 序列
     */
    @Column(columnDefinition = "TINYINT COMMENT '序列'")
    private Integer seq;

    /**
     * 执行状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '执行状态' ", nullable = false, insertable = false)
    private ExecStatus execStatus;

    /**
     * 行列表
     */
    @OneToMany(mappedBy = "row", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Grid> gridSet;

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public ExecStatus getExecStatus() {
        return execStatus;
    }

    public void setExecStatus(ExecStatus execStatus) {
        this.execStatus = execStatus;
    }

    public Set<Grid> getGridSet() {
        return gridSet;
    }

    public void setGridSet(Set<Grid> gridSet) {
        this.gridSet = gridSet;
    }
}
