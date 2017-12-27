package com.bjike.goddess.user.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 首页导航业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-21 09:37 ]
 * @Description: [ 首页导航业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HomeNavigationBO extends BaseBO {

    /**
     * 导航名称
     */
    private String navigationName;

    /**
     * 顺序序号
     */
    private Integer orderNum;

    /**
     * 对应的url
     */
    private String url;


    public String getNavigationName() {
        return navigationName;
    }

    public void setNavigationName(String navigationName) {
        this.navigationName = navigationName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}