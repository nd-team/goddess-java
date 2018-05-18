package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.marketdevelopment.enums.Status;

import javax.validation.constraints.NotNull;

/**
 * 月计划省市方向(市)
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 10:46 ]
 * @Description: [ 月计划省市方向(市) ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MonthSubjectAreaTO extends BaseTO {

    /**
     * 省市分类id
     */
    private String provincialId;

    /**
     * 各地区分类
     */
    @NotNull(message = "各地区分类不能为空", groups = {ADD.class, EDIT.class})
    private String areaClassify;

    /**
     * 各地区工作量权重(%)
     */
    @NotNull(message = "各地区工作量权重(%)不能为空", groups = {ADD.class, EDIT.class})
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