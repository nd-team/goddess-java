package com.bjike.goddess.businessevaluate.to;

import com.bjike.goddess.businessevaluate.enums.ChannelType;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 市场反应和创新能力
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 11:33 ]
 * @Description: [ 市场反应和创新能力 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MarketSesponseTO extends BaseTO {

    /**
     * 渠道
     */
    @NotNull(message = "渠道不能为空",groups = {ADD.class, EDIT.class})
    private ChannelType channelType;

    /**
     * 客户保持率
     */
    @NotNull(message = "客户保持率不能为空",groups = {ADD.class, EDIT.class})
    private Double customerKeepRate;

    /**
     * 客户满意度
     */
    @NotNull(message = "客户满意度不能为空",groups = {ADD.class, EDIT.class})
    private Double customerSatisfaction;

    /**
     * 客户满意度扣分事项
     */
    @NotBlank(message = "客户满意度扣分事项不能为空",groups = {ADD.class, EDIT.class})
    private String deductionDetail;

    /**
     * 项目信息Id
     */
    @NotBlank(message = "项目不能为空",groups = {ADD.class, EDIT.class})
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