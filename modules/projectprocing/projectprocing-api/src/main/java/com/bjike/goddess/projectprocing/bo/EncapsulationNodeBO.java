package com.bjike.goddess.projectprocing.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 获取出来的节点数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:26 ]
 * @Description: [ 获取出来的节点数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EncapsulationNodeBO extends BaseBO {

    /**
     * 派工金额
     */
    private Double estimSettleAmount;

    /**
     * 进度节点内容
     */
    private Integer nodeOneNameContent;
    /**
     * 节点父id
     */
    private String fatherId;

    /**
     * 节点1表头
     */
    private String nodeOneHeader;
    /**
     * 节点2表头
     */
    private String nodeTwoHeader;

    /**
     * 节点3表头
     */
    private String nodeThreeHeader;
    /**
     * 节点4表头
     */
    private String nodeFourHeader;

    public Double getEstimSettleAmount() {
        return estimSettleAmount;
    }

    public void setEstimSettleAmount(Double estimSettleAmount) {
        this.estimSettleAmount = estimSettleAmount;
    }

    public Integer getNodeOneNameContent() {
        return nodeOneNameContent;
    }

    public void setNodeOneNameContent(Integer nodeOneNameContent) {
        this.nodeOneNameContent = nodeOneNameContent;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public String getNodeOneHeader() {
        return nodeOneHeader;
    }

    public void setNodeOneHeader(String nodeOneHeader) {
        this.nodeOneHeader = nodeOneHeader;
    }

    public String getNodeTwoHeader() {
        return nodeTwoHeader;
    }

    public void setNodeTwoHeader(String nodeTwoHeader) {
        this.nodeTwoHeader = nodeTwoHeader;
    }

    public String getNodeThreeHeader() {
        return nodeThreeHeader;
    }

    public void setNodeThreeHeader(String nodeThreeHeader) {
        this.nodeThreeHeader = nodeThreeHeader;
    }

    public String getNodeFourHeader() {
        return nodeFourHeader;
    }

    public void setNodeFourHeader(String nodeFourHeader) {
        this.nodeFourHeader = nodeFourHeader;
    }
}