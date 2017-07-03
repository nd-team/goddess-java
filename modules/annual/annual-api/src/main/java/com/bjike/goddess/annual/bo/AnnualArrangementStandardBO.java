package com.bjike.goddess.annual.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 年假层级标准业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:33 ]
 * @Description: [ 年假层级标准业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnnualArrangementStandardBO extends BaseBO {

    /**
     * 标准ID
     */
    private String standardId;

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
    private String arrangementId;

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

    public String getStandardId() {
        return standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId;
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

    public String getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(String arrangementId) {
        this.arrangementId = arrangementId;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}