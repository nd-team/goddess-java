package com.bjike.goddess.moneyside.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * Created by ike on 17-6-6.
 */
public class CollectBO extends BaseBO{
    /**
     * 申请人
     */
    private String applyPeople;

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

    public String getApplyPeople() {
        return applyPeople;
    }

    public void setApplyPeople(String applyPeople) {
        this.applyPeople = applyPeople;
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
    public CollectBO(String applyPeople,String accessToFund,String fundEntryTime,Double money){
        this.applyPeople = applyPeople;
        this.accessToFund = accessToFund;
        this.fundEntryTime = fundEntryTime;
        this.money = money;

    }
}
