package com.bjike.goddess.staffactivity.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 活动分工
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 08:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffactivity_activitydivision")
public class ActivityDivision extends BaseEntity {

    /**
     * 活动主题
     */
    @Column(name = "theme", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '活动主题'")
    private String theme;

    /**
     * 组织人员名单
     */
    @Column(name = "organizers", nullable = false, columnDefinition = "TEXT COMMENT '组织人员名单'")
    private String organizers;

    /**
     * 组织人员权责明细
     */
    @Column(name = "organizerDuty", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '组织人员权责明细'")
    private String organizerDuty;

    /**
     * 执行人员名单
     */
    @Column(name = "executors", nullable = false, columnDefinition = "TEXT COMMENT '执行人员名单'")
    private String executors;

    /**
     * 执行人员权责明细
     */
    @Column(name = "executorsDuty", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '执行人员权责明细'")
    private String executorsDuty;

    /**
     * 活动监督人
     */
    @Column(name = "supervisor", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '活动监督人'")
    private String supervisor;

    /**
     * 活动方案id
     */
    @Column(name = "schemeId", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '活动方案id'")
    private String schemeId;


    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getOrganizers() {
        return organizers;
    }

    public void setOrganizers(String organizers) {
        this.organizers = organizers;
    }

    public String getOrganizerDuty() {
        return organizerDuty;
    }

    public void setOrganizerDuty(String organizerDuty) {
        this.organizerDuty = organizerDuty;
    }

    public String getExecutors() {
        return executors;
    }

    public void setExecutors(String executors) {
        this.executors = executors;
    }

    public String getExecutorsDuty() {
        return executorsDuty;
    }

    public void setExecutorsDuty(String executorsDuty) {
        this.executorsDuty = executorsDuty;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }
}