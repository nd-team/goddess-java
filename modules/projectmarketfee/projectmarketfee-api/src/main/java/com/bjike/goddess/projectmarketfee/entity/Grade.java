package com.bjike.goddess.projectmarketfee.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 等级设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-09 04:55 ]
 * @Description: [ 等级设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectmarketfee_grade")
public class Grade extends BaseEntity {

    /**
     * 备注
     */
    @Column(name = "note", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String note;

    /**
     * 等级
     */
    @Column(name = "grade", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '等级'")
    private String grade;


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}