package com.bjike.goddess.moneyside.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * Created by ike on 17-6-6.
 */
public class CollectBO extends BaseBO{
    /**
     * 投资人
     */
    private String investor;

    /**
     * 资金进入方式
     */
    private String accessToFund;

    /**
     * 资金进入时间
     */
    private String fundEntryTime;

    /**
     * 金额
     */
    private Double money;

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getAccessToFund() {
        return accessToFund;
    }

    public void setAccessToFund(String accessToFund) {
        this.accessToFund = accessToFund;
    }

    public String getFundEntryTime() {
        return fundEntryTime;
    }

    public void setFundEntryTime(String fundEntryTime) {
        this.fundEntryTime = fundEntryTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
    public CollectBO(){

    }
    public CollectBO(String investor,String accessToFund,String fundEntryTime,Double money){
        this.investor = investor;
        this.accessToFund = accessToFund;
        this.fundEntryTime = fundEntryTime;
        this.money = money;

    }
}
