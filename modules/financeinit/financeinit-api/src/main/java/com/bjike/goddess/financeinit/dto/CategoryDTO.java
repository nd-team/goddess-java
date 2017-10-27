package com.bjike.goddess.financeinit.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.financeinit.enums.CategoryName;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

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

    public interface TestList{}

    /**
     * 一级科目名
     */
    private String firstSubjectName;

    /**
     * 二级科目
     */
    private String secondSubject;

    /**
     * 三级科目
     */
    private String thirdSubject;

    /**
     * 说明
     */
    private String remark;

    /**
     * 类别
     */
    @NotNull(groups = {CategoryDTO.TestList.class} , message = "类别不能为空")
    private CategoryName categoryName;


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

    public String getThirdSubject() {
        return thirdSubject;
    }

    public void setThirdSubject(String thirdSubject) {
        this.thirdSubject = thirdSubject;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }
}