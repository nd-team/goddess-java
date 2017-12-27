package com.bjike.goddess.user.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 首页导航
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-21 09:37 ]
 * @Description: [ 首页导航 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "user_homenavigation")
public class HomeNavigation extends BaseEntity {

    /**
     * 导航名称
     */
    @Column(name = "navigationName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '导航名称'")
    private String navigationName;

    /**
     * 顺序序号
     */
    @Column(name = "orderNum", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '顺序序号'")
    private Integer orderNum;

    /**
     * 对应的url
     */
    @Column(name = "url", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '对应的url'")
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