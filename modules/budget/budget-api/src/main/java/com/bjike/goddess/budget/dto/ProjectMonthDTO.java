package com.bjike.goddess.budget.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 项目收入月数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:59 ]
 * @Description: [ 项目收入月数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectMonthDTO extends BaseDTO {
    /**
     * 项目数组
     */
    private String[] projects;

    /**
     * 时间(如2017-05)
     */
    private String time;

    /**
     * 地区
     */
    private String area;

    public String[] getProjects() {
        return projects;
    }

    public void setProjects(String[] projects) {
        this.projects = projects;
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
}