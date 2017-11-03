package com.bjike.goddess.recruit.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 招聘需求与计划数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-30 03:52 ]
 * @Description: [ 招聘需求与计划数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecruitDemandPlanDTO extends BaseDTO {
    /**
     * 招聘地区
     */
    private String area;

    /**
     * 招聘部门/项目组
     */
    private String projectGroup;

    /**
     * 招聘岗位
     */
    private String position;
    /**
     * 优先级
     */
    private Integer priority;
    /**
     * 是否完成招聘
     */
    private Boolean completeRecruit;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getCompleteRecruit() {
        return completeRecruit;
    }

    public void setCompleteRecruit(Boolean completeRecruit) {
        this.completeRecruit = completeRecruit;
    }
}