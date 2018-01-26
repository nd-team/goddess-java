package com.bjike.goddess.projectprocing.vo;

/**
 * 结算工作进度管理表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:09 ]
 * @Description: [ 结算工作进度管理表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SettleWorkProgreManageVO {

    /**
     * id
     */
    private String id;
    /**
     * 节点
     */
    private String node;

    /**
     * 节点id
     */
    private String nodeId;

    /**
     * 派工名称
     */
    private String dispatchingName;

    /**
     * 销售合同号（包括临时、转正）
     */
    private String salesContract;

    /**
     * 外包合同号
     */
    private String contractNo;

    /**
     * 可结算金额
     */
    private Double payAmount;

    /**
     * 时间
     */
    private String delayDate;

    /**
     * 责任人
     */
    private String responsible;
    /**
     * 分配人
     */
    private String allocationPeople;


    /**
     * 是否完成
     */
    private Boolean complete;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getDispatchingName() {
        return dispatchingName;
    }

    public void setDispatchingName(String dispatchingName) {
        this.dispatchingName = dispatchingName;
    }

    public String getSalesContract() {
        return salesContract;
    }

    public void setSalesContract(String salesContract) {
        this.salesContract = salesContract;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public String getDelayDate() {
        return delayDate;
    }

    public void setDelayDate(String delayDate) {
        this.delayDate = delayDate;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public String getAllocationPeople() {
        return allocationPeople;
    }

    public void setAllocationPeople(String allocationPeople) {
        this.allocationPeople = allocationPeople;
    }
}