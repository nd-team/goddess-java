package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 月计划的业务科目
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-06 05:04 ]
 * @Description: [ 月计划的业务科目 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MonthSubjectUpdateTO extends BaseTO {

    /**
     * 业务方向科目id
     */
    private String subjectDataId;

    /**
     * 各业务科目同一业务类型中占比(%)
     */
    @NotNull(message = "各业务科目同一业务类型中占比(%)不能为空", groups = {ADD.class, EDIT.class})
    private Double proportion;

//    /**
//     * 各业务科目年度占比(%)
//     */
//    @NotNull(message = "各业务科目年度占比(%)不能为空", groups = {ADD.class, EDIT.class})
//    private Double yearProportion;

    /**
     * 现有业务可发展对象
     */
    @NotNull(message = "现有业务可发展对象不能为空", groups = {ADD.class, EDIT.class})
    private Integer deveBusiness;

    /**
     * 各科目月度计划总任务量
     */
    @NotNull(message = "各科目月度计划总任务量不能为空", groups = {ADD.class, EDIT.class})
    private Integer planTotal;

    /**
     * 各科目月度实际总任务量
     */
    @NotNull(message = "各科目月度实际总任务量不能为空", groups = {ADD.class, EDIT.class})
    private Integer actualTotal;

//    /**
//     * 各科目月度差异总任务量
//     */
//    private Integer diffTotal;


    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public Integer getDeveBusiness() {
        return deveBusiness;
    }

    public void setDeveBusiness(Integer deveBusiness) {
        this.deveBusiness = deveBusiness;
    }

    public Integer getPlanTotal() {
        return planTotal;
    }

    public void setPlanTotal(Integer planTotal) {
        this.planTotal = planTotal;
    }

    public Integer getActualTotal() {
        return actualTotal;
    }

    public void setActualTotal(Integer actualTotal) {
        this.actualTotal = actualTotal;
    }

    public String getSubjectDataId() {
        return subjectDataId;
    }

    public void setSubjectDataId(String subjectDataId) {
        this.subjectDataId = subjectDataId;
    }
}