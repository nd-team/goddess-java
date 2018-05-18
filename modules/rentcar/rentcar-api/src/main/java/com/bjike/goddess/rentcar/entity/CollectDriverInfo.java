package com.bjike.goddess.rentcar.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 租车协议管理汇总
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-07 11:56 ]
* @Description:	[ 租车协议管理汇总 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "rentcar_collectdriverinfo")
public class CollectDriverInfo extends BaseEntity {
    /**
     * 地区
     */
    @Column(name = "area",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '地区'"  )
    private String  area;

    /**
     * 项目组/部门
     */
    @Column(name = "department",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'"  )
    private String  department;

    /**
     * 商务需求
     */
    @Column(name = "businessDemand",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '商务需求'"  )
    private String  businessDemand;

    /**
     * 项目需求
     */
    @Column(name = "projectDemand",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '项目需求'"  )
    private String  projectDemand;

    /**
     * 待签订协议数
     */
    @Column(name = "",nullable = false,columnDefinition = "INT(11)   COMMENT '待签订协议数'"  )
    private Integer  waitSignDealNumber;

    /**
     * 可用司机
     */
    @Column(nullable = false,columnDefinition = "INT(11)   COMMENT '可用司机'"  )
    private Integer  availalbeDriver;

    /**
     * 还需司机数
     */
    @Column(nullable = false,columnDefinition = "INT(11)   COMMENT '还需司机数'"  )
    private Integer  needDriver;

    /**
     * 待解除协议数
     */
    @Column(nullable = false,columnDefinition = "INT(11)   COMMENT '待解除协议数'"  )
    private Integer  waitRelieveDealNumber;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBusinessDemand() {
        return businessDemand;
    }

    public void setBusinessDemand(String businessDemand) {
        this.businessDemand = businessDemand;
    }

    public String getProjectDemand() {
        return projectDemand;
    }

    public void setProjectDemand(String projectDemand) {
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