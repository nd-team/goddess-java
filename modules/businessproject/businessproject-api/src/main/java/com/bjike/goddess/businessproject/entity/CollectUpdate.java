package com.bjike.goddess.businessproject.entity;

import com.bjike.goddess.businessproject.enums.MakeContract;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 商务项目合同
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:36 ]
 * @Description: [ 商务项目合同 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessproject_collectupdate")
public class CollectUpdate extends BaseEntity {

    /**
     * 内部项目名称
     */
    @Column(name = "innerName",  columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String innerName;
    /**
     * 修改的总规模数量
     */
    @Column(name = "scaleContract",  columnDefinition = "DECIMAL(10,2)   COMMENT '修改的总规模数量'")
    private Double scaleContract;

    /**
     * 修改的实际完成规模数量
     */
    @Column(name = "finishScale",  columnDefinition = "DECIMAL(10,2)   COMMENT '修改的实际完成规模数量'")
    private Double finishScale;

    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }

    public Double getScaleContract() {
        return scaleContract;
    }

    public void setScaleContract(Double scaleContract) {
        this.scaleContract = scaleContract;
    }

    public Double getFinishScale() {
        return finishScale;
    }

    public void setFinishScale(Double finishScale) {
        this.finishScale = finishScale;
    }
}