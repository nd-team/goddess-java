package com.bjike.goddess.bidding.vo;

import java.util.List;
import java.util.Map;

/**
 * Created by ike on 17-5-5.
 */
public class BiddingInfoCollectVO {

    /**
     * 招投标类型汇总集合
     */
    private List<Map<String,String>> biddingMap;
    /**
     * 业务类型汇总集合
     */
    private List<Map<String,String>> businessMap;
    /**
     * 个数
     */
    private Integer counts;

    /**
     * 地区
     */
    private String cities;

    /**
     * remark
     */
    private String remark;

    public List<Map<String, String>> getBiddingMap() {
        return biddingMap;
    }

    public void setBiddingMap(List<Map<String, String>> biddingMap) {
        this.biddingMap = biddingMap;
    }

    public List<Map<String, String>> getBusinessMap() {
        return businessMap;
    }

    public void setBusinessMap(List<Map<String, String>> businessMap) {
        this.businessMap = businessMap;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
