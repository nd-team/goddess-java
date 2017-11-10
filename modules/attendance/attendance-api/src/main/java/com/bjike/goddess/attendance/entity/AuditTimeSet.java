package com.bjike.goddess.attendance.entity;

import com.bjike.goddess.attendance.enums.AuditType;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 审批时间设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 04:37 ]
 * @Description: [ 审批时间设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attendance_audittimeset")
public class AuditTimeSet extends BaseEntity {

    /**
     * 创建人
     */
    @Column(name = "creater", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建人'")
    private String creater;

    /**
     * 项目组
     */
    @Column(name = "depart", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String depart;

    /**
     * 岗位
     */
    @Column(name = "position", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String position;

    /**
     * 审批类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '审批类型'")
    private AuditType type;

    /**
     * 设置审核时间（小时内）
     */
    @Column(name = "time", nullable = false, columnDefinition = "INT(11)   COMMENT '设置审核时间（小时内）'")
    private Integer time;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
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

    public AuditType getType() {
        return type;
    }

    public void setType(AuditType type) {
        this.type = type;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}