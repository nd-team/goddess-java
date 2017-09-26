package com.bjike.goddess.task.entity;

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
@javax.persistence.Table(name = "task_val")
public class Val extends BaseEntity {
    /**
     * 值
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(36) comment '值' ")
    private String val;

    @OneToOne( mappedBy = "val", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private Grid grid;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}
