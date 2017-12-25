package com.bjike.goddess.buyticket.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 购票标准
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 06:40 ]
 * @Description: [ 购票标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "buyticket_buyticketstandard")
public class BuyTicketStandard extends BaseEntity {

    /**
     * 交通工具
     */
    @Column(name = "vehicle", columnDefinition = "VARCHAR(255)   COMMENT '交通工具'")
    private String vehicle;

    /**
     * 总行程
     */
    @Column(name = "totalTrip", columnDefinition = "VARCHAR(255)   COMMENT '总行程'")
    private String totalTrip;

    /**
     * 情况
     */
    @Column(name = "situation", columnDefinition = "VARCHAR(255)   COMMENT '情况'")
    private String situation;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getTotalTrip() {
        return totalTrip;
    }

    public void setTotalTrip(String totalTrip) {
        this.totalTrip = totalTrip;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}