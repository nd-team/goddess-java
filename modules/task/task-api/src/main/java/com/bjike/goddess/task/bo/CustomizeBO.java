package com.bjike.goddess.task.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.task.enums.DateType;
import com.bjike.goddess.task.enums.NoticeType;
import com.bjike.goddess.task.enums.SummaryType;
import com.bjike.goddess.task.enums.TimeType;

/**
 * 自定义汇总
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-25 17:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CustomizeBO extends BaseBO{
    /**
     * 汇总名
     */
    private String collectName;
    /**
     * 项目id
     */
    private String projectId;

    /**
     * 项目
     */
    private String project;

    /**
     * 表
     */
    private String[] tables;
    /**
     * 表id
     */
    private String tablesId;
    /**
     * 创建人
     */
    private String user;

    /**
     * 汇总自定义字段
     */
    private String fields;
    /**
     * 定时时间类型
     */
    private TimeType timeType;

    /**
     * 定时时间间隔值
     */
    private String timeVal;

    /**
     * 汇总部门
     */
    private String department;

    /**
     * 通知类型
     */
    private NoticeType noticeType;
    /**
     * 通知目标(部门,用户等,所有时为空)
     */
    private String noticeTarget;

    /**
     * 是否启用
     */
    private Boolean enable;
    /**
     * 汇总类型
     */
    private SummaryType summaryType;
    /**
     * 汇总目标(部门,用户等,所有时为空)
     */
    private String summaryTarget;
    /**
     * 间隔类型
     */
    private DateType dateType;
    /**
     * 间隔值
     */
    private Integer dateVal;

    /**
     * 上次发送时间
     */
    private String lastTime;

    public String getCollectName() {
        return collectName;
    }

    public void setCollectName(String collectName) {
        this.collectName = collectName;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String[] getTables() {
        return tables;
    }

    public void setTables(String[] tables) {
        this.tables = tables;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public TimeType getTimeType() {
        return timeType;
    }

    public void setTimeType(TimeType timeType) {
        this.timeType = timeType;
    }

    public String getTimeVal() {
        return timeVal;
    }

    public void setTimeVal(String timeVal) {
        this.timeVal = timeVal;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public NoticeType getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(NoticeType noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeTarget() {
        return noticeTarget;
    }

    public void setNoticeTarget(String noticeTarget) {
        this.noticeTarget = noticeTarget;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public SummaryType getSummaryType() {
        return summaryType;
    }

    public void setSummaryType(SummaryType summaryType) {
        this.summaryType = summaryType;
    }

    public String getSummaryTarget() {
        return summaryTarget;
    }

    public void setSummaryTarget(String summaryTarget) {
        this.summaryTarget = summaryTarget;
    }

    public DateType getDateType() {
        return dateType;
    }

    public void setDateType(DateType dateType) {
        this.dateType = dateType;
    }

    public Integer getDateVal() {
        return dateVal;
    }

    public void setDateVal(Integer dateVal) {
        this.dateVal = dateVal;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTablesId() {
        return tablesId;
    }

    public void setTablesId(String tablesId) {
        this.tablesId = tablesId;
    }
}
