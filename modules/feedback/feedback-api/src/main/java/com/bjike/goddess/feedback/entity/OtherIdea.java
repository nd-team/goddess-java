package com.bjike.goddess.feedback.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 其他模块意见
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 06:48 ]
 * @Description: [ 其他模块意见 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "feedback_otheridea")
public class OtherIdea extends BaseEntity {

    /**
     * 模块
     */
    @Column(name = "module", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '模块'")
    private String module;

    /**
     * 意见
     */
    @Column(name = "idea", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '意见'")
    private String idea;


    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }
}