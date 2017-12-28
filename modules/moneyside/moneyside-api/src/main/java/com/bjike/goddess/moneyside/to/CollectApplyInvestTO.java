package com.bjike.goddess.moneyside.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * Created by ike on 17-6-10.
 */
public class CollectApplyInvestTO extends BaseTO{
    /**
     * 投资人
     */
    private String[] investor;

    /**
     * 投资对象
     */
    private String[] investObject;
    /**
     * 投资开始日期
     */
    private String startDate;
    /**
     * 投资结束日期
     */
    private String endDate;

    public String[] getInvestor() {
        return investor;
    }

    public void setInvestor(String[] investor) {
        this.investor = investor;
    }

    public String[] getInvestObject() {
        return investObject;
    }

    public void setInvestObject(String[] investObject) {
        this.investObject = investObject;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
