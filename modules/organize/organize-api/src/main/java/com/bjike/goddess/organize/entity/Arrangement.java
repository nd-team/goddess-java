package com.bjike.goddess.organize.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.*;

/**
 * 岗位层级
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午10:03]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Entity
@Table(name = "organize_arrangement")
public class Arrangement extends BaseEntity {

    /**
     * 编号
     */
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(24) COMMENT '编号'")
    private String serialNumber;

    /**
     * 层级
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '层级'", nullable = false)
    private String arrangement;

    /**
     * 描述
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '描述'")
    private String description;

    /**
     * 使用状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '岗位层级状态'", nullable = false, insertable = false)
    private Status status;

    /**
     * 上级层级
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(columnDefinition = "VARCHAR(36) COMMENT '上级层级'", name = "parent_id")
    private Arrangement parent;

    public Arrangement getParent() {
        return parent;
    }

    public void setParent(Arrangement parent) {
        this.parent = parent;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
