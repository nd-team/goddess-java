package com.bjike.goddess.shareholdersmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 股权交易记录详情
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:11 ]
 * @Description: [ 股权交易记录详情 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "shareholdersmanage_equitytransactrecorddetail")
public class EquityTransactRecordDetail extends BaseEntity {

    /**
     * 股东姓名
     */
    @Column(name = "shareholderName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '股东姓名'")
    private String shareholderName;

    /**
     * 交易时间
     */
    @Column(name = "transactDate", nullable = false, columnDefinition = "DATE   COMMENT '交易时间Integer'")
    private LocalDate transactDate;

    /**
     * 持股数量
     */
    @Column(name = "holdNum", nullable = false, columnDefinition = "INT(11)   COMMENT '持股数量'")
    private Integer holdNum;
    /**
     * 每股价格/元
     */
    @Column(name = "perSharePrice", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '每股价格/元'")
    private Double perSharePrice;

    /**
     * 金额
     */
    @Column(name = "amount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '金额'")
    private Double amount;

    /**
     * 交易类型
     */
    @Column(name = "transactType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '交易类型'")
    private String transactType;

    /**
     * 对应交易类型id
     */
    @Column(name = "transactId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '对应交易类型id'")
    private String transactId;


    public Integer getHoldNum() {
        return holdNum;
    }

    public void setHoldNum(Integer holdNum) {
        this.holdNum = holdNum;
    }

    public String getShareholderName() {
        return shareholderName;
    }

    public void setShareholderName(String shareholderName) {
        this.shareholderName = shareholderName;
    }

    public LocalDate getTransactDate() {
        return transactDate;
    }

    public void setTransactDate(LocalDate transactDate) {
        this.transactDate = transactDate;
    }

    public Double getPerSharePrice() {
        return perSharePrice;
    }

    public void setPerSharePrice(Double perSharePrice) {
        this.perSharePrice = perSharePrice;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactType() {
        return transactType;
    }

    public void setTransactType(String transactType) {
        this.transactType = transactType;
    }

    public String getTransactId() {
        return transactId;
    }

    public void setTransactId(String transactId) {
        this.transactId = transactId;
    }
}