package com.bjike.goddess.projectprocing.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.projectprocing.enums.Types;

/**
 * 验收情况传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:26 ]
 * @Description: [ 验收情况传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AcceptSituationBO extends BaseBO {

    /**
     * 验收情况(已完工到货金额/已完工待初验金额/已完工待终验金额/已完工不需要验收金额/已完工可制作结算资料/已完工正在走结算金额)
     */
    private String completedAmount;

    /**
     * 计数(已完工到货金额/已完工待初验金额/已完工待终验金额/已完工不需要验收金额/已完工可制作结算资料/已完工正在走结算金额)
     */
    private Double completedAmountCount;

    public String getCompletedAmount() {
        return completedAmount;
    }

    public void setCompletedAmount(String completedAmount) {
        this.completedAmount = completedAmount;
    }

    public Double getCompletedAmountCount() {
        return completedAmountCount;
    }

    public void setCompletedAmountCount(Double completedAmountCount) {
        this.completedAmountCount = completedAmountCount;
    }
}