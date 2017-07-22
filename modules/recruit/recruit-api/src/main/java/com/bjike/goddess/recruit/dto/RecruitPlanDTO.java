package com.bjike.goddess.recruit.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 招聘计划
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 10:36]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RecruitPlanDTO extends BaseDTO {
    /**
     * 日期
     */
    private String time;
    /**
     * 招聘地区
     */
    private String area;
    /**
     * 招聘部门/项目组
     */
    private String depart;
    /**
     * 招聘岗位
     */
    private String position;

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

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
