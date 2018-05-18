package com.bjike.goddess.attendance.bo.overtime;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 补班设置业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-12 04:42 ]
 * @Description: [ 补班设置业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ExtralOverWorkBO extends BaseBO {

    /**
     * 补班类型
     */
    private String overType;

    /**
     * 补班开始时间
     */
    private String overStartTime;

    /**
     * 补班结束时间
     */
    private String overEndTime;

    /**
     * 补班天数
     */
    private Double overDay;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 是否午休
     */
    private Boolean lunchBreak;

    public Boolean getLunchBreak() {
        return lunchBreak;
    }

    public void setLunchBreak(Boolean lunchBreak) {
        this.lunchBreak = lunchBreak;
    }

    public String getOverType() {
        return overType;
    }

    public void setOverType(String overType) {
        this.overType = overType;
    }

    public String getOverStartTime() {
        return overStartTime;
    }

    public void setOverStartTime(String overStartTime) {
        this.overStartTime = overStartTime;
    }

    public String getOverEndTime() {
        return overEndTime;
    }

    public void setOverEndTime(String overEndTime) {
        this.overEndTime = overEndTime;
    }

    public Double getOverDay() {
        return overDay;
    }

    public void setOverDay(Double overDay) {
        this.overDay = overDay;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}