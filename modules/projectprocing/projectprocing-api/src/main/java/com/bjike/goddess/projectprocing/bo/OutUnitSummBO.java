package com.bjike.goddess.projectprocing.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 外包单位传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:26 ]
 * @Description: [ 外包单位传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OutUnitSummBO extends BaseBO {

    /**
     * 外包单位
     */
    private String outUnit;

    /**
     * 外包单位总计
     */
    private Double outUnitTotal;

    /**
     * 派工情况
     */
    private List<DispatchingConditionBO> dispatchingConditionBOS;

    /**
     * 节点对应数据
     */
    private List<NodeDataBO> nodeDataBOS;

    public String getOutUnit() {
        return outUnit;
    }

    public void setOutUnit(String outUnit) {
        this.outUnit = outUnit;
    }

    public Double getOutUnitTotal() {
        return outUnitTotal;
    }

    public void setOutUnitTotal(Double outUnitTotal) {
        this.outUnitTotal = outUnitTotal;
    }

    public List<DispatchingConditionBO> getDispatchingConditionBOS() {
        return dispatchingConditionBOS;
    }

    public void setDispatchingConditionBOS(List<DispatchingConditionBO> dispatchingConditionBOS) {
        this.dispatchingConditionBOS = dispatchingConditionBOS;
    }

    public List<NodeDataBO> getNodeDataBOS() {
        return nodeDataBOS;
    }

    public void setNodeDataBOS(List<NodeDataBO> nodeDataBOS) {
        this.nodeDataBOS = nodeDataBOS;
    }
}