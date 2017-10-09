package com.bjike.goddess.taskallotment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.taskallotment.enums.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 完成情况汇总设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-28 09:31 ]
 * @Description: [ 完成情况汇总设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FinishCountEmailTO extends BaseTO {

    /**
     * 汇总表名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "汇总表名称不能为空")
    private String name;
    /**
     * 汇总对象
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "汇总对象不能为空")
    private CountType countType;
    /**
     * 汇总个人
     */
    private String[] countPersonss;
    /**
     * 地区
     */
    private String[] areas;
    /**
     * 项目组
     */
    private String[] departs;

    /**
     * 项目
     */
    private String[] projects;

    /**
     * 项目表
     */
    private String[] tables;

    /**
     * 提醒间隔
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "提醒间隔不能为空")
    private Spacing spacing;

    /**
     * 提醒频率
     */
    @Min(value = 0,groups = {ADD.class, EDIT.class},message = "提醒频率不能小于0")
    @NotNull(groups = {ADD.class, EDIT.class},message = "提醒频率不能为空")
    private Integer remind;

    /**
     * 设置时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "设置时间不能为空")
    private String setTime;

    /**
     * 通报对象
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "通报对象不能为空")
    private ForObject forObject;
    /**
     * 通报部门
     */
    private String[] forDepartss;

    /**
     * 通报个人
     */
    private String[] forPersonss;

    /**
     * 汇总频率
     */
    private CountFrequency countFrequency;
    /**
     * 汇总开始时间
     */
    private String startTime;

    /**
     * 汇总结束时间
     */
    private String endTime;

    /**
     * 状态
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "状态不能为空")
    private Status status;

    /**
     * 备注
     */
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountType getCountType() {
        return countType;
    }

    public void setCountType(CountType countType) {
        this.countType = countType;
    }

    public String[] getCountPersonss() {
        return countPersonss;
    }

    public void setCountPersonss(String[] countPersonss) {
        this.countPersonss = countPersonss;
    }

    public String[] getAreas() {
        return areas;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
    }

    public String[] getDeparts() {
        return departs;
    }

    public void setDeparts(String[] departs) {
        this.departs = departs;
    }

    public String[] getProjects() {
        return projects;
    }

    public void setProjects(String[] projects) {
        this.projects = projects;
    }

    public String[] getTables() {
        return tables;
    }

    public void setTables(String[] tables) {
        this.tables = tables;
    }

    public Spacing getSpacing() {
        return spacing;
    }

    public void setSpacing(Spacing spacing) {
        this.spacing = spacing;
    }

    public Integer getRemind() {
        return remind;
    }

    public void setRemind(Integer remind) {
        this.remind = remind;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public ForObject getForObject() {
        return forObject;
    }

    public void setForObject(ForObject forObject) {
        this.forObject = forObject;
    }

    public String[] getForDepartss() {
        return forDepartss;
    }

    public void setForDepartss(String[] forDepartss) {
        this.forDepartss = forDepartss;
    }

    public String[] getForPersonss() {
        return forPersonss;
    }

    public void setForPersonss(String[] forPersonss) {
        this.forPersonss = forPersonss;
    }

    public CountFrequency getCountFrequency() {
        return countFrequency;
    }

    public void setCountFrequency(CountFrequency countFrequency) {
        this.countFrequency = countFrequency;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}