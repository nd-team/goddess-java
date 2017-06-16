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
    private String[] applyPeople;

    public String[] getApplyPeople() {
        return applyPeople;
    }

    public void setApplyPeople(String[] applyPeople) {
        this.applyPeople = applyPeople;
    }
}
