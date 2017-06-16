package com.bjike.goddess.bidding.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;



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
     * 网站名称
     */
    private String webName;

    /**
     * 网址
     */
    private String url;

    /**
     * 省份
     */
    private String provinces;

    /**
     * 地市
     */
    private String cities;
    /**
     * 项目名称
     */
    private String projectName;



    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}