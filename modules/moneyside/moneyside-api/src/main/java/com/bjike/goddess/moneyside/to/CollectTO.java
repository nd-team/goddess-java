package com.bjike.goddess.moneyside.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * Created by ike on 17-6-6.
 */
public class CollectTO extends BaseTO {
    /**
     * 申请人
     */
    private String[] applyPeople;

    /**
     * 资金进入方式
     */
    private String[] accessToFund;

    /**
     * 开始资金进入时间
     */
    private String startTime;

    /**
     * 结束资金进入时间
     */
    private String endTime;


    public String[] getApplyPeople() {
        return applyPeople;
    }

    public void setApplyPeople(String[] applyPeople) {
        this.applyPeople = applyPeople;
    }

    public String[] getAccessToFund() {
        return accessToFund;
    }

    public void setAccessToFund(String[] accessToFund) {
        this.accessToFund = accessToFund;
    }

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
}
