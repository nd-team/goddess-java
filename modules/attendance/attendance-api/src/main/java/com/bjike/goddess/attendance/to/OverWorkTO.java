package com.bjike.goddess.attendance.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 加班
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 加班 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OverWorkTO extends BaseTO {

    public  interface TESTAddAndEdit{}

    /**
     * 地区
     */
    @NotBlank(groups = {OverWorkTO.TESTAddAndEdit.class} , message = "地区不能为空")
    private String area;

    /**
     * 任务下达人
     */
    @NotBlank(groups = {OverWorkTO.TESTAddAndEdit.class} , message = "任务下达人不能为空")
    private String tasker;

    /**
     * 加班人员
     */
    @NotBlank(groups = {OverWorkTO.TESTAddAndEdit.class} , message = "加班人员不能为空")
    private String overWorker;

    /**
     * 加班类型(普通加班/节假日加班/其他....)
     */
    @NotBlank(groups = {OverWorkTO.TESTAddAndEdit.class} , message = "加班类型不能为空")
    private String overType;

    /**
     * 部门
     */
    @NotBlank(groups = {OverWorkTO.TESTAddAndEdit.class} , message = "部门不能为空")
    private String depart;

    /**
     * 职位
     */
    @NotBlank(groups = {OverWorkTO.TESTAddAndEdit.class} , message = "职位不能为空")
    private String position;

    /**
     * 开始时间
     */
    @NotBlank(groups = {OverWorkTO.TESTAddAndEdit.class} , message = "开始时间不能为空，格式为2017-10-01 18:00:00")
    private String overStartTime;

    /**
     * 结束时间
     */
    @NotBlank(groups = {OverWorkTO.TESTAddAndEdit.class} , message = "结束时间不能为空，格式为2017-10-01 18:00:00")
    private String overEndTime;

    /**
     * 加班时长
     */
    @NotNull(groups = {OverWorkTO.TESTAddAndEdit.class} , message = "加班时长不能为空")
    private Double overLong;

    /**
     * 是否午休
     */
    @NotNull(groups = {OverWorkTO.TESTAddAndEdit.class} , message = "是否午休不能为空")
    private Boolean noonBreakOr;

    /**
     * 工作内容
     */
    @NotBlank(groups = {OverWorkTO.TESTAddAndEdit.class} , message = "工作内容不能为空")
    private String workContent;

    /**
     * 完成情况
     */
    @NotBlank(groups = {OverWorkTO.TESTAddAndEdit.class} , message = "完成情况不能为空")
    private String completeCon;

    /**
     * 可休天数
     */
    @NotNull(groups = {OverWorkTO.TESTAddAndEdit.class} , message = "可休天数不能为空")
    private Double relaxDay;

    /**
     * 负责人(审批人)
     */
    @NotBlank(groups = {OverWorkTO.TESTAddAndEdit.class} , message = "负责人(审批人)不能为空")
    private String charger;



    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTasker() {
        return tasker;
    }

    public void setTasker(String tasker) {
        this.tasker = tasker;
    }

    public String getOverWorker() {
        return overWorker;
    }

    public void setOverWorker(String overWorker) {
        this.overWorker = overWorker;
    }

    public String getOverType() {
        return overType;
    }

    public void setOverType(String overType) {
        this.overType = overType;
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

    public String getOverStartTime() {
        return overStartTime;
    }

    public void setOverStartTime(String overStartTime) {
        this.overStartTime = overStartTime;
    }

    public String getOverEndTime() {
        return overEndTime;
    }

    public void setOverEndTime(String overEndTime) {
        this.overEndTime = overEndTime;
    }

    public Double getOverLong() {
        return overLong;
    }

    public void setOverLong(Double overLong) {
        this.overLong = overLong;
    }

    public Boolean getNoonBreakOr() {
        return noonBreakOr;
    }

    public void setNoonBreakOr(Boolean noonBreakOr) {
        this.noonBreakOr = noonBreakOr;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getCompleteCon() {
        return completeCon;
    }

    public void setCompleteCon(String completeCon) {
        this.completeCon = completeCon;
    }

    public Double getRelaxDay() {
        return relaxDay;
    }

    public void setRelaxDay(Double relaxDay) {
        this.relaxDay = relaxDay;
    }

    public String getCharger() {
        return charger;
    }

    public void setCharger(String charger) {
        this.charger = charger;
    }

}