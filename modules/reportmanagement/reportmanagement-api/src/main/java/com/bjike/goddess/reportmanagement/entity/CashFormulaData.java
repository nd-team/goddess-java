package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 补充资料公式
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-22 03:08 ]
 * @Description: [ 补充资料公式 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reportmanagement_cashformuladata")
public class CashFormulaData extends BaseEntity {

    /**
     * 补充资料id
     */
    @Column(name = "dataId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '补充资料id'")
    private String dataId;

    /**
     * 补充资料
     */
    @Column(name = "data", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '补充资料'")
    private String data;

    /**
     * 公式
     */
    @Column(name = "form", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公式'")
    private String form;


    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }
}