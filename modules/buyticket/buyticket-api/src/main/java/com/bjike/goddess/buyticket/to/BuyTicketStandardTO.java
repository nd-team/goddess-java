package com.bjike.goddess.buyticket.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 购票标准
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 06:40 ]
 * @Description: [ 购票标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BuyTicketStandardTO extends BaseTO {

    /**
     * 交通工具
     */
    private String vehicle;

    /**
     * 总行程
     */
    private String totalTrip;

    /**
     * 情况
     */
    private String situation;

    /**
     * 备注
     */
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