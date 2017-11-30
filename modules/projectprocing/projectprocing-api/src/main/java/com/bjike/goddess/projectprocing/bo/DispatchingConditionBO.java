package com.bjike.goddess.projectprocing.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 派工情况传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:26 ]
 * @Description: [ 派工情况传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DispatchingConditionBO extends BaseBO {

    /**
     * 派工情况(已派工已完工金额/已派工未完工金额)
     */
    private String completedAmount;

    /**
     * 合计(已派工已完工金额/已派工未完工金额)
     */
    private Double completedAmountTot;

    /**
     * 完工状态
     */
    private List<CompletionStateBO> completionStateBOS;

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

    public List<CompletionStateBO> getCompletionStateBOS() {
        return completionStateBOS;
    }

    public void setCompletionStateBOS(List<CompletionStateBO> completionStateBOS) {
        this.completionStateBOS = completionStateBOS;
    }
}