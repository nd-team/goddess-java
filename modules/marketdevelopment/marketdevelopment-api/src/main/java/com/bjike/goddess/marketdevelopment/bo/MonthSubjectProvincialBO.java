package com.bjike.goddess.marketdevelopment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.marketdevelopment.enums.Status;

import java.util.List;

/**
 * 月计划省市方向业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 10:29 ]
 * @Description: [ 月计划省市方向业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MonthSubjectProvincialBO extends BaseBO {

    /**
     * 业务方向类型id
     */
    private String businessDataId;

    /**
     * 省市分类
     */
    private String provincial;

    /**
     * 各省市工作量权重(%)
     */
    private String provincialWeight;

    /**
     * 状态
     */
    private Status status;

    /**
     * 市方向集合
     */
    private List<MonthSubjectAreaBO> monthSubjectAreaVOs;


    public String getBusinessDataId() {
        return businessDataId;
    }

    public void setBusinessDataId(String businessDataId) {
        this.businessDataId = businessDataId;
    }

    public String getProvincial() {
        return provincial;
    }

    public void setProvincial(String provincial) {
        this.provincial = provincial;
    }

    public String getProvincialWeight() {
        return provincialWeight;
    }

    public void setProvincialWeight(String provincialWeight) {
        this.provincialWeight = provincialWeight;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<MonthSubjectAreaBO> getMonthSubjectAreaVOs() {
        return monthSubjectAreaVOs;
    }

    public void setMonthSubjectAreaVOs(List<MonthSubjectAreaBO> monthSubjectAreaVOs) {
        this.monthSubjectAreaVOs = monthSubjectAreaVOs;
    }
}