package com.bjike.goddess.progressmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


/**
 * 节点表头对应值的行标记
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-26 10:01 ]
 * @Description: [ 节点表头对应值的行标记 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "progressmanage_nodeheadrowsign")
public class NodeHeadRowSign extends BaseEntity {

    /**
     * 进度节点
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "progressNode_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '进度节点'")
    private ProgressNode progressNode;

    /**
     * 表头对应值
     */
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.EAGER, mappedBy = "nodeHeadRowSign")
    private Set<NodeHeadValue> nodeHeadValueSet;

    @Embedded
    private List<NodeHeadValue> nodeHeadValueList;


    public ProgressNode getProgressNode() {
        return progressNode;
    }

    public void setProgressNode(ProgressNode progressNode) {
        this.progressNode = progressNode;
    }

    public Set<NodeHeadValue> getNodeHeadValueSet() {
        return nodeHeadValueSet;
    }

    public void setNodeHeadValueSet(Set<NodeHeadValue> nodeHeadValueSet) {
        this.nodeHeadValueSet = nodeHeadValueSet;
    }

    public List<NodeHeadValue> getNodeHeadValueList() {
        return nodeHeadValueList;
    }

    public void setNodeHeadValueList(List<NodeHeadValue> nodeHeadValueList) {
        this.nodeHeadValueList = nodeHeadValueList;
    }
}