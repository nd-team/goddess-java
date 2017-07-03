package com.bjike.goddess.materialsummary.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.materialinstock.type.MaterialState;

/**
 * 物资状态年汇总业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:21 ]
 * @Description: [ 物资状态年汇总业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MaterialStatusYearSumBO extends BaseBO {

    /**
     * 汇总起始时间
     */
    private String sumStartTime;

    /**
     * 汇总结束时间
     */
    private String sumEndTime;

    /**
     * 物资状态
     */
    private MaterialState instockState;

    /**
     * 物资分类
     */
    private String deviceType;

    /**
     * 地区
     */
    private String area;

    /**
     * 部门(项目组)
     */
    private String projectGroup;

    /**
     * 总数量
     */
    private Integer totalQty;

    /**
     * 状态
     */
    private Status status;

    public String getSumStartTime() {
        return sumStartTime;
    }

    public void setSumStartTime(String sumStartTime) {
        this.sumStartTime = sumStartTime;
    }

    public String getSumEndTime() {
        return sumEndTime;
    }

    public void setSumEndTime(String sumEndTime) {
        this.sumEndTime = sumEndTime;
    }

    public MaterialState getInstockState() {
        return instockState;
    }

    public void setInstockState(MaterialState instockState) {
        this.instockState = instockState;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Integer getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Integer totalQty) {
        this.totalQty = totalQty;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}