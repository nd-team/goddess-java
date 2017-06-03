package com.bjike.goddess.market.bo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ike on 17-5-2.
 */
public class MarketCollectBO implements Serializable {
    private Object mobile;
    private Object soft;
    private Object system;
    private Object plan;
    private Object first;
    private Object second;
    private Object third;
    private Object fourth;
    private Object has;
    private Object notHas;
    private Object fresh;
    private Object old;
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
