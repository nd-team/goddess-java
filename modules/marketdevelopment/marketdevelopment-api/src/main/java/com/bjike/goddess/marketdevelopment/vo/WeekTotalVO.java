package com.bjike.goddess.marketdevelopment.vo;

/**
 * 周计划的合计表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-02 05:42 ]
 * @Description: [ 周计划的合计表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WeekTotalVO {

    /**
     * id
     */
    private String id;
    /**
     * 周计划的周期id
     */
    private String weekCycleId;

    /**
     * 合计
     */
    private String total;

    /**
     * 备注
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeekCycleId() {
        return weekCycleId;
    }

    public void setWeekCycleId(String weekCycleId) {
        this.weekCycleId = weekCycleId;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}