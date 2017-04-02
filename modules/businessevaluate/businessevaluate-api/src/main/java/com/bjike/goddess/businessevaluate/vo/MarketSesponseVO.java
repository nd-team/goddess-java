package com.bjike.goddess.businessevaluate.vo;

import com.bjike.goddess.businessevaluate.enums.ChannelType;

/**
 * 市场反应和创新能力表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 11:33 ]
 * @Description: [ 市场反应和创新能力表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MarketSesponseVO {

    /**
     * id
     */
    private String id;
    /**
     * 能力成长类型
     */
    private ChannelType channelType;

    /**
     * 客户保持率
     */
    private Double customerKeepRate;

    /**
     * 客户满意度
     */
    private Double customerSatisfaction;

    /**
     * 客户满意度扣分事项
     */
    private String deductionDetail;

    /**
     * 项目信息Id
     */
    private String projectInfoId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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