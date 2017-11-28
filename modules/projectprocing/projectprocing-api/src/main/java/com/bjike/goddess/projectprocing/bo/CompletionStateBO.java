package com.bjike.goddess.projectprocing.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 已派工完工状态传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:26 ]
 * @Description: [ 已派工完工状态传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompletionStateBO extends BaseBO {

    /**
     * 完工状态(已派工已完工金额/已派工未完工金额/未派工已完工金额/未派工未完工金额)
     */
    private String completedAmount;

    /**
     * 合计(已派工已完工金额/已派工未完工金额/未派工已完工金额/未派工未完工金额)
     */
    private Double completedAmountTot;

    /**
     * 验收情况
     */
    private List<AcceptSituationBO> acceptSituationBOS;

    public String getCompletedAmount() {
        return completedAmount;
    }

    public void setCompletedAmount(String completedAmount) {
        this.completedAmount = completedAmount;
    }

    public Double getCompletedAmountTot() {
        return completedAmountTot;
    }

    public void setCompletedAmountTot(Double completedAmountTot) {
        this.completedAmountTot = completedAmountTot;
    }

    public List<AcceptSituationBO> getAcceptSituationBOS() {
        return acceptSituationBOS;
    }

    public void setAcceptSituationBOS(List<AcceptSituationBO> acceptSituationBOS) {
        this.acceptSituationBOS = acceptSituationBOS;
    }
}