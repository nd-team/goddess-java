package com.bjike.goddess.bidding.vo;

import java.io.Serializable;

/**
 * Created by ike on 17-5-5.
 */
public class BidOpeningCollectVO implements Serializable{
    /**
     * 地市
     */
    private String cities;
    /**
     * 竞争公司
     */
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
