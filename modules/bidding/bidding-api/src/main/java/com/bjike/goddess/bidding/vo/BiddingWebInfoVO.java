package com.bjike.goddess.bidding.vo;

/**
 * 招投标网站信息表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T10:15:43.320 ]
 * @Description: [ 招投标网站信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BiddingWebInfoVO {

    /**
     * 网站名称
     */
    private String webName;

    /**
     * 网址
     */
    private String url;


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
}