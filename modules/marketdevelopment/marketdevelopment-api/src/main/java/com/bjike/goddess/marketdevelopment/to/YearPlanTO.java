package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 年计划
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 05:57 ]
 * @Description: [ 年计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class YearPlanTO extends BaseTO {

    /**
     * 年份
     */
    @Size(max = 2999, min = 1990)
    @NotNull(message = "年份不能为空", groups = {ADD.class, EDIT.class})
    private Integer year;

    /**
     * 业务类型
     */
    @NotBlank(message = "业务类型不能为空", groups = {ADD.class, EDIT.class})
    private String type;

    /**
     * 工作量权重
     */
    @NotNull(message = "工作量权重不能为空", groups = {ADD.class, EDIT.class})
    private Double workloadWeight;

    /**
     * 业务方向科目
     */
    @NotBlank(message = "业务方向科目不能为空", groups = {ADD.class, EDIT.class})
    private String course;

    /**
     * 可发展对象
     */
    @Size(max = 9999, min = 0)
    @NotNull(message = "可发展对象不能为空", groups = {ADD.class, EDIT.class})
    private Integer development;

    /**
     * 同一类业务类型中占比
     */
    @NotNull(message = "同一类业务类型中占比不能为空", groups = {ADD.class, EDIT.class})
    private Double businessAccounted;

    /**
     * 各业务科目年度占比
     */
    @NotNull(message = "各业务科目年度占比不能为空", groups = {ADD.class, EDIT.class})
    private Double courseAccounted;

    /**
     * 年任务量
     */
    @NotNull(message = "年任务量不能为空", groups = {ADD.class, EDIT.class})
    private Double quota;


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getWorkloadWeight() {
        return workloadWeight;
    }

    public void setWorkloadWeight(Double workloadWeight) {
        this.workloadWeight = workloadWeight;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getDevelopment() {
        return development;
    }

    public void setDevelopment(Integer development) {
        this.development = development;
    }

    public Double getBusinessAccounted() {
        return businessAccounted;
    }

    public void setBusinessAccounted(Double businessAccounted) {
        this.businessAccounted = businessAccounted;
    }

    public Double getCourseAccounted() {
        return courseAccounted;
    }

    public void setCourseAccounted(Double courseAccounted) {
        this.courseAccounted = courseAccounted;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }
}