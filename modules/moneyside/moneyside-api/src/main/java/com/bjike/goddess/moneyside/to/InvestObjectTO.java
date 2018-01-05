package com.bjike.goddess.moneyside.to;

/**
 * Created by ike on 17-6-10.
 */
public class InvestObjectTO {
    public interface Collect {
    }
    /**
     * 投资对象
     */
    private String[] investObject;

    public String[] getInvestObject() {
        return investObject;
    }

    public void setInvestObject(String[] investObject) {
        this.investObject = investObject;
    }
}
