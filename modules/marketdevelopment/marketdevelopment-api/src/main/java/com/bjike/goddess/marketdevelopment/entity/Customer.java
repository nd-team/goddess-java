package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.marketdevelopment.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 客户接触阶段
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-29 09:37 ]
 * @Description: [ 客户接触阶段 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_customer")
public class Customer extends BaseEntity {

    /**
     * 阶段编码
     */
    @Column(name = "code", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '阶段编码'")
    private String code;

    /**
     * 阶段
     */
    @Column(name = "stage", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '阶段'")
    private String stage;

    /**
     * 定义
     */
    @Column(name = "define", columnDefinition = "VARCHAR(255)   COMMENT '定义'")
    private String define;

    /**
     * 输出结果
     */
    @Column(name = "results", columnDefinition = "VARCHAR(255)   COMMENT '输出结果'")
    private String results;

    /**
     * 阶段数量
     */
    @Column(name = "stageNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '阶段数量'")
    private String stageNum;

    /**
     * 状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '状态' ", nullable = false)
    private Status status;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDefine() {
        return define;
    }

    public void setDefine(String define) {
        this.define = define;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getStageNum() {
        return stageNum;
    }

    public void setStageNum(String stageNum) {
        this.stageNum = stageNum;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}