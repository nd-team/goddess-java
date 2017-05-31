package com.bjike.goddess.managepromotion.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 员工已晋升情况
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:20 ]
 * @Description: [ 员工已晋升情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EmployeePromotedTO extends BaseTO {

    /**
     * 部门
     */
    @NotBlank(message = "部门不能为空",groups = {ADD.class, EDIT.class})
    private String department;

    /**
     * 项目组
     */
    @NotBlank(message = "项目组不能为空",groups = {ADD.class, EDIT.class})
    private String projectGroup;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空",groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 岗位
     */
    @NotBlank(message = "岗位不能为空",groups = {ADD.class, EDIT.class})
    private String jobs;

    /**
     * 渠道
     */
    @NotBlank(message = "渠道不能为空",groups = {ADD.class, EDIT.class})
    private String channel;

    /**
     * 时间
     */
    @NotBlank(message = "时间不能为空",groups = {ADD.class, EDIT.class})
    private String times;

    /**
     * 晋升前（不包括各项补助）
     */
    private Integer promotionBefore;

    /**
     * 晋升后（不包括各项补助）
     */
    private Integer promotionAfter;

    /**
     * 幅度(晋升后-晋升前)
     */
    private Integer extent;

    /**
     * 总幅度
     */
    private Integer totalRange;

    /**
     * 状态（审核中/通过/不通过）
     */
    @NotBlank(message = "状态不能为空",groups = {ADD.class, EDIT.class})
    private String status;


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public Integer getPromotionBefore() {
        return promotionBefore;
    }

    public void setPromotionBefore(Integer promotionBefore) {
        this.promotionBefore = promotionBefore;
    }

    public Integer getPromotionAfter() {
        return promotionAfter;
    }

    public void setPromotionAfter(Integer promotionAfter) {
        this.promotionAfter = promotionAfter;
    }

    public Integer getExtent() {
        return extent;
    }

    public void setExtent(Integer extent) {
        this.extent = extent;
    }

    public Integer getTotalRange() {
        return totalRange;
    }

    public void setTotalRange(Integer totalRange) {
        this.totalRange = totalRange;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}