package com.bjike.goddess.financeinit.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 类别数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 04:18 ]
 * @Description: [ 类别数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CategoryDTO extends BaseDTO {


    /**
     * 一级科目名
     */
    private String firstSubjectName;

    /**
     * 二级科目
     */
    private String secondSubject;


    public String getFirstSubjectName() {
        return firstSubjectName;
    }

    public void setFirstSubjectName(String firstSubjectName) {
        this.firstSubjectName = firstSubjectName;
    }

    public String getSecondSubject() {
        return secondSubject;
    }

    public void setSecondSubject(String secondSubject) {
        this.secondSubject = secondSubject;
    }
}