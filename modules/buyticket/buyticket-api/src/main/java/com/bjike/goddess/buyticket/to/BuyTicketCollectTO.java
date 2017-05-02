package com.bjike.goddess.buyticket.to;

import com.bjike.goddess.buyticket.enums.CollectCycle;
import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 车票购买汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 06:13 ]
 * @Description: [ 车票购买汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BuyTicketCollectTO extends BaseTO {

    /**
     * 地区
     */
    private String area;

    /**
     * 部门/项目组
     */
    private String department;

    /**
     * 汇总类型
     */
    private String collectType;

    /**
     * 汇总类型的数据
     */
    private String collectTypeData;

    /**
     * 汇总周期
     */
    private CollectCycle collectCycle;

    /**
     * 汇总开始时间
     */
    private String collectStartTime;

    /**
     * 汇总结束时间
     */
    private String collectEndTime;

    /**
     * 呈现类型（饼图/简单的记录）
     */
    private String presentType;


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

    public String getCollectType() {
        return collectType;
    }

    public void setCollectType(String collectType) {
        this.collectType = collectType;
    }

    public String getCollectTypeData() {
        return collectTypeData;
    }

    public void setCollectTypeData(String collectTypeData) {
        this.collectTypeData = collectTypeData;
    }

    public CollectCycle getCollectCycle() {
        return collectCycle;
    }

    public void setCollectCycle(CollectCycle collectCycle) {
        this.collectCycle = collectCycle;
    }

    public String getCollectStartTime() {
        return collectStartTime;
    }

    public void setCollectStartTime(String collectStartTime) {
        this.collectStartTime = collectStartTime;
    }

    public String getCollectEndTime() {
        return collectEndTime;
    }

    public void setCollectEndTime(String collectEndTime) {
        this.collectEndTime = collectEndTime;
    }

    public String getPresentType() {
        return presentType;
    }

    public void setPresentType(String presentType) {
        this.presentType = presentType;
    }
}