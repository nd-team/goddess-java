package com.bjike.goddess.receivable.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;

/**
 * 承包商列表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 03:14 ]
 * @Description: [ 承包商列表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContractorTO extends BaseTO {

    /**
     * 名称
     */
    private String name;

    /**
     * 百分比
     */
    private Double percent;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String creationTime;

    /**
     * 状态
     */
    private Status status;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}