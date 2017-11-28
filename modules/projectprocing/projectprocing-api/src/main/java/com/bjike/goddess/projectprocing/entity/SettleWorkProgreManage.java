package com.bjike.goddess.projectprocing.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 结算工作进度管理
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:09 ]
 * @Description: [ 结算工作进度管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectprocing_settleworkprogremanage")
public class SettleWorkProgreManage extends BaseEntity {

    /**
     * 节点
     */
    @Column(name = "node", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '节点'")
    private String node;

    /**
     * 节点id
     */
    @Column(name = "nodeId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '节点id'")
    private String nodeId;

    /**
     * 派工名称
     */
    @Column(name = "dispatchingName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '派工名称'")
    private String dispatchingName;

    /**
     * 销售合同号（包括临时、转正）
     */
    @Column(name = "salesContract", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '销售合同号（包括临时、转正）'")
    private String salesContract;

    /**
     * 外包合同号
     */
    @Column(name = "contractNo", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '外包合同号'")
    private String contractNo;

    /**
     * 可结算金额
     */
    @Column(name = "payAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '可结算金额'")
    private Double payAmount;

    /**
     * 时间
     */
    @Column(name = "delayDate", nullable = false, columnDefinition = "DATE   COMMENT '时间'")
    private String delayDate;

    /**
     * 责任人
     */
    @Column(name = "responsible", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '责任人'")
    private String responsible;

    /**
     * 是否完成
     */
    @Column(name = "is_complete", columnDefinition = "TINYINT(1)  COMMENT '是否完成'")
    private Boolean complete;


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
}