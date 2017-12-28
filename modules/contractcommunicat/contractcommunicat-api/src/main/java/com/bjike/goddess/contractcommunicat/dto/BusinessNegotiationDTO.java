package com.bjike.goddess.contractcommunicat.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 商务洽谈数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-28 11:24 ]
 * @Description: [ 商务洽谈数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessNegotiationDTO extends BaseDTO {
    /**
     * 市场编号
     */
    private String marketNum;
    /**
     * 洽谈公司
     */
    private String discussCompany;
    /**
     * 洽谈对象
     */
    private String discussObject;
    /**
     * 洽谈人
     */
    private String discussPeople;
    /**
     * 内部项目编号
     */
    private String projectNum;
    /**
     * 地区
     */
    private String area;

    public String getMarketNum() {
        return marketNum;
    }

    public void setMarketNum(String marketNum) {
        this.marketNum = marketNum;
    }

    public String getDiscussCompany() {
        return discussCompany;
    }

    public void setDiscussCompany(String discussCompany) {
        this.discussCompany = discussCompany;
    }

    public String getDiscussObject() {
        return discussObject;
    }

    public void setDiscussObject(String discussObject) {
        this.discussObject = discussObject;
    }

    public String getDiscussPeople() {
        return discussPeople;
    }

    public void setDiscussPeople(String discussPeople) {
        this.discussPeople = discussPeople;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}