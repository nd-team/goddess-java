package com.bjike.goddess.reportmanagement.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 现金流量分析数据传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-24 10:20 ]
 * @Description: [ 现金流量分析数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CashAnalyseDTO extends BaseDTO {

    /**
     * 时间
     */
    private String time;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目
     */
    private String projectName;

    /**
     * 项目组
     */
    private String projectGroup;

    public CashAnalyseDTO() {
    }
    public CashAnalyseDTO(String time, String area, String projectName, String projectGroup) {
        this.time = time;
        this.area = area;
        this.projectName = projectName;
        this.projectGroup = projectGroup;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }
}