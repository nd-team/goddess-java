package com.bjike.goddess.marketdevelopment.vo;

import com.bjike.goddess.marketdevelopment.enums.Status;

/**
 * 月计划省市方向(市)表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 10:46 ]
 * @Description: [ 月计划省市方向(市)表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MonthSubjectAreaVO {

    /**
     * id
     */
    private String id;
    /**
     * 省市分类id
     */
    private String provincialId;

    /**
     * 各地区分类
     */
    private String areaClassify;

    /**
     * 各地区工作量权重(%)
     */
    private String areaWeight;

    /**
     * 状态
     */
    private Status status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvincialId() {
        return provincialId;
    }

    public void setProvincialId(String provincialId) {
        this.provincialId = provincialId;
    }

    public String getAreaClassify() {
        return areaClassify;
    }

    public void setAreaClassify(String areaClassify) {
        this.areaClassify = areaClassify;
    }

    public String getAreaWeight() {
        return areaWeight;
    }

    public void setAreaWeight(String areaWeight) {
        this.areaWeight = areaWeight;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}