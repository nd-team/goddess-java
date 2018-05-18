package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.marketdevelopment.enums.Status;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 月计划省市方向
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 10:29 ]
 * @Description: [ 月计划省市方向 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MonthSubjectProvincialTO extends BaseTO {

    /**
     * 业务方向类型id
     */
    private String businessDataId;

    /**
     * 省市分类id
     */
    private String monthSubjectProvincialAreaId;


    /**
     * 省市分类
     */
    @NotNull(message = "省市分类不能为空", groups = {ADD.class, EDIT.class})
    private String provincial;

    /**
     * 各省市工作量权重(%)
     */
    @NotNull(message = "各省市工作量权重(%)不能为空", groups = {ADD.class, EDIT.class})
    private String provincialWeight;

    /**
     * 状态
     */
    private Status status;

    /**
     * 市方向
     */
    private List<MonthSubjectAreaTO> monthSubjectAreaTOs;


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

    public List<MonthSubjectAreaTO> getMonthSubjectAreaTOs() {
        return monthSubjectAreaTOs;
    }

    public void setMonthSubjectAreaTOs(List<MonthSubjectAreaTO> monthSubjectAreaTOs) {
        this.monthSubjectAreaTOs = monthSubjectAreaTOs;
    }

    public String getMonthSubjectProvincialAreaId() {
        return monthSubjectProvincialAreaId;
    }

    public void setMonthSubjectProvincialAreaId(String monthSubjectProvincialAreaId) {
        this.monthSubjectProvincialAreaId = monthSubjectProvincialAreaId;
    }
}