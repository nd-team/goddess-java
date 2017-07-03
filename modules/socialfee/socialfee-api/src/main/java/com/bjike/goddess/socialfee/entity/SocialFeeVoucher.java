package com.bjike.goddess.socialfee.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 社会缴费记账凭证记录
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-20 10:40 ]
 * @Description: [ 社会缴费记账凭证记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "socialfee_socialfeevoucher")
public class SocialFeeVoucher extends BaseEntity {

    /**
     * 缴费所属年月
     */
    @Column(name = "payTimeYear", nullable = false, unique = true ,columnDefinition = "VARCHAR(255)   COMMENT '缴费所属年月'")
    private String payTimeYear;

    /**
     * 记账凭证id
     */
    @Column(name = "voucherId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '记账凭证id'")
    private String voucherId;

    /**
     * 应缴金额
     */
    @Column(name = "totalMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '应缴金额'")
    private Double totalMoney;


    public String getPayTimeYear() {
        return payTimeYear;
    }

    public void setPayTimeYear(String payTimeYear) {
        this.payTimeYear = payTimeYear;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

}