package com.bjike.goddess.attendance.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 补班子表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-21 10:32 ]
 * @Description: [ 补班子表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attendance_extraloverworkson")
public class ExtralOverWorkSon extends BaseEntity {

    /**
     * 补班id
     */
    @Column(name = "fatherId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '补班id'")
    private String fatherId;

    /**
     * 补班开始时间
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "DATETIME   COMMENT '补班开始时间'")
    private LocalDateTime startTime;

    /**
     * 补班结束时间
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "DATETIME   COMMENT '补班结束时间'")
    private LocalDateTime endTime;


    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}