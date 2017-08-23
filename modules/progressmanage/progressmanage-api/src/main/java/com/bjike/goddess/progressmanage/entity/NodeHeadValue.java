package com.bjike.goddess.progressmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 节点表头值
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-26 09:54 ]
 * @Description: [ 节点表头值 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "progressmanage_nodeheadvalue")
public class NodeHeadValue extends BaseEntity {

    /**
     * 节点表头
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nodeHead_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '节点表头'")
    private NodeHead nodeHead;

    /**
     * 行标记
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nodeHeadRowSign_id", referencedColumnName = "id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '行标记'")
    private NodeHeadRowSign nodeHeadRowSign;

    /**
     * 值
     */
    private String value;


    public NodeHead getNodeHead() {
        return nodeHead;
    }

    public void setNodeHead(NodeHead nodeHead) {
        this.nodeHead = nodeHead;
    }

    public NodeHeadRowSign getNodeHeadRowSign() {
        return nodeHeadRowSign;
    }

    public void setNodeHeadRowSign(NodeHeadRowSign nodeHeadRowSign) {
        this.nodeHeadRowSign = nodeHeadRowSign;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}