package com.bjike.goddess.dispatchcar.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.dispatchcar.enums.Acctype;

import javax.validation.constraints.NotNull;

/**
 * 财务项目汇总
 *
 * @Author: [Jason]
 * @Date: [17-4-18 上午9:09]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProjectCollectTO extends BaseTO {

    public interface SelectCollect {
    }

    /**
     * 年份
     */
    @NotNull(message = "年份不能为空!", groups = {ProjectCollectTO.SelectCollect.class})
    private Integer year;

    /**
     * 月份
     */
    @NotNull(message = "月份不能为空!", groups = {ProjectCollectTO.SelectCollect.class})
    private Integer month;

    /**
     * 项目
     */
    private String project;

    /**
     * 项目组
     */
    private String group;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

}
