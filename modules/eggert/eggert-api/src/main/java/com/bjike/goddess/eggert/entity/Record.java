package com.bjike.goddess.eggert.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 信息记录
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:22 ]
 * @Description: [ 信息记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "eggert_record")
public class Record extends BaseEntity {

    /**
     * 答案
     */
    @Column(name = "answer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '答案'")
    private String answer;

    /**
     * 答题信息
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "exam_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '答题信息'")
    private Exam exam;
    /**
     * 设置考题
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "examQuestions_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '设置考题'")
    private ExamQuestions examQuestions;


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public ExamQuestions getExamQuestions() {
        return examQuestions;
    }

    public void setExamQuestions(ExamQuestions examQuestions) {
        this.examQuestions = examQuestions;
    }
}