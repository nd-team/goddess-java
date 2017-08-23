package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 资产负债总表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:37 ]
 * @Description: [ 资产负债总表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reportmanagement_assetdebt")
public class AssetDebt extends BaseEntity {

    /**
     * 起始时间
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "DATE   COMMENT '起始时间'")
    private LocalDate startTime;

    /**
     * 结束时间
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "DATE   COMMENT '结束时间'")
    private LocalDate endTime;

    /**
     * 对应的行次
     */
    @Column(name = "num", nullable = false, columnDefinition = "INT(11)   COMMENT '对应的行次'")
    private Integer num;


    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}