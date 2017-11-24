package com.bjike.goddess.projectprocing.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 结算工作进度管理
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:09 ]
 * @Description: [ 结算工作进度管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SettleWorkProgreManageTO extends BaseTO {

    /**
     * 节点
     */
    @NotBlank(message = "节点不能为空", groups = {ADD.class})
    private String node;

    /**
     * 节点id
     */
    @NotBlank(message = "节点id不能为空", groups = {ADD.class})
    private String nodeId;

    /**
     * 派工名称
     */
    @NotBlank(message = "派工名称不能为空", groups = {ADD.class})
    private String dispatchingName;

    /**
     * 销售合同号（包括临时、转正）
     */
    @NotBlank(message = "销售合同号不能为空", groups = {ADD.class})
    private String salesContract;

    /**
     * 外包合同号
     */
    @NotBlank(message = "外包合同号不能为空", groups = {ADD.class})
    private String contractNo;

    /**
     * 可结算金额
     */
    @NotBlank(message = "可结算金额不能为空", groups = {ADD.class})
    private Double payAmount;

    /**
     * 时间
     */
    @NotBlank(message = "时间不能为空", groups = {ADD.class})
    private String delayDate;

    /**
     * 责任人
     */
    @NotBlank(message = "责任人不能为空", groups = {ADD.class})
    private String responsible;


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

}