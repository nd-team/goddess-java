package com.bjike.goddess.rentcar.vo;

/**
* 租车协议管理汇总表现层对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-07 11:56 ]
* @Description:	[ 租车协议管理汇总表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CollectDriverInfoVO {
    /**
     * id
     */
    private String  id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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