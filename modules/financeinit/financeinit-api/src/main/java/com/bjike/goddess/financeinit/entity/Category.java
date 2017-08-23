package com.bjike.goddess.financeinit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.financeinit.enums.CategoryName;

import javax.persistence.*;


/**
 * 类别
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 04:18 ]
 * @Description: [ 类别 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "financeinit_category")
public class Category extends BaseEntity {

    /**
     * 代码
     */
    @Column(name = "code", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '代码'")
    private String code;
    /**
     * 一级科目代码
     */
    @Column(name = "firstCode", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '一级科目代码'")
    private String firstCode;
    /**
     * 二级科目代码
     */
    @Column(name = "secondCode", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '二级科目代码'")
    private String secondCode;
    /**
     * 三级科目代码
     */
    @Column(name = "thirdCode",  columnDefinition = "VARCHAR(255)   COMMENT '三级科目代码'")
    private String thirdCode;

    /**
     * 二级科目
     */
    @Column(name = "secondSubject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '二级科目'")
    private String secondSubject;

    /**
     * 三级科目
     */
    @Column(name = "thirdSubject",  columnDefinition = "VARCHAR(255)   COMMENT '三级科目'")
    private String thirdSubject;

    /**
     * 说明
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '说明'")
    private String remark;

    /**
     * 类别
     */
    @Column(name = "categoryName", nullable = false, columnDefinition = "INT(2) COMMENT '类别'")
    private CategoryName categoryName;

    /**
     * 一级科目
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "firstSubject_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '一级科目'")
    private FirstSubject firstSubject;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstCode() {
        return firstCode;
    }

    public void setFirstCode(String firstCode) {
        this.firstCode = firstCode;
    }

    public String getSecondCode() {
        return secondCode;
    }

    public void setSecondCode(String secondCode) {
        this.secondCode = secondCode;
    }

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
    }

    public FirstSubject getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(FirstSubject firstSubject) {
        this.firstSubject = firstSubject;
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