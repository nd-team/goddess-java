package com.bjike.goddess.supplier.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 获奖情况
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:53:15.048 ]
 * @Description: [ 获奖情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "supplier_reward_situation")
public class RewardSituation extends BaseEntity {

    /**
     * 供应商基本信息
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "information_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '供应商基本信息sdfsd'")
    private SupplierInformation information;

    /**
     * 获奖名称
     */
    @Column( nullable = false, columnDefinition = "VARCHAR(100)   COMMENT '获奖名称jhgfouyhgjh'")
    private String awardName;

    /**
     * 获得时间
     */
    @Column(name = "acquisition", nullable = false, columnDefinition = "DATE   COMMENT '获得时间ss'")
    private LocalDate acquisition;


    public SupplierInformation getInformation() {
        return information;
    }

    public void setInformation(SupplierInformation information) {
        this.information = information;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public LocalDate getAcquisition() {
        return acquisition;
    }

    public void setAcquisition(LocalDate acquisition) {
        this.acquisition = acquisition;
    }
}