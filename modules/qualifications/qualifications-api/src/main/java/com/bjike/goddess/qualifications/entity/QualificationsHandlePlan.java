package com.bjike.goddess.qualifications.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 资质办理计划
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:46 ]
 * @Description: [ 资质办理计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "qualifications_handle_plan")
public class QualificationsHandlePlan extends BaseEntity {

    /**
     * 资质办理
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "handle_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '资质办理'")
    private QualificationsHandle handle;

    /**
     * 准备开始时间
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "DATE   COMMENT '准备开始时间'")
    private LocalDate startTime;

    /**
     * 计划结束时间
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "DATE   COMMENT '计划结束时间'")
    private LocalDate endTime;

    /**
     * 办理方式
     */
    @Column(name = "way", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '办理方式'")
    private String way;


    public QualificationsHandle getHandle() {
        return handle;
    }

    public void setHandle(QualificationsHandle handle) {
        this.handle = handle;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }
}