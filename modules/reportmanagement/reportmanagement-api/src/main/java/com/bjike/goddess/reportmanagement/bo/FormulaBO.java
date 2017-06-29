package com.bjike.goddess.reportmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.reportmanagement.enums.Form;

/**
 * 对应的公式业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-20 09:56 ]
 * @Description: [ 对应的公式业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FormulaBO extends BaseBO {

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
     * 期末数
     */
    private Double end;

    /**
     * 本期发生额
     */
    private Double current;

    /**
     * 期间
     */
    private String term;

    /**
     * 摘要
     */
    private String digest;

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
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