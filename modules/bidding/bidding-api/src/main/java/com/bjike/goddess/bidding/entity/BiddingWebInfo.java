package com.bjike.goddess.bidding.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 招投标网站信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T10:15:43.314 ]
 * @Description: [ 招投标网站信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "bidding_biddingwebinfo")
public class BiddingWebInfo extends BaseEntity {

    /**
     * 网站名称
     */
    @Column(name = "webName", columnDefinition = "VARCHAR(255)   COMMENT '网站名称'")
    private String webName;

    /**
     * 网址
     */
    @Column(name = "url", columnDefinition = "VARCHAR(255)   COMMENT '网址'")
    private String url;
    /**
     * 账号
     */
    @Column(name = "account", columnDefinition = "VARCHAR(255)   COMMENT '账号'")
    private String account;

    /**
     * 密码
     */
    @Column(name = "password", columnDefinition = "VARCHAR(255)   COMMENT '密码'")
    private String password;

    /**
     * 注册人
     */
    @Column(name = "registrant", columnDefinition = "VARCHAR(255)   COMMENT '注册人'")
    private String registrant;

    /**
     * 注册信息
     */
    @Column(name = "registrationInfo", columnDefinition = "VARCHAR(255)   COMMENT '注册信息'")
    private String registrationInfo;

    /**
     * 状态
     */
    @Column(name = "status", columnDefinition = "TINYINT(2)   COMMENT '状态'")
    private Status status;


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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistrant() {
        return registrant;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public String getRegistrationInfo() {
        return registrationInfo;
    }

    public void setRegistrationInfo(String registrationInfo) {
        this.registrationInfo = registrationInfo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}