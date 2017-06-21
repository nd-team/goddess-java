package com.bjike.goddess.progressmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.progressmanage.enums.HeadType;

import javax.persistence.*;


/**
 * 进度节点表头
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 05:43 ]
 * @Description: [ 进度节点表头 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "progressmanage_nodehead", uniqueConstraints = {@UniqueConstraint(columnNames = {"progressNode_id", "headName"}),
        @UniqueConstraint(columnNames = {"progressNode_id", "sortIndex"})})
public class NodeHead extends BaseEntity {

    /**
     * 表头名
     */
    @Column(name = "headName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '表头名'")
    private String headName;

    /**
     * 所属节点
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "progressNode_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '所属节点'")
    private ProgressNode progressNode;

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

    public ProgressNode getProgressNode() {
        return progressNode;
    }

    public void setProgressNode(ProgressNode progressNode) {
        this.progressNode = progressNode;
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