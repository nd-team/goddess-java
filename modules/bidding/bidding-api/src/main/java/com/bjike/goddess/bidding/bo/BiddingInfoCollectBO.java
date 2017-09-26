package com.bjike.goddess.bidding.bo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by ike on 17-5-5.
 */
public class BiddingInfoCollectBO implements Serializable{

    private String cities;
    private Map<String,Integer> biddingMap;
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
