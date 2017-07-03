package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.marketdevelopment.enums.MonthType;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "年计划ID不能为空",groups = {ADD.class, EDIT.class})
    private String yearId;

    /**
     * 月份
     */
    @NotNull(message = "月份不能为空",groups = {ADD.class, EDIT.class})
    private MonthType month;

    /**
     * 各科目年度任务量
     */
    @NotNull(message = "各科目年度任务量不能为空",groups = {ADD.class, EDIT.class})
    private Double quota;

    /**
     * 年度占比
     */
    @NotNull(message = "年度占比不能为空",groups = {ADD.class, EDIT.class})
    private Double accounted;

    /**
     * 业务方向科目中占比
     */
    @NotNull(message = "业务方向科目中占比不能为空",groups = {ADD.class, EDIT.class})
    private Double courseAccounted;

    /**
     * 计划最小任务量
     */
    @NotNull(message = "计划最小任务量不能为空",groups = {ADD.class, EDIT.class})
    private Double leastQuota;

    /**
     * 总计
     */
    private Double total;

    public String getYearId() {
        return yearId;
    }

    public void setYearId(String yearId) {
        this.yearId = yearId;
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