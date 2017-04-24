package com.bjike.goddess.function.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 用户功能
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-22 01:56 ]
 * @Description: [ 用户功能 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "user_function")
public class UserFunction extends BaseEntity {

    /**
     * 功能
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "function_id",nullable = false,columnDefinition = "VARCHAR(36) COMMENT '功能' ")
    private Function function;

    /**
     * 所属人
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '所属人'")
    private String userId;

    /**
     * 排序序列
     */
    @Column(nullable = false, columnDefinition = "INT(3)   COMMENT '排序序列'")
    private Integer seq;


    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

}