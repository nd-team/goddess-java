package com.bjike.goddess.progressmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 进度表表头对应Value
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-19 04:48 ]
 * @Description: [ 进度表表头对应Value ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "progressmanage_tableheadvalue")
public class TableHeadValue extends BaseEntity {


    /**
     * 表头
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tableHead_id", referencedColumnName = "id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '表头'")
    private TableHead tableHead;

    /**
     * 行标记
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tableHeadRowSign_id", referencedColumnName = "id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '行标记'")
    private TableHeadRowSign tableHeadRowSign;

    /**
     * 值
     */
    @Column(name = "value", columnDefinition = "VARCHAR(255)   COMMENT '值'")
    private String value;


    public TableHead getTableHead() {
        return tableHead;
    }

    public void setTableHead(TableHead tableHead) {
        this.tableHead = tableHead;
    }

    public TableHeadRowSign getTableHeadRowSign() {
        return tableHeadRowSign;
    }

    public void setTableHeadRowSign(TableHeadRowSign tableHeadRowSign) {
        this.tableHeadRowSign = tableHeadRowSign;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}