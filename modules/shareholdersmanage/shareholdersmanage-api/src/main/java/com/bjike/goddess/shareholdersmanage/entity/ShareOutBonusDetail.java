package com.bjike.goddess.shareholdersmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 分红明细
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:55 ]
 * @Description: [ 分红明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "shareholdersmanage_shareoutbonusdetail")
public class ShareOutBonusDetail extends BaseEntity {

    /**
     * 股东姓名
     */
    @Column(name = "shareholderName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '股东姓名'")
    private String shareholderName;

    /**
     * 共分红
     */
    @Column(name = "totalShareOutBonus", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '共分红'")
    private Double totalShareOutBonus;

    /**
     * 分红比例
     */
    @Column(name = "shareOutBonusPropor", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '分红比例'")
    private Double shareOutBonusPropor;

    /**
     * 分红额
     */
    @Column(name = "shareOutBonusAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '分红额'")
    private Double shareOutBonusAmount;

    /**
     * 所得税比例
     */
    @Column(name = "incomeTaxPropor", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '所得税比例'")
    private Double incomeTaxPropor;

    /**
     * 所得税
     */
    @Column(name = "incomeTax", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '所得税'")
    private Double incomeTax;

    /**
     * 税后利润
     */
    @Column(name = "afterTaxProfits", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '税后利润'")
    private Double afterTaxProfits;

    /**
     * 分红管理id
     */
    @Column(name = "shareOutBonusManageId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分红管理id'")
    private String shareOutBonusManageId;


    public String getShareholderName() {
        return shareholderName;
    }

    public void setShareholderName(String shareholderName) {
        this.shareholderName = shareholderName;
    }

    public Double getTotalShareOutBonus() {
        return totalShareOutBonus;
    }

    public void setTotalShareOutBonus(Double totalShareOutBonus) {
        this.totalShareOutBonus = totalShareOutBonus;
    }

    public Double getShareOutBonusPropor() {
        return shareOutBonusPropor;
    }

    public void setShareOutBonusPropor(Double shareOutBonusPropor) {
        this.shareOutBonusPropor = shareOutBonusPropor;
    }

    public Double getShareOutBonusAmount() {
        return shareOutBonusAmount;
    }

    public void setShareOutBonusAmount(Double shareOutBonusAmount) {
        this.shareOutBonusAmount = shareOutBonusAmount;
    }

    public Double getIncomeTaxPropor() {
        return incomeTaxPropor;
    }

    public void setIncomeTaxPropor(Double incomeTaxPropor) {
        this.incomeTaxPropor = incomeTaxPropor;
    }

    public Double getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(Double incomeTax) {
        this.incomeTax = incomeTax;
    }

    public Double getAfterTaxProfits() {
        return afterTaxProfits;
    }

    public void setAfterTaxProfits(Double afterTaxProfits) {
        this.afterTaxProfits = afterTaxProfits;
    }

    public String getShareOutBonusManageId() {
        return shareOutBonusManageId;
    }

    public void setShareOutBonusManageId(String shareOutBonusManageId) {
        this.shareOutBonusManageId = shareOutBonusManageId;
    }
}