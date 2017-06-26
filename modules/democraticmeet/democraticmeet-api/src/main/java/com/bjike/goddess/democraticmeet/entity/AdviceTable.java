package com.bjike.goddess.democraticmeet.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 建议表
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-03 11:26 ]
 * @Description: [ 建议表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "democraticmeet_advicetable")
public class AdviceTable extends BaseEntity {

    /**
     * 他人给予的建议
     */
    @Column(name = "advice",  columnDefinition = "MEDIUMTEXT   COMMENT '他人给予的建议'")
    private String advice;

    /**
     * 建议人
     */
    @Column(name = "advicer",  columnDefinition = "VARCHAR(255)   COMMENT '建议人'")
    private String advicer;


    /**
     * 会议纪要
     */
    @ManyToOne(fetch = FetchType.EAGER , cascade = {CascadeType.REFRESH} )
    @JoinColumn(name = "summary_id",nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '会议纪要'")
    private Summary summary;


    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getAdvicer() {
        return advicer;
    }

    public void setAdvicer(String advicer) {
        this.advicer = advicer;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }
}