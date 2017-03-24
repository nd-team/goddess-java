package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.marketdevelopment.enums.MonthType;

/**
 * 月计划
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 06:41 ]
 * @Description: [ 月计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MonthPlanTO extends BaseTO {

    /**
     * 年计划ID
     */
    private String year_id;

    /**
     * 月份
     */
    private MonthType month;

    /**
     * 各科目年度任务量
     */
    private Double quota;

    /**
     * 年度占比
     */
    private Double accounted;

    /**
     * 业务方向科目中占比
     */
    private Double courseAccounted;

    /**
     * 计划最小任务量
     */
    private Double leastQuota;

    /**
     * 总计
     */
    private Double total;

    public String getYear_id() {
        return year_id;
    }

    public void setYear_id(String year_id) {
        this.year_id = year_id;
    }

    public MonthType getMonth() {
        return month;
    }

    public void setMonth(MonthType month) {
        this.month = month;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }

    public Double getAccounted() {
        return accounted;
    }

    public void setAccounted(Double accounted) {
        this.accounted = accounted;
    }

    public Double getCourseAccounted() {
        return courseAccounted;
    }

    public void setCourseAccounted(Double courseAccounted) {
        this.courseAccounted = courseAccounted;
    }

    public Double getLeastQuota() {
        return leastQuota;
    }

    public void setLeastQuota(Double leastQuota) {
        this.leastQuota = leastQuota;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}