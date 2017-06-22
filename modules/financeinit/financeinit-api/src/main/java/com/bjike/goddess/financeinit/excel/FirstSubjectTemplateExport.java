package com.bjike.goddess.financeinit.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.io.Serializable;

/**
 * 一级科目业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 一级科目业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FirstSubjectTemplateExport implements Serializable{


    /**
     * 会计科目名称
     */
    @ExcelHeader(name = "会计科目名称",notNull = true)
    private String name;

    /**
     * 级别所属类别
     */
    @ExcelHeader(name = "级别所属类别",notNull = true)
    private String category;

    /**
     * 会计科目适用范围
     */
    @ExcelHeader(name = "会计科目适用范围",notNull = true)
    private String remark;




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