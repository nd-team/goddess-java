package com.bjike.goddess.projectmarketfee.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 等级设计业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-09 04:55 ]
 * @Description: [ 等级设计业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class GradeBO extends BaseBO {

    /**
     * 备注
     */
    private String note;

    /**
     * 等级
     */
    private String grade;

    /**
     * 总记录数
     */
    private Long num;

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

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