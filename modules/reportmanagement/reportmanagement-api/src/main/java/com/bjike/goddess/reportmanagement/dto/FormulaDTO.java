package com.bjike.goddess.reportmanagement.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 对应的公式数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-20 09:56 ]
 * @Description: [ 对应的公式数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FormulaDTO extends BaseDTO {
    /**
     * 起始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 项目名称数组
     */
    private String[] projectNames;

    /**
     * 项目组/部门数组
     */
    private String[] departs;

    /**
     * 地区数组
     */
    private String[] areas;

    public String[] getDeparts() {
        return departs;
    }

    public void setDeparts(String[] departs) {
        this.departs = departs;
    }

    public String[] getAreas() {
        return areas;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
    }

    public String[] getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(String[] projectNames) {
        this.projectNames = projectNames;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}