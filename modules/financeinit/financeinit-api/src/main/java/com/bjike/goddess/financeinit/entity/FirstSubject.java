package com.bjike.goddess.financeinit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 一级科目
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 一级科目 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "financeinit_firstsubject")
public class FirstSubject extends BaseEntity {

    /**
     * 编号
     */
    @Column(name = "code", nullable = false, unique = true , columnDefinition = "VARCHAR(255)   COMMENT '编号'")
    private String code;

    /**
     * 会计科目名称
     */
    @Column(name = "name", nullable = false,unique = true ,columnDefinition = "VARCHAR(255)   COMMENT '会计科目名称'")
    private String name;

    /**
     * 级别所属类别
     */
    @Column(name = "category", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '级别所属类别'")
    private String category;

    /**
     * 会计科目适用范围
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)  COMMENT '会计科目适用范围'")
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}