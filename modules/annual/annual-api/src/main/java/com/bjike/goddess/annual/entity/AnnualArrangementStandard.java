package com.bjike.goddess.annual.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 年假层级标准
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:33 ]
 * @Description: [ 年假层级标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "annual_annual_arrangement_standard")
public class AnnualArrangementStandard extends BaseEntity {

    /**
     * 标准
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "standard_id", columnDefinition = "VARCHAR(255)   COMMENT '标准'")
    private AnnualStandard standard;

    /**
     * 岗位层级id
     */
    @Column(name = "arrangement_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '岗位层级id'")
    private String arrangementId;

    /**
     * 年假天数
     */
    @Column(name = "days", nullable = false, columnDefinition = "INT(11)   COMMENT '年假天数'")
    private Integer days;


    public AnnualStandard getStandard() {
        return standard;
    }

    public void setStandard(AnnualStandard standard) {
        this.standard = standard;
    }

    public String getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(String arrangementId) {
        this.arrangementId = arrangementId;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}