package com.bjike.goddess.progressmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 进度表表头对应值的行标记
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-20 03:27 ]
 * @Description: [ 进度表表头对应值的行标记 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "progressmanage_tableheadrowsign")
public class TableHeadRowSign extends BaseEntity {

    /**
     * 进度表
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "progressTable_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '进度表'")
    private ProgressTable progressTable;

    /**
     * 表头对应值
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "tableHeadRowSign")
    private Set<TableHeadValue> tableHeadValueSet = new HashSet<TableHeadValue>();

    public ProgressTable getProgressTable() {
        return progressTable;
    }

    public void setProgressTable(ProgressTable progressTable) {
        this.progressTable = progressTable;
    }

    public Set<TableHeadValue> getTableHeadValueSet() {
        return tableHeadValueSet;
    }

    public void setTableHeadValueSet(Set<TableHeadValue> tableHeadValueSet) {
        this.tableHeadValueSet = tableHeadValueSet;
    }

}