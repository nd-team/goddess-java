package com.bjike.goddess.bidding.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by ike on 17-5-5.
 */
public class BiddingInfoCollectBO extends BaseBO{

    /**
     * 招投标类型汇总集合
     */
    private List<Map<String, String>> biddingMap;
    /**
     * 业务类型汇总集合
     */
    private List<Map<String, String>> businessMap;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

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
}
