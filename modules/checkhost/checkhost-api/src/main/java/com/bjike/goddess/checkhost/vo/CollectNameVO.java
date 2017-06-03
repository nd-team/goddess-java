package com.bjike.goddess.checkhost.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * Created by ike on 17-6-2.
 */
public class CollectNameVO {
    /**
     * 姓名
     */
    private String name;

    /**
     * 员工编号
     */
    private String num;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 入住宿舍
     */
    private String address;


    /**
     * 入住时间
     */
    private String stayTime;

    /**
     * 离宿时间
     */
    private String hostTime;

    /**
     * 是否领用钥匙
     */
    private String receiveKey;

    /**
     * 是否领用床上用品
     */
    private String bed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStayTime() {
        return stayTime;
    }

    public void setStayTime(String stayTime) {
        this.stayTime = stayTime;
    }

    public String getHostTime() {
        return hostTime;
    }

    public void setHostTime(String hostTime) {
        this.hostTime = hostTime;
    }

    public String getReceiveKey() {
        return receiveKey;
    }

    public void setReceiveKey(String receiveKey) {
        this.receiveKey = receiveKey;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }
}
