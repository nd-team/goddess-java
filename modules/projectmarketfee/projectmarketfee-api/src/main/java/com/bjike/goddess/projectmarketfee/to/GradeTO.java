package com.bjike.goddess.projectmarketfee.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 等级设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-09 04:55 ]
 * @Description: [ 等级设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class GradeTO extends BaseTO {

    /**
     * 备注
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "备注不能为空")
    private String note;

    /**
     * 等级
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "等级不能为空")
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