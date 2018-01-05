package com.bjike.goddess.qualifications.vo;

import com.bjike.goddess.qualifications.enums.HandleStatus;

/**
 * 资质办理管理表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 07:15 ]
 * @Description: [ 资质办理管理表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class QualificationsHandleVO {

    /**
     * id
     */
    private String id;
    /**
     * 资质类别
     */
    private String type;

    /**
     * 是否办理成功
     */
    private HandleStatus status;

    /**
     * 预算的费用
     */
    private Double cost;

    /**
     * 备注
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HandleStatus getStatus() {
        return status;
    }

    public void setStatus(HandleStatus status) {
        this.status = status;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}