package com.bjike.goddess.bidding.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;

/**
 * 招投标网站信息业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T10:15:43.316 ]
 * @Description: [ 招投标网站信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BiddingWebInfoBO extends BaseBO {

    /**
     * 网站名称
     */
    private String webName;

    /**
     * 网址
     */
    private String url;
    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 注册人
     */
    private String registrant;

    /**
     * 注册信息
     */
    private String registrationInfo;

    /**
     * 状态
     */
    private Status status;
    /**
     * 检索关键词
     */
    private String keyWords;
    /**
     * 检索频率
     */
    private String retrieveFrequency;


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

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getRetrieveFrequency() {
        return retrieveFrequency;
    }

    public void setRetrieveFrequency(String retrieveFrequency) {
        this.retrieveFrequency = retrieveFrequency;
    }
}