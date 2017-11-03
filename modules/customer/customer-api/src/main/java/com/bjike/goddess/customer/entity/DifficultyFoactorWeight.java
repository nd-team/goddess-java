package com.bjike.goddess.customer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 难易度因素层权重
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:20 ]
 * @Description: [ 难易度因素层权重 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customer_difficultyfoactorweight")
public class DifficultyFoactorWeight extends BaseEntity {

    /**
     * 困难度类型名称
     */
    @Column(name = "difficName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '困难度类型名称'")
    private String difficName;

    /**
     * 困难度类型权重
     */
    @Column(name = "difficWeight", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '困难度类型权重'")
    private Double difficWeight;


    public String getDifficName() {
        return difficName;
    }

    public void setDifficName(String difficName) {
        this.difficName = difficName;
    }

    public Double getDifficWeight() {
        return difficWeight;
    }

    public void setDifficWeight(Double difficWeight) {
        this.difficWeight = difficWeight;
    }
}