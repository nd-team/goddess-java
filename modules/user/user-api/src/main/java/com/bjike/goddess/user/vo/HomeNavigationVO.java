package com.bjike.goddess.user.vo;

/**
 * 首页导航表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-21 09:37 ]
 * @Description: [ 首页导航表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HomeNavigationVO {

    /**
     * id
     */
    private String id;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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