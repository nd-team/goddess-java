package com.bjike.goddess.projectprocing.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.projectprocing.enums.ActualCompletedState;
import com.bjike.goddess.projectprocing.enums.DispatCondition;
import com.bjike.goddess.projectprocing.enums.Kpi;

/**
 * 结算进度管理数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 02:22 ]
 * @Description: [ 结算进度管理数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SettleProgressManageDTO extends BaseDTO {
    /**
     * 运营商名称
     */
    private String operatorName;

    /**
     * 地区
     */
    private String area;

    /**
     * 外包单位
     */
    private String outUnit;
    /**
     * 销售合同号
     */
    private String saleContractNo;

    /**
     * 外包合同号
     */
    private String contractNo;
    /**
     * 派工情况
     */
    private DispatCondition dispatCondition;
    /**
     * 实际完工状态
     */
    private ActualCompletedState actualCompletedState;
    /**
     * KPI
     */
    private Kpi kpi;
    /**
     * 进度
     */
    private String progress;

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOutUnit() {
        return outUnit;
    }

    public void setOutUnit(String outUnit) {
        this.outUnit = outUnit;
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

    public DispatCondition getDispatCondition() {
        return dispatCondition;
    }

    public void setDispatCondition(DispatCondition dispatCondition) {
        this.dispatCondition = dispatCondition;
    }

    public ActualCompletedState getActualCompletedState() {
        return actualCompletedState;
    }

    public void setActualCompletedState(ActualCompletedState actualCompletedState) {
        this.actualCompletedState = actualCompletedState;
    }

    public Kpi getKpi() {
        return kpi;
    }

    public void setKpi(Kpi kpi) {
        this.kpi = kpi;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}