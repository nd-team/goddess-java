package com.bjike.goddess.attendance.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 手机提醒打卡
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 09:28 ]
 * @Description: [ 手机提醒打卡 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attendance_phoneremind")
public class PhoneRemind extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 是否启用
     */
    @Column(name = "is_enable", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否启用'")
    private Boolean enable;

    /**
     * 上次提醒时间（上班提醒）
     */
    @Column(name = "goLastTime", columnDefinition = "DATETIME   COMMENT '上次提醒时间（上班提醒）'")
    private LocalDateTime goLastTime;

    /**
     * 上次提醒时间（下班提醒）
     */
    @Column(name = "outLastTime", columnDefinition = "DATETIME   COMMENT '上次提醒时间（下班提醒）'")
    private LocalDateTime outLastTime;

    public LocalDateTime getGoLastTime() {
        return goLastTime;
    }

    public void setGoLastTime(LocalDateTime goLastTime) {
        this.goLastTime = goLastTime;
    }

    public LocalDateTime getOutLastTime() {
        return outLastTime;
    }

    public void setOutLastTime(LocalDateTime outLastTime) {
        this.outLastTime = outLastTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}