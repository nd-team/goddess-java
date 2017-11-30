package com.bjike.goddess.projectprocing.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 分配节点获取的数据
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:19 ]
 * @Description: [ 分配节点获取的数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AllotmentNodeDataVO  {

    /**
     * 节点id
     */
    private String nodeId;

    /**
     * 节点
     */
    private String nodeHeader;

    /**
     * 节点内容(时间)
     */
    private String nodeContent;

    /**
     * 派工名称
     */
    private String dispatName;

    /**
     * 销售合同号（包括临时、转正）
     */
    private String saleContractNo;

    /**
     * 外包合同号
     */
    private String contractNo;

    /**
     * 可结算金额
     */
    private Double payableAmount;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeHeader() {
        return nodeHeader;
    }

    public void setNodeHeader(String nodeHeader) {
        this.nodeHeader = nodeHeader;
    }

    public String getNodeContent() {
        return nodeContent;
    }

    public void setNodeContent(String nodeContent) {
        this.nodeContent = nodeContent;
    }

    public String getDispatName() {
        return dispatName;
    }

    public void setDispatName(String dispatName) {
        this.dispatName = dispatName;
    }

    public String getSaleContractNo() {
        return saleContractNo;
    }

    public void setSaleContractNo(String saleContractNo) {
        this.saleContractNo = saleContractNo;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Double getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(Double payableAmount) {
        this.payableAmount = payableAmount;
    }
}