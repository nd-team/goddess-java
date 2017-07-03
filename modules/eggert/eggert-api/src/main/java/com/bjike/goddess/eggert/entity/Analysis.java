package com.bjike.goddess.eggert.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 分析记录
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:23 ]
 * @Description: [ 分析记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "eggert_analysis")
public class Analysis extends BaseEntity {

    /**
     * 意见
     */
    @Column(name = "opinion", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '意见'")
    private String opinion;

    /**
     * 答题信息
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "exam_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '答题信息'")
    private Exam exam;

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}