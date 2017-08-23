package com.bjike.goddess.bankrecords.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


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
     * 银行流水
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bankRecord_id", referencedColumnName = "id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '银行流水'")
    private BankRecord bankRecord;

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
     * 标题下标
     */
    @Column(name = "titleIndex", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '标题下标'")
    private Integer titleIndex;

    public BankRecord getBankRecord() {
        return bankRecord;
    }

    public void setBankRecord(BankRecord bankRecord) {
        this.bankRecord = bankRecord;
    }

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

    public Integer getTitleIndex() {
        return titleIndex;
    }

    public void setTitleIndex(Integer titleIndex) {
        this.titleIndex = titleIndex;
    }
}