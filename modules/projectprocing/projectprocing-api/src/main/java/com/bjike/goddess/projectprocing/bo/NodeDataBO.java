package com.bjike.goddess.projectprocing.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 节点数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:26 ]
 * @Description: [ 节点数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NodeDataBO extends BaseBO {

    /**
     * 节点
     */
    private String node;

    /**
     * 节点对应金额
     */
    private Double nodeAmount;

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Double getNodeAmount() {
        return nodeAmount;
    }

    public void setNodeAmount(Double nodeAmount) {
        this.nodeAmount = nodeAmount;
    }
}