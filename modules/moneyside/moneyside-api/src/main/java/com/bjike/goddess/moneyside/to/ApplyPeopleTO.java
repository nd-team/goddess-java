package com.bjike.goddess.moneyside.to;

/**
 * Created by ike on 17-6-6.
 */
public class ApplyPeopleTO {
    public interface Collect {
    }
    /**
     * 申请人
     */
    private String[] investor;

    public String[] getInvestor() {
        return investor;
    }

    public void setInvestor(String[] investor) {
        this.investor = investor;
    }
}
