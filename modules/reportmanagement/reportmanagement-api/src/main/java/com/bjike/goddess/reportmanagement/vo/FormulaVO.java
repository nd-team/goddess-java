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
     * 公式方向
     */
    private Form form;

    /**
     * 年初数
     */
    private Double begin;

    /**
     * 年末数
     */
    private Double end;

    /**
     * 本月数
     */
    private Double current;

    /**
     * 运算方式
     */
    private String operation;

    /**
     * 本年累计数
     */
    private Double year;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getYear() {
        return year;
    }

    public void setYear(Double year) {
        this.year = year;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

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