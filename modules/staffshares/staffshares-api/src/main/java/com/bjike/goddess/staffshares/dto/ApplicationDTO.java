package com.bjike.goddess.staffshares.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 干股代表申请数据传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:27 ]
 * @Description: [ 干股代表申请数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ApplicationDTO extends BaseDTO {
    /**
     * 持股人
     */
    private String shareholder;

    /**
     * 申请时间
     */
    private String time;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 岗位
     */
    private String position;

    /**
     * 在职时间（月数）
     */
    private int months;

    /**
     * 申请原因
     */
    private String reason;

    public String getShareholder() {
        return shareholder;
    }

    public void setShareholder(String shareholder) {
        this.shareholder = shareholder;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}