package com.bjike.goddess.attendance.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 自动打卡
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-06 09:22 ]
 * @Description: [ 自动打卡 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attendance_autopunch")
public class AutoPunch extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, unique = true,columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 上班时间
     */
    @Column(name = "goTime", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '上班时间'")
    private String goTime;

    /**
     * 下班时间
     */
    @Column(name = "outTime", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '下班时间'")
    private String outTime;

    /**
     * 是否启用
     */
    @Column(name = "is_enable", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否启用'")
    private Boolean enable;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoTime() {
        return goTime;
    }

    public void setGoTime(String goTime) {
        this.goTime = goTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }
}