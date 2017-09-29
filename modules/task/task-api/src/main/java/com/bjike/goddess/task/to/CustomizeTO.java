package com.bjike.goddess.task.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.task.enums.DateType;
import com.bjike.goddess.task.enums.NoticeType;
import com.bjike.goddess.task.enums.SummaryType;
import com.bjike.goddess.task.enums.TimeType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 定制化
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-25 17:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CustomizeTO extends BaseTO {
    /**
     * 汇总名
     */
    @NotBlank(message = "汇总名不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 项目
     */
    @NotBlank(message = "项目不能为空", groups = {ADD.class, EDIT.class})
    private String projectId;

    /**
     * 表id
     */
    @NotNull(message = "表id不能为空", groups = {ADD.class, EDIT.class})
    private String[] tables;


    /**
     * 汇总字段
     */
    @NotNull(message = "汇总字段不能为空", groups = {ADD.class, EDIT.class})
    private String[] fields;
    /**
     * 定时时间类型
     */
    @NotNull(message = "定时时间类型不能为空", groups = {ADD.class, EDIT.class})
    private TimeType timeType;

    /**
     * 定时时间间隔值
     */
    @NotBlank(message = "定时间隔值不能为空", groups = {ADD.class, EDIT.class})
    private String timeVal;

    /**
     * 通知类型
     */
    @NotNull(message = "通知类型不能为空", groups = {ADD.class, EDIT.class})
    private NoticeType noticeType;
    /**
     * 通知目标(部门,用户等,所有时为空)
     */
    private String noticeTarget;

    /**
     * 是否启用
     */
    @NotNull(message = "是否启用不能为空", groups = {ADD.class, EDIT.class})
    private Boolean enable;
    /**
     * 汇总类型,执行项目的所有人还是个人...
     */
    @NotNull(message = "汇总类型不能为空", groups = {ADD.class, EDIT.class})
    private SummaryType summaryType;
    /**
     * 汇总目标(部门,用户等,所有时为空)
     */
    private String summaryTarget;
    /**
     * 间隔类型
     */
    @NotNull(message = "间隔类型不能为空", groups = {ADD.class, EDIT.class})
    private DateType dateType;
    /**
     * 间隔值
     */
    @NotNull(message = "间隔值不能为空", groups = {ADD.class, EDIT.class})
    private Integer dateVal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String[] getTables() {
        return tables;
    }

    public void setTables(String[] tables) {
        this.tables = tables;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
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

}
