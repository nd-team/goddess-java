package com.bjike.goddess.moneyside.to;

/**
 * Created by ike on 17-6-10.
 */
public class InvestorTO {
    public interface Collect {
    }
    /**
     * 投资人
     */
    private String[] investor;

    public String[] getInvestor() {
        return investor;
    }

    public void setInvestor(String[] investor) {
        this.investor = investor;
    }
}
