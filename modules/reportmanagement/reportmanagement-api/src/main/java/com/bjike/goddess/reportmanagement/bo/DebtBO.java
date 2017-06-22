package com.bjike.goddess.reportmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 负债表业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:21 ]
 * @Description: [ 负债表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DebtBO extends BaseBO {

    /**
     * 起始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 负债和所有者权益(或股东权益)
     */
    private String debt;

    /**
     * 负债年初数
     */
    private Double beginDebt;

    /**
     * 负债期末数
     */
    private Double endAsset;


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public Double getBeginDebt() {
        return beginDebt;
    }

    public void setBeginDebt(Double beginDebt) {
        this.beginDebt = beginDebt;
    }

    public Double getEndAsset() {
        return endAsset;
    }

    public void setEndAsset(Double endAsset) {
        this.endAsset = endAsset;
    }
}