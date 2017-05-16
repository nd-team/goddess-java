package com.bjike.goddess.dispatchcar.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.time.LocalDate;

/**
 * 根据地区及项目组及项目名称及年份及月份查询出车记录
 *
 * @Author: [Jason]
 * @Date: [17-5-12 下午4:09]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ConditionTO extends BaseTO {

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String group;

    /**
     * 项目名称
     */
    private String project;

    /**
     * 出车日期查询范围
     */
    private LocalDate[] dispatchDate;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public LocalDate[] getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(LocalDate[] dispatchDate) {
        this.dispatchDate = dispatchDate;
    }
}
