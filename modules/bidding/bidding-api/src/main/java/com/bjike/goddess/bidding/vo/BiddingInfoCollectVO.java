package com.bjike.goddess.bidding.vo;

import java.io.Serializable;

/**
 * Created by ike on 17-5-5.
 */
public class BiddingInfoCollectVO implements Serializable{

    /**
     * 地市
     */
    private String cities;
    /**
     * 邀请招标
     */
    private Object invite;
    /**
     * 公开招标
     */
    private Object openly;
    /**
     * 移动通信
     */
    private Object mobile;
    /**
     * 软件开发
     */
    private Object soft;
    /**
     * 智能系统集成
     */
    private Object system;
    /**
     * 策划与营销方案
     */
    private Object plan;

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public Object getInvite() {
        return invite;
    }

    public void setInvite(Object invite) {
        this.invite = invite;
    }

    public Object getOpenly() {
        return openly;
    }

    public void setOpenly(Object openly) {
        this.openly = openly;
    }

    public Object getMobile() {
        return mobile;
    }

    public void setMobile(Object mobile) {
        this.mobile = mobile;
    }

    public Object getSoft() {
        return soft;
    }

    public void setSoft(Object soft) {
        this.soft = soft;
    }

    public Object getSystem() {
        return system;
    }

    public void setSystem(Object system) {
        this.system = system;
    }

    public Object getPlan() {
        return plan;
    }

    public void setPlan(Object plan) {
        this.plan = plan;
    }
}
