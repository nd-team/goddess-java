package com.bjike.goddess.communicatemeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 交流讨论
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-27 02:00 ]
 * @Description: [ 交流讨论 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "communicatemeeting_communicate")
public class Communicate extends BaseEntity {

    /**
     * 交流意见
     */
    @Column(name = "opinion", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '交流意见'")
    private String opinion;

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 票数
     */
    @Column(name = "num", columnDefinition = "INT(11)   COMMENT '票数'")
    private Integer num;


    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}