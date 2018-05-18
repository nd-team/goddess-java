package com.bjike.goddess.marketdevelopment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.marketdevelopment.enums.Status;

/**
 * 月计划省市方向(市)业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 10:46 ]
 * @Description: [ 月计划省市方向(市)业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MonthSubjectAreaBO extends BaseBO {

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