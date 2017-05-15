package com.bjike.goddess.rotation.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 岗位轮换条件
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:41 ]
 * @Description: [ 岗位轮换条件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "rotation_rotationcondition")
public class RotationCondition extends BaseEntity {

    /**
     * 轮换方式
     */
    @Column(name = "way", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '轮换方式'")
    private String way;

    /**
     * 条件
     */
    @Column(name = "condition", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '条件'")
    private String condition;


    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}