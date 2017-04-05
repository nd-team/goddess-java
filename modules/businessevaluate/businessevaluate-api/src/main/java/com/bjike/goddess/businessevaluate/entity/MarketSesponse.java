package com.bjike.goddess.businessevaluate.entity;

import com.bjike.goddess.businessevaluate.enums.ChannelType;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 市场反应和创新能力
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 11:33 ]
 * @Description: [ 市场反应和创新能力 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessevaluate_marketsesponse")
public class MarketSesponse extends BaseEntity {

    /**
     * 能力成长类型
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '能力成长类型'")
    private ChannelType channelType;

    /**
     * 客户保持率
     */
    @Column(name = "customerKeepRate", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '客户保持率'")
    private Double customerKeepRate;

    /**
     * 客户满意度
     */
    @Column(name = "customerSatisfaction", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '客户满意度'")
    private Double customerSatisfaction;

    /**
     * 客户满意度扣分事项
     */
    @Column(name = "deductionDetail", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '客户满意度扣分事项'")
    private String deductionDetail;

    /**
     * 项目信息Id
     */
    @Column(name = "projectInfoId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目信息Id'")
    private String projectInfoId;


    public ChannelType getChannelType() {
        return channelType;
    }

    public void setChannelType(ChannelType channelType) {
        this.channelType = channelType;
    }

    public Double getCustomerKeepRate() {
        return customerKeepRate;
    }

    public void setCustomerKeepRate(Double customerKeepRate) {
        this.customerKeepRate = customerKeepRate;
    }

    public Double getCustomerSatisfaction() {
        return customerSatisfaction;
    }

    public void setCustomerSatisfaction(Double customerSatisfaction) {
        this.customerSatisfaction = customerSatisfaction;
    }

    public String getDeductionDetail() {
        return deductionDetail;
    }

    public void setDeductionDetail(String deductionDetail) {
        this.deductionDetail = deductionDetail;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
    }
}