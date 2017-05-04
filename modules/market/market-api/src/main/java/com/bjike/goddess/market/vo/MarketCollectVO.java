package com.bjike.goddess.market.vo;

import java.io.Serializable;

/**
 * Created by ike on 17-5-3.
 */
public class MarketCollectVO implements Serializable {
    /**
     * 移动通信行业市场信息数量
     */
    private Object mobile;
    /**
     * 软件开发行业市场信息数量
     */
    private Object soft;
    /**
     * 智能系统集成行业市场信息数量
     */
    private Object system;
    /**
     * 策划与营销方案行业市场信息数量
     */
    private Object plan;
    /**
     * 项目级别-A级市场信息数量
     */
    private Object first;
    /**
     * 项目级别-B级市场信息数量
     */
    private Object second;
    /**
     * 项目级别-C级市场信息数量
     */
    private Object third;
    /**
     * 项目级别-D级市场信息数量
     */
    private Object fourth;
    /**
     * 有效信息市场数量
     */
    private Object has;
    /**
     * 无效信息市场数量
     */
    private Object notHas;
    /**
     * 新项目市场信息数量
     */
    private Object fresh;
    /**
     * 已有项目or进行中项目市场信息数量
     */
    private Object old;
    /**
     * 地区
     */
    private String area;

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

    public Object getFirst() {
        return first;
    }

    public void setFirst(Object first) {
        this.first = first;
    }

    public Object getSecond() {
        return second;
    }

    public void setSecond(Object second) {
        this.second = second;
    }

    public Object getThird() {
        return third;
    }

    public void setThird(Object third) {
        this.third = third;
    }

    public Object getFourth() {
        return fourth;
    }

    public void setFourth(Object fourth) {
        this.fourth = fourth;
    }

    public Object getHas() {
        return has;
    }

    public void setHas(Object has) {
        this.has = has;
    }

    public Object getNotHas() {
        return notHas;
    }

    public void setNotHas(Object notHas) {
        this.notHas = notHas;
    }

    public Object getFresh() {
        return fresh;
    }

    public void setFresh(Object fresh) {
        this.fresh = fresh;
    }

    public Object getOld() {
        return old;
    }

    public void setOld(Object old) {
        this.old = old;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
