package com.bjike.goddess.progressmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.progressmanage.enums.HeadType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * 进度表表头
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 05:08 ]
 * @Description: [ 进度表表头 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "progressmanage_tablehead", uniqueConstraints = {@UniqueConstraint(columnNames = {"progressTable_id", "headName"}),
        @UniqueConstraint(columnNames = {"progressTable_id", "sortIndex"})})
public class TableHead extends BaseEntity {

    /**
     * 进度表
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "progressTable_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '进度表'")
    private ProgressTable progressTable;

    /**
     * 表头名
     */
    @Column(name = "headName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '表头名'")
    private String headName;

    /**
     * 类型
     */
    @Column(name = "headType", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '类型'")
    private HeadType headType;

    /**
     * 是否必填
     */
    @Column(name = "is_required", nullable = false, columnDefinition = "TINYINT(1)  COMMENT '是否必填'")
    private Boolean required;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 顺序
     */
    @Column(name = "sortIndex", nullable = false, columnDefinition = "INT(11)   COMMENT '顺序'")
    private Integer sortIndex;


    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public HeadType getHeadType() {
        return headType;
    }

    public void setHeadType(HeadType headType) {
        this.headType = headType;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public ProgressTable getProgressTable() {
        return progressTable;
    }

    public void setProgressTable(ProgressTable progressTable) {
        this.progressTable = progressTable;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

}