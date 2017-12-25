package com.bjike.goddess.bidding.bo;

import java.io.Serializable;

/**
 * Created by ike on 17-5-5.
 */
public class BidOpeningCollectBO implements Serializable{
    private String cities;
    private String competitive;

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public String getCompetitive() {
        return competitive;
    }

    public void setCompetitive(String competitive) {
        this.competitive = competitive;
    }
}
