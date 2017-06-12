package com.bjike.goddess.secure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 离职名单
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 04:11 ]
 * @Description: [ 离职名单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "secure_outemployee")
public class OutEmployee extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 离职时间
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "DATE   COMMENT '离职时间'")
    private String endTime;

    /**
     * 是否继续购买
     */
    @Column(name = "is_again", columnDefinition = "TINYINT(1)  DEFAULT 0   COMMENT '是否继续购买'")
    private Boolean isAgain;

    /**
     * 意见
     */
    @Column(name = "advice", columnDefinition = "VARCHAR(255)   COMMENT '意见'")
    private String advice;

    /**
     * 离职id
     */
    @Column(name = "dimission_id", unique = true, nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '离职id'")
    private String dimissionId;

    public String getDimissionId() {
        return dimissionId;
    }

    public void setDimissionId(String dimissionId) {
        this.dimissionId = dimissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Boolean getIsAgain() {
        return isAgain;
    }

    public void setIsAgain(Boolean isAgain) {
        this.isAgain = isAgain;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}