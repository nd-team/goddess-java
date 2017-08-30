package com.bjike.goddess.reportmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.reportmanagement.enums.Form;

/**
 * 明细
 *
 * @Author: [chenjunhao]
 * @Date: [2017-06-30 14:09]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DetailBO extends BaseBO {
    /**
     * 科目
     */
    private String project;
    /**
     * 期间
     */
    private String term;
    /**
     * 摘要
     */
    private String state;
    /**
     * 借方
     */
    private Double debit;
    /**
     * 贷方
     */
    private Double credit;

    /**
     * 方向
     */
    private Form form;

    /**
     * 余额
     */
    private Double remain;

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Double getRemain() {
        return remain;
    }

    public void setRemain(Double remain) {
        this.remain = remain;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
}
