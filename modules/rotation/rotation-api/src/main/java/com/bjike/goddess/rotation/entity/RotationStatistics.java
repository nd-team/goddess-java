package com.bjike.goddess.rotation.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 岗位轮换统计
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:38 ]
 * @Description: [ 岗位轮换统计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "rotation_rotationstatistics")
public class RotationStatistics extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "username", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String username;

    /**
     * 岗位层级
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "arrangement_id", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位层级'")
    private SubsidyStandard arrangement;

    /**
     * 补助周期开始时间
     */
    @Column(name = "subsidyStart", nullable = false, columnDefinition = "DATE   COMMENT '补助周期开始时间'")
    private LocalDate subsidyStart;

    /**
     * 补助周期结束时间
     */
    @Column(name = "subsidyEnd", nullable = false, columnDefinition = "DATE   COMMENT '补助周期结束时间'")
    private LocalDate subsidyEnd;

    /**
     * 开始担任时间
     */
    @Column(name = "occupyStart", nullable = false, columnDefinition = "DATE   COMMENT '开始担任时间'")
    private LocalDate occupyStart;

    /**
     * 结束担任时间
     */
    @Column(name = "occupyEnd", nullable = false, columnDefinition = "DATE   COMMENT '结束担任时间'")
    private LocalDate occupyEnd;

    /**
     * 周期天数
     */
    @Column(name = "cycle", nullable = false, columnDefinition = "INT(11)   COMMENT '周期天数'")
    private Integer cycle;

    /**
     * 周期内补贴天数
     */
    @Column(name = "subsidy", nullable = false, columnDefinition = "INT(11)   COMMENT '周期内补贴天数'")
    private Integer subsidy;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SubsidyStandard getArrangement() {
        return arrangement;
    }

    public void setArrangement(SubsidyStandard arrangement) {
        this.arrangement = arrangement;
    }

    public LocalDate getSubsidyStart() {
        return subsidyStart;
    }

    public void setSubsidyStart(LocalDate subsidyStart) {
        this.subsidyStart = subsidyStart;
    }

    public LocalDate getSubsidyEnd() {
        return subsidyEnd;
    }

    public void setSubsidyEnd(LocalDate subsidyEnd) {
        this.subsidyEnd = subsidyEnd;
    }

    public LocalDate getOccupyStart() {
        return occupyStart;
    }

    public void setOccupyStart(LocalDate occupyStart) {
        this.occupyStart = occupyStart;
    }

    public LocalDate getOccupyEnd() {
        return occupyEnd;
    }

    public void setOccupyEnd(LocalDate occupyEnd) {
        this.occupyEnd = occupyEnd;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Integer getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(Integer subsidy) {
        this.subsidy = subsidy;
    }
}