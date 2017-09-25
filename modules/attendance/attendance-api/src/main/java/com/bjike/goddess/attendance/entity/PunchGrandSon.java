package com.bjike.goddess.attendance.entity;

import com.bjike.goddess.attendance.enums.PunchStatus;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 打卡孙子表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 05:13 ]
 * @Description: [ 打卡孙子表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attendance_punchgrandson")
public class PunchGrandSon extends BaseEntity {

    /**
     * 打卡状态
     */
    @Column(name = "punchStatus", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '打卡状态'")
    private PunchStatus punchStatus;

    /**
     * 父id
     */
    @Column(name = "punchSonId", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '父id'")
    private String punchSonId;


    public PunchStatus getPunchStatus() {
        return punchStatus;
    }

    public void setPunchStatus(PunchStatus punchStatus) {
        this.punchStatus = punchStatus;
    }

    public String getPunchSonId() {
        return punchSonId;
    }

    public void setPunchSonId(String punchSonId) {
        this.punchSonId = punchSonId;
    }
}