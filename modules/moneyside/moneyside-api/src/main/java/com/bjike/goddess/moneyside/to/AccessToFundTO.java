package com.bjike.goddess.moneyside.to;

/**
 * Created by ike on 17-6-6.
 */
public class AccessToFundTO {
    public interface Collect {
    }
    /**
     * 资金进入方式
     */
    private String[] accessToFund;

    public String[] getAccessToFund() {
        return accessToFund;
    }

    public void setAccessToFund(String[] accessToFund) {
        this.accessToFund = accessToFund;
    }
}
