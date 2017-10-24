package com.bjike.goddess.carinfo.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 图形化业务传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-12 02:30 ]
* @Description:	[ 图形化业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ImagingBO extends BaseBO {


    /**
     * 商务需求
     */
    private Integer  businessDemand;

    /**
     * 项目需求
     */
    private Integer  projectDemand;

    /**
     * 可用司机
     */
    private Integer  availalbeDriver;

    /**
     * 还需司机数
     */
    private Integer  needDriver;

    /**
     * 待签订协议数
     */
    private Integer  waitSignDealNumber;

    /**
     * 司机信息收集数
     */
    private Integer  driverInformationCollect;

    /**
     * 司机已联系数
     */
    private Integer  driverContactNumber;

    /**
     * 待联系司机数
     */
    private Integer  waitDriverContactNumber;

    public Integer getBusinessDemand() {
        return businessDemand;
    }

    public void setBusinessDemand(Integer businessDemand) {
        this.businessDemand = businessDemand;
    }

    public Integer getProjectDemand() {
        return projectDemand;
    }

    public void setProjectDemand(Integer projectDemand) {
        this.projectDemand = projectDemand;
    }

    public Integer getAvailalbeDriver() {
        return availalbeDriver;
    }

    public void setAvailalbeDriver(Integer availalbeDriver) {
        this.availalbeDriver = availalbeDriver;
    }

    public Integer getNeedDriver() {
        return needDriver;
    }

    public void setNeedDriver(Integer needDriver) {
        this.needDriver = needDriver;
    }

    public Integer getWaitSignDealNumber() {
        return waitSignDealNumber;
    }

    public void setWaitSignDealNumber(Integer waitSignDealNumber) {
        this.waitSignDealNumber = waitSignDealNumber;
    }

    public Integer getDriverInformationCollect() {
        return driverInformationCollect;
    }

    public void setDriverInformationCollect(Integer driverInformationCollect) {
        this.driverInformationCollect = driverInformationCollect;
    }

    public Integer getDriverContactNumber() {
        return driverContactNumber;
    }

    public void setDriverContactNumber(Integer driverContactNumber) {
        this.driverContactNumber = driverContactNumber;
    }

    public Integer getWaitDriverContactNumber() {
        return waitDriverContactNumber;
    }

    public void setWaitDriverContactNumber(Integer waitDriverContactNumber) {
        this.waitDriverContactNumber = waitDriverContactNumber;
    }
}