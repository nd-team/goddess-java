package com.bjike.goddess.materialbuy.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;

/**
 * 地区购买情况汇总BO
 *
 * @Author: [sunfengtao]
 * @Date: [2017-06-08 10:06]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AreaBuyStatusDayCollectBO extends BaseBO {

    /**
     * 汇总日期
     */
    private String sumDate;

    /**
     * 地区
     */
    private String area;

    /**
     * 部门(项目组)
     */
    private String projectGroup;

    /**
     * 物资类型
     */
    private String deviceType;

    /**
     * 总数量
     */
    private Integer totalQty;

    /**
     * 总金额
     */
    private Double amoutn;

    /**
     * 状态
     */
    private Status status;

    public String getSumDate() {
        return sumDate;
    }

    public void setSumDate(String sumDate) {
        this.sumDate = sumDate;
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

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Integer totalQty) {
        this.totalQty = totalQty;
    }

    public Double getAmoutn() {
        return amoutn;
    }

    public void setAmoutn(Double amoutn) {
        this.amoutn = amoutn;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
