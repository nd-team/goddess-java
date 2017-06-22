package com.bjike.goddess.reportmanagement.vo;

import com.bjike.goddess.reportmanagement.enums.Form;

/**
 * 对应的公式表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-20 09:56 ]
 * @Description: [ 对应的公式表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FormulaVO {

    /**
     * id
     */
    private String id;
    /**
     * 科目
     */
    private String project;

    /**
     * 公式添加的形式
     */
    private Form form;

    /**
     * 年初数
     */
    private Double begin;

    /**
     * 期末数
     */
    private Double end;

    public Double getBegin() {
        return begin;
    }

    public void setBegin(Double begin) {
        this.begin = begin;
    }

    public Double getEnd() {
        return end;
    }

    public void setEnd(Double end) {
        this.end = end;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}