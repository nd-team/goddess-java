package com.bjike.goddess.attendance.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;


/**
 * 加班导出
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-16 10:32 ]
 * @Description: [ 加班 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OverWorkImportExcel extends BaseBO {

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 加班录入人
     */
    @ExcelHeader(name = "加班录入人", notNull = true)
    private String entryer;
    /**
     * 任务下达人
     */
    @ExcelHeader(name = "任务下达人", notNull = true)
    private String tasker;

    /**
     * 加班人员
     */
    @ExcelHeader(name = "加班人员", notNull = true)
    private String overWorker;

    /**
     * 加班类型
     */
    @ExcelHeader(name = "加班类型", notNull = true)
    private String overType;

    /**
     * 部门
     */
    @ExcelHeader(name = "部门", notNull = true)
    private String depart;

    /**
     * 职位
     */
    @ExcelHeader(name = "职位", notNull = true)
    private String position;

    /**
     * 开始时间
     */
    @ExcelHeader(name = "开始时间", notNull = true)
    private String overStartTime;

    /**
     * 结束时间
     */
    @ExcelHeader(name = "结束时间", notNull = true)
    private String overEndTime;

    /**
     * 加班时长
     */
    @ExcelHeader(name = "加班时长", notNull = true)
    private Double overLong;

    /**
     * 是否午休
     */
    @ExcelHeader(name = "是否午休", notNull = true)
    private String noonBreakOr;

    /**
     * 工作内容
     */
    @ExcelHeader(name = "工作内容", notNull = true)
    private String workContent;

    /**
     * 完成情况
     */
    @ExcelHeader(name = "完成情况", notNull = true)
    private String completeCon;

    /**
     * 可休天数
     */
    @ExcelHeader(name = "可休天数", notNull = true)
    private Double relaxDay;

    /**
     * 负责人(审批人)
     */
    @ExcelHeader(name = "负责人(审批人)", notNull = true)
    private String charger;

    /**
     * 审核意见
     */
    @ExcelHeader(name = "审核意见")
    private String auditAdvice;

    /**
     * 审核时间
     */
    @ExcelHeader(name = "审核时间")
    private String auditTime;

    /**
     * 审核状态
     */
    @ExcelHeader(name = "审核状态", notNull = true)
    private String auditStatus;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEntryer() {
        return entryer;
    }

    public void setEntryer(String entryer) {
        this.entryer = entryer;
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

    public String getNoonBreakOr() {
        return noonBreakOr;
    }

    public void setNoonBreakOr(String noonBreakOr) {
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

    public String getAuditAdvice() {
        return auditAdvice;
    }

    public void setAuditAdvice(String auditAdvice) {
        this.auditAdvice = auditAdvice;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }
}