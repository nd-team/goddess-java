package com.bjike.goddess.qualifications.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 资质办理信息采集业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:33 ]
 * @Description: [ 资质办理信息采集业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class QualificationsGatherBO extends BaseBO {

    /**
     * 资质类别
     */
    private String type;

    /**
     * 认证条件
     */
    private String authentication;

    /**
     * 申请开始时间
     */
    private String startTime;

    /**
     * 申请结束时间
     */
    private String endTime;

    /**
     * 认证时间
     */
    private String authenticationTime;

    /**
     * 认证要求描述
     */
    private String demand;

    /**
     * 所需资料描述
     */
    private String material;

    /**
     * 证书获取方式
     */
    private String way;

    /**
     * 办理费用(元)
     */
    private Double cost;

    /**
     * 采集信息来源
     */
    private String originate;

    /**
     * 备注
     */
    private String remark;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAuthenticationTime() {
        return authenticationTime;
    }

    public void setAuthenticationTime(String authenticationTime) {
        this.authenticationTime = authenticationTime;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getOriginate() {
        return originate;
    }

    public void setOriginate(String originate) {
        this.originate = originate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}