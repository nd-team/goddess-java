package com.bjike.goddess.rentcar.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 租车协议管理汇总业务传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-07 11:56 ]
* @Description:	[ 租车协议管理汇总业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CollectDriverInfoBO extends BaseBO {

    /**
     * 商务需求
     */
    private Integer  businessDemand;

    /**
     * 项目需求
     */
    private Integer  projectDemand;

    /**
     * 待签订协议数
     */
    private Integer  waitSignDealNumber;

    /**
     * 可用司机
     */
    private Integer  availalbeDriver;

    /**
     * 还需司机数
     */
    private Integer  needDriver;

    /**
     * 待解除协议数
     */
    private Integer  waitRelieveDealNumber;


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

    public Integer getWaitSignDealNumber() {
        return waitSignDealNumber;
    }

    public void setWaitSignDealNumber(Integer waitSignDealNumber) {
        this.waitSignDealNumber = waitSignDealNumber;
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

    public Integer getWaitRelieveDealNumber() {
        return waitRelieveDealNumber;
    }

    public void setWaitRelieveDealNumber(Integer waitRelieveDealNumber) {
        this.waitRelieveDealNumber = waitRelieveDealNumber;
    }
}