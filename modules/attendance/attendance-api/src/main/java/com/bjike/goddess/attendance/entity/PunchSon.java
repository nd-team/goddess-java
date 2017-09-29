package com.bjike.goddess.attendance.entity;

import com.bjike.goddess.attendance.enums.PunchSource;
import com.bjike.goddess.attendance.enums.PunchStatus;
import com.bjike.goddess.attendance.enums.PunchType;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 打卡子表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:26 ]
 * @Description: [ 打卡子表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attendance_punchson")
public class PunchSon extends BaseEntity {

    /**
     * 打卡时间
     */
    @Column(name = "punchTime", nullable = false, columnDefinition = "DATETIME   COMMENT '打卡时间'")
    private LocalDateTime punchTime;

    /**
     * 打卡地点
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地点'")
    private String area;

    /**
     * 打卡来源
     */
    @Column(name = "punchSource", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '打卡来源'")
    private PunchSource punchSource;
    /**
     * 打卡父id
     */
    @Column(name = "punch_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '打卡父id'")
    private String punchId;
    /**
     * 打卡类型
     */
    @Column(name = "punchType", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '打卡类型'")
    private PunchType punchType;

    public String getPunchId() {
        return punchId;
    }

    public void setPunchId(String punchId) {
        this.punchId = punchId;
    }

    public PunchType getPunchType() {
        return punchType;
    }

    public void setPunchType(PunchType punchType) {
        this.punchType = punchType;
    }

    public PunchSource getPunchSource() {
        return punchSource;
    }

    public void setPunchSource(PunchSource punchSource) {
        this.punchSource = punchSource;
    }

    public LocalDateTime getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(LocalDateTime punchTime) {
        this.punchTime = punchTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}