package com.bjike.goddess.annual.vo;

/**
 * 年假层级标准表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:33 ]
 * @Description: [ 年假层级标准表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnnualArrangementStandardVO {
    /**
     * 标准ID
     */
    private String standard_id;

    /**
     * 开始工龄范围(年)
     */
    private Integer startCycle;

    /**
     * 结束工龄范围(年)
     */
    private Integer endCycle;

    /**
     * 病假限制(月)
     */
    private Integer astrict;

    /**
     * 备注
     */
    private String remark;

    /**
     * 岗位层级id
     */
    private String arrangement_id;

    /**
     * 岗位层级
     */
    private String arrangement;

    /**
     * 年假天数
     */
    private Integer days;

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public String getStandard_id() {
        return standard_id;
    }

    public void setStandard_id(String standard_id) {
        this.standard_id = standard_id;
    }

    public Integer getStartCycle() {
        return startCycle;
    }

    public void setStartCycle(Integer startCycle) {
        this.startCycle = startCycle;
    }

    public Integer getEndCycle() {
        return endCycle;
    }

    public void setEndCycle(Integer endCycle) {
        this.endCycle = endCycle;
    }

    public Integer getAstrict() {
        return astrict;
    }

    public void setAstrict(Integer astrict) {
        this.astrict = astrict;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getArrangement_id() {
        return arrangement_id;
    }

    public void setArrangement_id(String arrangement_id) {
        this.arrangement_id = arrangement_id;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

}