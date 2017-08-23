package com.bjike.goddess.bidding.bo;

import java.io.Serializable;

/**
 * Created by ike on 17-5-5.
 */
public class BiddingInfoCollectBO implements Serializable{

    private String cities;
    private Object invite;
    private Object openly;
    private Object mobile;
    private Object soft;
    private Object system;
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
