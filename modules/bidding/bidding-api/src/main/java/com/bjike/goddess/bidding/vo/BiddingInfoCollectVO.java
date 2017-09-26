package com.bjike.goddess.bidding.vo;

import java.util.Map;

/**
 * Created by ike on 17-5-5.
 */
public class BiddingInfoCollectVO {

    /**
     * 地市
     */
    private String cities;
    private Map<String, Integer> biddingMap;
    private Map<String,Integer> businessMap;

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public Map<String, Integer> getBiddingMap() {
        return biddingMap;
    }

    public void setBiddingMap(Map<String, Integer> biddingMap) {
        this.biddingMap = biddingMap;
    }

    public Map<String, Integer> getBusinessMap() {
        return businessMap;
    }

    public void setBusinessMap(Map<String, Integer> businessMap) {
        this.businessMap = businessMap;
    }
}
