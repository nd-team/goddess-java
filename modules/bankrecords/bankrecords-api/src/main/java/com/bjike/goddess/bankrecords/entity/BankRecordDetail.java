package com.bjike.goddess.bankrecords.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 银行流水明细
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 05:47 ]
 * @Description: [ 银行流水明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "bankrecords_bankrecorddetail")
public class BankRecordDetail extends BaseEntity {

    /**
     * 标题
     */
    @Column(name = "title", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '标题'")
    private String title;

    /**
     * 内容
     */
    @Column(name = "val", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内容'")
    private String val;

    /**
     * 银行流水Id
     */
    @Column(name = "bankRecordId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '银行流水Id'")
    private String bankRecordId;

    /**
     * 标题下标
     */
    @Column(name = "titleIndex", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '标题下标'")
    private Integer titleIndex;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getBankRecordId() {
        return bankRecordId;
    }

    public void setBankRecordId(String bankRecordId) {
        this.bankRecordId = bankRecordId;
    }

    public Integer getTitleIndex() {
        return titleIndex;
    }

    public void setTitleIndex(Integer titleIndex) {
        this.titleIndex = titleIndex;
    }
}