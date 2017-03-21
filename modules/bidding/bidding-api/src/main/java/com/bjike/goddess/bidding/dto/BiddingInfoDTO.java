package com.bjike.goddess.bidding.dto;

import com.bjike.goddess.bidding.entity.BiddingInfo;
import com.bjike.goddess.common.api.dto.BaseDTO;

import javax.persistence.Column;
import java.time.LocalDate;

/**
 * 招标信息数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:48:29.957 ]
 * @Description: [ 招标信息数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BiddingInfoDTO extends BaseDTO {
    /**
     * 招投标类型
     */
    private String biddingType;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 省份
     */
    private String provinces;

    /**
     * 地市
     */
    private String cities;

    public String getBiddingType() {
        return biddingType;
    }

    public void setBiddingType(String biddingType) {
        this.biddingType = biddingType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getProvinces() {
        return provinces;
    }

    public void setProvinces(String provinces) {
        this.provinces = provinces;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }
}